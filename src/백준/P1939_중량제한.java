package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1939_중량제한 {
    static int N, M, start, end;
    static List<Edge>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = 0;
        int l = 0;
        int h = 1_000_000_000;
        while (l <= h) {
            int mid = (l + h) / 2;

            if (canMove(start, end, mid)) {
                l = mid + 1;
                answer = mid;
            } else {
                h = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canMove(int start, int end, int weight) {
        boolean[] visit = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == end) {
                return true;
            }

            if (visit[cur]) {
                continue;
            }
            visit[cur] = true;

            for (Edge next : adjList[cur]) {
                if (next.cost >= weight) {
                    queue.offer(next.node);
                }
            }
        }

        return false;
    }

    private static class Edge {
        int node, cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
