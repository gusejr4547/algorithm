package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P12784_인하니카_공화국 {
    // N개의 섬. 트리형태로 이어져있음
    // 1번섬 이외 다리가 하나밖에 없는 섬 => 1번을 루트로 하는 트리의 리프노드?
    // 그 섬들로 가는 경로를 차단하려고함. 차단경로마다 d의 비용이 발생
    // 비용을 최소화.

    static List<Edge>[] adj;
    static int[] dp;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            adj = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                adj[u].add(new Edge(v, d));
                adj[v].add(new Edge(u, d));
            }
            visit = new boolean[N + 1];
            dp = new int[N + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dfs(1);

            if(N == 1) System.out.println(0);
            else System.out.println(dp[1]);
        }
    }

    private static void dfs(int cur) {
        visit[cur] = true;

        int total = 0;
        // 다음 노드
        for (Edge edge : adj[cur]) {
            int next = edge.to;
            if (!visit[next]) {
                dfs(next);

                // 다음 노드로 가는 비용
                int cost = edge.d;

                // 현재 edge를 끊을까? 더 들어가서 끊을까?
                total += Math.min(cost, dp[next]);
            }
        }

        dp[cur] = total == 0 ? Integer.MAX_VALUE : total;
    }

    private static class Edge {
        int to, d;

        public Edge(int to, int d) {
            this.to = to;
            this.d = d;
        }
    }
}
