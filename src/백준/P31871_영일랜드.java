package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P31871_영일랜드 {
    static final long INF = 999999999;
    static int N, M;
    static long[][] dp, adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new long[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 최대값 구하기
            adj[u][v] = Math.max(adj[u][v], d);
        }

        // 정문에서 출발해 모든 놀이기구를 한 번씩만 탑승하고 정문으로 돌아오는 최장 시간
        // TSP를 최대 값으로 만드는거?
        dp = new long[N + 1][1 << (N + 1)];
        long answer = tsp(0, 1);
        System.out.println(answer < 0 ? -1 : answer);
    }

    private static long tsp(int node, int visit) {
        if (visit == (1 << (N + 1)) - 1) {
            if (adj[node][0] == 0) {
                return -INF;
            }
            return adj[node][0];
        }

        if (dp[node][visit] != 0) {
            return dp[node][visit];
        }

        dp[node][visit] = -INF;
        for (int i = 1; i <= N; i++) {
            if ((visit & (1 << i)) != 0) {
                continue;
            }
            if (adj[node][i] == 0) {
                continue;
            }

            dp[node][visit] = Math.max(dp[node][visit], tsp(i, visit | (1 << i)) + adj[node][i]);
        }

        return dp[node][visit];
    }
}
