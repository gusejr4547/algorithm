package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 정렬로 사전순 가장 먼저 오는거 구할 수 없다.

public class P22870_산책_large {
    static int N, M;
    static List<Node>[] adj;
    static long[] distanceS, distanceE;
    static boolean[] visit;

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
            int C = Integer.parseInt(st.nextToken());
            adj[A].add(new Node(B, C));
            adj[B].add(new Node(A, C));
        }
        for (int i = 0; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        distanceS = new long[N + 1];
        distanceE = new long[N + 1];
        Arrays.fill(distanceS, Long.MAX_VALUE);
        Arrays.fill(distanceE, Long.MAX_VALUE);

        dijkstra(S, distanceS);
        dijkstra(E, distanceE);

        long answer = distanceS[E];
        // 사전순 먼저 오는 경로 찾기
        // S -> E 경로의 지나간 곳을 지워야함
        erasePath(S, E);
//        System.out.println(Arrays.toString(visit));

        Arrays.fill(distanceE, Long.MAX_VALUE);
        dijkstra(E, distanceE);
        answer += distanceE[S];

        System.out.println(answer);
    }

    private static void erasePath(int start, int end) {
        int prev = start;

        while (start != end) {
            int min = Integer.MAX_VALUE;
            for (Node next : adj[start]) {
                if (prev == next.num) continue;
                if (distanceS[start] + next.cost + distanceE[next.num] == distanceS[end]) {
                    min = Math.min(min, next.num);
                }
            }
            prev = start;
            start = min;
            if (start != end) visit[start] = true;
        }

    }

    // 가중치 간선은 다익스트라
    public static void dijkstra(int start, long[] distance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNum = curr.num;
            long currCost = curr.cost;

            if (distance[currNum] < currCost) continue;

            for (Node next : adj[currNum]) {
                if (visit[next.num]) continue;
                if (distance[next.num] > currCost + next.cost) {
                    distance[next.num] = currCost + next.cost;
                    pq.offer(new Node(next.num, distance[next.num]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        long cost;

        public Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
