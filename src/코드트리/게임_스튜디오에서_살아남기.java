package 코드트리;

import java.util.*;

public class 게임_스튜디오에서_살아남기 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[][][] dp = new int[n + 1][3][3]; // G, B, T를 넣을 수 있는 경우의 수 / dp[i][j][k] i일, j개 B 연속, k개 T 총합
        dp[0][0][0] = 1;


        for (int i = 1; i <= n; i++) {
            // G
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][0][k] = (dp[i][0][k] + dp[i - 1][j][k]) % MOD;
                }
            }

            // B
            for (int j = 1; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - 1][k]) % MOD;
                }
            }


            // T
            for (int j = 0; j < 3; j++) {
                for (int k = 1; k < 3; k++) {
                    dp[i][0][k] = (dp[i][0][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }

        int answer = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                answer = (answer + dp[n][j][k]) % MOD;
            }
        }

        // for(int i=1; i<=n; i++){
        //     System.out.println(Arrays.deepToString(dp[i]));
        // }

        System.out.println(answer);
    }
}
