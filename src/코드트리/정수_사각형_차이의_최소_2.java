package 코드트리;

import java.util.Scanner;

public class 정수_사각형_차이의_최소_2 {
    static final int MAX = 99999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        int answer = MAX;

        for (int low = 1; low <= 100; low++) {
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = MAX;
                }
            }

            if (grid[0][0] < low) {
                continue;
            }
            dp[0][0] = grid[0][0];

            for (int i = 1; i < n; i++) {
                if (grid[i][0] < low)
                    break;

                dp[i][0] = Math.max(dp[i - 1][0], grid[i][0]);
            }
            for (int j = 1; j < n; j++) {
                if (grid[0][j] < low)
                    break;

                dp[0][j] = Math.max(dp[0][j - 1], grid[0][j]);
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (grid[i][j] < low || (dp[i - 1][j] == MAX && dp[i][j - 1] == MAX))
                        continue;

                    dp[i][j] = Math.max(Math.min(dp[i - 1][j], dp[i][j - 1]), grid[i][j]);
                }
            }


            if (dp[n - 1][n - 1] == MAX) {
                break;
            }

            // System.out.println("low : " + low + " max : " + dp[n-1][n-1]);
            answer = Math.min(answer, dp[n - 1][n - 1] - low);


        }

        System.out.println(answer);
    }
}
