package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P15989_1_2_3더하기_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            // 1,2,3의 합으로 n을 만들수있는 경우의 수.
            // 수의 순서만 다른것은 같은것으로 친다.
            // 더하는 수는 무조건 오름차순
            // dp[n][1] => n을 만들었는데 마지막에 1을 더한 경우, ...
            int[][] dp = new int[10001][4];

            dp[1][1] = dp[2][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

            for (int i = 4; i <= n; i++) {
                dp[i][1] = dp[i - 1][1];
                dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
                dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
            }

            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
    }
}
