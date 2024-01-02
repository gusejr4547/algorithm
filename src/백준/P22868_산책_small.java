package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P22868_산책_small {
    static int N, M;
    static List<Integer>[] adj;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj[A].add(B);
            adj[B].add(A);
        }
        for (int i = 0; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int answer = 0;
        boolean[] visit = new boolean[N + 1];
        Node result = bfs(S, E, visit);
        answer += result.cost;
        Arrays.fill(visit, false);
        st = new StringTokenizer(result.path);
        while (st.hasMoreTokens()) {
            visit[Integer.parseInt(st.nextToken())] = true;
        }
        visit[S] = false;
        visit[E] = false;
//        System.out.println(Arrays.toString(visit));
        result = bfs(E, S, visit);
        answer += result.cost;

        System.out.println(answer);
    }

    static Node bfs(int start, int end, boolean[] visit) {
        ArrayDeque<Node> pq = new ArrayDeque<>();
        pq.offer(new Node(start, 0, start + ""));
        visit[start] = true;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.num == end) {
//                System.out.println(curr.path);
                return curr;
            }


            for (int next : adj[curr.num]) {
                if (visit[next]) continue;

                pq.offer(new Node(next, curr.cost + 1, curr.path + " " + next));
                visit[next] = true;
            }
        }

        return null;
    }

    static class Node {
        int num;
        int cost;
        String path;

        public Node(int num, int cost, String path) {
            this.num = num;
            this.cost = cost;
            this.path = path;
        }

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
