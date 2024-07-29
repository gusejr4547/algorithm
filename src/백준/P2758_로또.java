package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1부터 m까지 숫자 중에 n개의 수를 고르는 로또
// 각 숫자는 이전에 고른 수보다 적어도 2배가 되도록 고르기
public class P2758_로또 {
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        dp = new long[11][2001];
        for (int i = 1; i <= 2000; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= 10; i++) {
            for (int j = 1; j <= 2000; j++) {
                if (dp[i - 1][j] != 0) {
                    for (int k = j * 2; k <= 2000; k++) {
                        dp[i][k] += dp[i - 1][j];
                    }
                }
            }
        }

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long result = 0;
            for (int i = 0; i <= m; i++) {
                result += dp[n][i];
            }

            System.out.println(result);
        }
    }
}
