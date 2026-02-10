package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2240_자두나무 {
    // 2개 나무 있다.
    // T초 동안 1초에 한개의 나무에서 자두 떨어짐.
    //
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] drop = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            drop[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[T + 1][W + 1]; // dp[i][j][k] i초에 j번 위치변경 최대개수, j가 짝수이면 1 홀수이면 2

        for (int i = 1; i <= T; i++) {
            int p = drop[i]; // p번에 떨어짐
            // 0번 움직인 경우는 계속 1번에 있는것이다.
            dp[i][0] = dp[i - 1][0] + (p == 1 ? 1 : 0);

            for (int j = 1; j <= W; j++) {
                int my = j % 2 == 0 ? 1 : 2;

                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + (p == my ? 1 : 0);
            }
        }

        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, dp[T][i]);
        }
        System.out.println(answer);
    }
}
