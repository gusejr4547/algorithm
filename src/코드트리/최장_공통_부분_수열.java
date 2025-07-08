package 코드트리;

import java.util.Scanner;

public class 최장_공통_부분_수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] A = sc.next().toCharArray();
        char[] B = sc.next().toCharArray();
        int n = A.length;
        int m = B.length;
        // Please write your code here.
        int[][] dp = new int[n + 1][m + 1]; // super subsequence 길이

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
