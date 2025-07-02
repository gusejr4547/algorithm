package 코드트리;

import java.util.*;

public class 숫자_전쟁 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        // Please write your code here.

        // b의 합이 최대가 되도록

        int[][] dp = new int[n + 1][n + 1]; // dp[i][j] a가 i번째 b가 j번째 일때 b합 최대값
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) continue;

                if (a[i] < b[j]) {
                    // A가 이김
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                }
                if (a[i] > b[j]) {
                    // B가 이김
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + b[j]);
                }

                // 버림
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
            }
        }

        int answer = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] != -1) {
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer);
    }
}
