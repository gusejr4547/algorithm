package 코드트리;

import java.util.*;

public class 정수_사각형_최솟값의_최대 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.min(dp[0][j - 1], matrix[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]), matrix[i][j]);
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}
