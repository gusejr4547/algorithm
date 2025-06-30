package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1719_택배 {
    static int N, M;
    static List<Edge>[] adj;

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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, c));
            adj[v].add(new Edge(u, c));
        }

        int[][] answer = new int[N][N];
        for (int start = 1; start <= N; start++) {
            int[] load = dijkstra(start);

            for (int i = 0; i < N; i++) {
                if (i != start - 1) {
                    answer[i][start - 1] = load[i + 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j] != 0 ? answer[i][j] : "-").append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        pq.offer(new Edge(start, 0));

        int[] dist = new int[N + 1];
        int[] load = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        for (int i = 0; i <= N; i++) {
            load[i] = i;
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.node] < cur.weight) {
                continue;
            }

            for (Edge next : adj[cur.node]) {
                if (dist[next.node] > dist[cur.node] + next.weight) {
                    dist[next.node] = dist[cur.node] + next.weight;
                    pq.offer(new Edge(next.node, dist[next.node]));
                    load[next.node] = cur.node;
                }
            }
        }

//        System.out.println(Arrays.toString(load));
        return load;
    }


    private static class Edge {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}
