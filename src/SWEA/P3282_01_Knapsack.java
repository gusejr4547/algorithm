package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3282_01_Knapsack {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int V = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                for (int k = 1; k <= K; k++) {
                    if (k < V) {
                        dp[i][k] = dp[i - 1][k];
                    }else{
                        dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - V] + C);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
