package 코드트리;

import java.util.Scanner;

public class 신전_탐험하기_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int[][] dp = new int[N][M];
        for (int j = 0; j < M; j++) {
            dp[0][j] = a[0][j];
        }

        for (int i = 1; i < N; i++) {
            for (int cur = 0; cur < M; cur++) {
                for (int prev = 0; prev < M; prev++) {
                    if (cur != prev) {
                        dp[i][cur] = Math.max(dp[i][cur], dp[i - 1][prev] + a[i][cur]);
                    }
                }
            }
        }

        int answer = 0;
        for (int j = 0; j < M; j++) {
            answer = Math.max(answer, dp[N - 1][j]);
        }

        System.out.println(answer);
    }
}
