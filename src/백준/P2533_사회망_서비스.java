package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.
// 트리 + dp
public class P2533_사회망_서비스 {
    static int N;
    static int[][] dp;
    static boolean[] visit;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        dp = new int[N + 1][2];
        visit = new boolean[N + 1];
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    private static void dfs(int node) {
        visit[node] = true;

        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int next : adj[node]) {
            if (visit[next]) continue;
            dfs(next);
            // node가 얼리어답터 아님
            dp[node][0] += dp[next][1];
            // node가 얼리어답터
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
