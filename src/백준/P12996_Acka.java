package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P12996_Acka {
    static int MOD = 1_000_000_007;
    static int S, dotorya, kesakiyo, hongjun7;
    static long[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        dotorya = Integer.parseInt(st.nextToken());
        kesakiyo = Integer.parseInt(st.nextToken());
        hongjun7 = Integer.parseInt(st.nextToken());

        dp = new long[S + 1][dotorya + 1][kesakiyo + 1][hongjun7 + 1];

        for (int i = 0; i <= S; i++) {
            for (int j = 0; j <= dotorya; j++) {
                for (int k = 0; k <= kesakiyo; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        System.out.println(recur(0, 0, 0, 0));

    }

    private static long recur(int songIdx, int a, int b, int c) {
        if (songIdx == S) {
            if (a == dotorya && b == kesakiyo && c == hongjun7) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[songIdx][a][b][c] != -1) {
            return dp[songIdx][a][b][c];
        }

        long result = 0;
        // 곡 부를 사람을 선택하는 7가지 방법
        if (a < dotorya) {
            result = (result + recur(songIdx + 1, a + 1, b, c)) % MOD;
        }
        if (b < kesakiyo) {
            result = (result + recur(songIdx + 1, a, b + 1, c)) % MOD;
        }
        if (c < hongjun7) {
            result = (result + recur(songIdx + 1, a, b, c + 1)) % MOD;
        }
        if (a < dotorya && b < kesakiyo) {
            result = (result + recur(songIdx + 1, a + 1, b + 1, c)) % MOD;
        }
        if (a < dotorya && c < hongjun7) {
            result = (result + recur(songIdx + 1, a + 1, b, c + 1)) % MOD;
        }
        if (b < kesakiyo && c < hongjun7) {
            result = (result + recur(songIdx + 1, a, b + 1, c + 1)) % MOD;
        }
        if (a < dotorya && b < kesakiyo && c < hongjun7) {
            result = (result + recur(songIdx + 1, a + 1, b + 1, c + 1)) % MOD;
        }

        return dp[songIdx][a][b][c] = result;
    }
}
