package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P11657_타임머신 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> adj = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.add(new Edge(a, b, c));
        }

        // 밸만 포드 알고리즘
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        boolean isCycle = false;
        for (int i = 0; i < N; i++) {
            for (Edge edge : adj) {
                if (dist[edge.s] == Integer.MAX_VALUE) {
                    continue;
                }

                if (dist[edge.e] > dist[edge.s] + edge.cost) {
                    dist[edge.e] = dist[edge.s] + edge.cost;

                    // 마지막에도 변경이 일어나면 사이클 존재
                    if (i == N - 1) {
                        isCycle = true;
                    }
                }
            }
        }

        if (isCycle) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append('\n');
            }

            System.out.println(sb);
        }
    }


    private static class Edge {
        int s, e, cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }
}
