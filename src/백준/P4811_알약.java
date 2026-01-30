package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P4811_알약 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            long[][] dp = new long[N + 1][N + 1];
//            for (int i = 0; i < N; i++) {
//                Arrays.fill(dp, -1);
//            }

            find(N, 0, dp);
            sb.append(dp[N][0]).append('\n');
        }
        System.out.println(sb);
    }

    private static long find(int full, int half, long[][] dp) {
        if (full == 0 && half == 0) {
            return 1;
        }

        if (dp[full][half] != 0) {
            return dp[full][half];
        }

        // full 선택
        if (full >= 1) {
            dp[full][half] += find(full - 1, half + 1, dp);
        }

        // half 선택
        if (half >= 1) {
            dp[full][half] += find(full, half - 1, dp);
        }

        return dp[full][half];
    }
}
