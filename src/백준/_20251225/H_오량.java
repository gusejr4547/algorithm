package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H_오량 {
    // 최종적으로 가지고 있는 노트 개수가 주어짐
    // 노트는 전부 다름
    // 학생이 가지고 있는 노트 경우의 수?

    // T<=1000, N<=2000

    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 2000개중에 0 ~ 2000개 뽑는 경우의 수 미리 계산
        long[][] dp = new long[2001][2001];
        for (int n = 0; n <= 2000; n++) {
            dp[n][0] = 1;
            dp[n][n] = 1;
            for (int k = 1; k < n; k++) {
                dp[n][k] = (dp[n - 1][k - 1] + dp[n - 1][k]) % MOD;
            }
        }

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int total = N;
            long answer = 1;

            for (int i = 0; i < N; i++) {
                int A = Integer.parseInt(st.nextToken());
                if (A > 0) {
                    // total개 중에 A개를 뽑는 경우의 수
                    answer = (answer * dp[total][A]) % MOD;
                }

                total = total - A;
            }

            sb.append(answer).append("\n");
        }
        sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }
}
