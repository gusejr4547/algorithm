package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P22857_가장_긴_짝수_연속한_부분_수열small {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1]; // N까지, K번지움
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (arr[i] % 2 == 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else if (j != 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        int maxV = Integer.MIN_VALUE;
        for (int i = 0; i <= N; i++) {
            maxV = Math.max(maxV, dp[i][K]);
        }
        System.out.println(maxV);
    }
}
