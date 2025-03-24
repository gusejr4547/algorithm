package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P9465_스티커 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[2][n];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxValue = 0;
            int[][] dp = new int[2][n];
            // 첫번째
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];
            maxValue = Math.max(dp[0][0], dp[1][0]);

            for (int j = 1; j < n; j++) {
                if (j == 1) {
                    dp[0][j] = dp[1][0] + map[0][j];
                    dp[1][j] = dp[0][0] + map[1][j];
                    maxValue = Math.max(maxValue, Math.max(dp[0][j], dp[1][j]));
                } else {
                    dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + map[0][j];
                    dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + map[1][j];
                    maxValue = Math.max(maxValue, Math.max(dp[0][j], dp[1][j]));
                }
            }

            System.out.println(maxValue);
        }
    }
}
