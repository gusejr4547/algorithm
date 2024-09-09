package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11058_크리보드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];

        // 선택 + 복사 + 붙여넣기 3번 무조건 필요함
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i >= 7) {
                // 붙여넣기 횟수
                for (int j = 1; j <= 3; j++) {
                    dp[i] = Math.max(dp[i], dp[i - (j + 2)] + (dp[i - (j + 2)] * j));
                }
            }
        }

        System.out.println(dp[N]);
    }
}
