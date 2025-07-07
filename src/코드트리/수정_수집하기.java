package 코드트리;

import java.util.*;

public class 수정_수집하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        String s = sc.next();
        // Please write your code here.

        int[][][] dp = new int[N + 1][2][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i][0], -1);
            Arrays.fill(dp[i][1], -1);
        }
        dp[0][0][0] = 0;

        for (int i = 1; i <= N; i++) {
            int[] score = new int[2];
            if ('L' == s.charAt(i - 1)) {
                score[0] = 1;
            } else {
                score[1] = 1;
            }
            for (int lr = 0; lr <= 1; lr++) {
                for (int j = 0; j <= K; j++) {
                    if (dp[i - 1][lr][j] != -1) {
                        // 그대로
                        dp[i][lr][j] = Math.max(dp[i][lr][j], dp[i - 1][lr][j] + score[lr]);
                    }

                    if (j >= 1 && dp[i-1][(lr+1) % 2][j - 1] != -1) {
                        // 이동
                        dp[i][lr][j] = Math.max(dp[i][lr][j], dp[i - 1][(lr + 1) % 2][j - 1] + score[lr]);
                    }
                }
            }
        }

        int answer = 0;
        for (int lr = 0; lr <= 1; lr++) {
            for (int j = 0; j <= K; j++) {
                answer = Math.max(answer, dp[N][lr][j]);
            }
        }

        System.out.println(answer);
    }
}
