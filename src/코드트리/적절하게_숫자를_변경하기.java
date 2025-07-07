package 코드트리;

import java.util.Scanner;

public class 적절하게_숫자를_변경하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][][] dp = new int[N][M + 1][5];
        dp[0][0][seq[0]] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int num = 1; num <= 4; num++) {
                    int sim = seq[i] == num ? 1 : 0;
                    for (int prev = 1; prev <= 4; prev++) {
                        if (prev != num && j >= 1) {
                            dp[i][j][num] = Math.max(dp[i][j][num], dp[i - 1][j - 1][prev] + sim);
                        }
                        if (prev == num) {
                            dp[i][j][num] = Math.max(dp[i][j][num], dp[i - 1][j][prev] + sim);
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int j = 0; j <= M; j++) {
            for (int num = 1; num <= 4; num++) {
                answer = Math.max(answer, dp[N - 1][j][num]);
            }
        }

        System.out.println(answer);
    }
}
