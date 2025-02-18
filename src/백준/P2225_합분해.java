package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2225_합분해 {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1]; // 숫자 k번 사용해서 N의 숫자를 만드는 경우의 수


        // 숫자 i번사용
        for (int i = 1; i <= K; i++) {
            dp[i][0] = 1;
            // j 숫자를 만듬
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        System.out.println(dp[K][N]);
    }
}
