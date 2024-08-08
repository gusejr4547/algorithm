package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1562_계단_수 {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 0~9까지 숫자 모두 등장
        int[][][] dp = new int[N][10][1 << 10];
        for (int num = 1; num <= 9; num++) {
            dp[0][num][1 << num]++;
        }

        for (int i = 1; i < N; i++) {
            for (int num = 0; num <= 9; num++) {
                for (int bit = 0; bit < 1 << 10; bit++) {
                    if (num == 0) {
                        dp[i][num][bit | 1 << num] = (dp[i][num][bit | 1 << num] + dp[i - 1][num + 1][bit]) % MOD;
                    } else if (num == 9) {
                        dp[i][num][bit | 1 << num] = (dp[i][num][bit | 1 << num] + dp[i - 1][num - 1][bit]) % MOD;
                    } else {
                        dp[i][num][bit | 1 << num] = (dp[i][num][bit | 1 << num] + (dp[i - 1][num - 1][bit] + dp[i - 1][num + 1][bit]) % MOD) % MOD;
                    }
                }
            }
        }

        int sum = 0;
        for (int num = 0; num <= 9; num++) {
            sum = (sum + dp[N - 1][num][(1 << 10) - 1]) % MOD;
        }

//        System.out.println(Arrays.toString(dp[N - 1][0]));

        System.out.println(sum % MOD);
    }
}
