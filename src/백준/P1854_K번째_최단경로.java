package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1854_K번째_최단경로 {
    static int N, M, K;
    static List<Node>[] adj;
    static PriorityQueue<Long>[] dist;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, c));
        }

        dist = new PriorityQueue[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        dijkstra(1);

        for (int i = 1; i <= N; i++) {
            if (dist[i].size() == K) {
                System.out.println(dist[i].peek());
            } else {
                System.out.println(-1);
            }
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost > 0 ? 1 : -1);
        pq.offer(new Node(start, 0));
        dist[start].offer(0L);

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int idx = curr.idx;

            for (Node next : adj[idx]) {
                long cost = curr.cost + next.cost;
                // 방문 체크, dist pq에 K개가 차면 root 값 비교해서 작으면 교체, 크면 다음 진행
                if (dist[next.idx].size() < K) {
                    dist[next.idx].offer(cost);

                    pq.offer(new Node(next.idx, cost));
                } else if (dist[next.idx].peek() > cost) {
                    dist[next.idx].poll();
                    dist[next.idx].offer(cost);

                    pq.offer(new Node(next.idx, cost));
                }
            }
        }
    }

    static class Node {
        int idx;
        long cost;

        public Node() {
        }

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", cost=" + cost +
                    '}';
        }
    }
}
