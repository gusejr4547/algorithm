package 코드트리.HSAT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 자동차_전시장 {
    static int N, M, K;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
        }
        int[] startPoints = new int[K];
        for (int i = 0; i < K; i++) {
            startPoints[i] = sc.nextInt();
        }

        int[][] dist = new int[K][];
        // 다익스트라
        for (int i = 0; i < K; i++) {
            int start = startPoints[i];
            dist[i] = dijkstra(start);
//            System.out.println(Arrays.toString(dist[i]));
        }

        int[] maxDist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < K; j++) {
                maxDist[i] = Math.max(maxDist[i], dist[j][i]);
            }
        }
//        System.out.println(Arrays.toString(maxDist));
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            answer = Math.min(answer, maxDist[i]);
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int next : adj[cur.num]) {
                if (dist[next] > cur.cost + 1) {
                    dist[next] = cur.cost + 1;

                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }

    private static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
