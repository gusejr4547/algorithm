package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1865_웜홀 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }


            sb.append(bellmanFord(N, edges) ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord(int N, List<Edge> edges) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 0);

        for (int i = 0; i < N; i++) {
            // 모든 간선에 대해 계산
            for (Edge edge : edges) {
                if (dist[edge.to] > dist[edge.from] + edge.time) {
                    dist[edge.to] = dist[edge.from] + edge.time;

                    if (i == N - 1) return true;
                }
            }
        }

        return false;
    }

    static class Edge {
        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}
