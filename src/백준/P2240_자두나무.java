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

        int[][][] dp = new int[T + 1][W + 1][2]; // dp[i][j][k] i초에 j번 위치변경 k에 위치해있을때 최대개수
        for (int i = 0; i <= T; i++) {
            for (int j = 0; j <= W; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        // 처음은 0에 위치
        dp[0][0][0] = 0;

        for (int i = 1; i <= T; i++) {
            int p = drop[i] - 1; // p번에 떨어짐
            for (int j = 0; j <= W; j++) {
                // 0
                if (j != 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1]);
                } else {
                    dp[i][j][0] = dp[i - 1][j][0];
                }

                // 1
                if (j != 0) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0]);
                } else {
                    dp[i][j][1] = dp[i - 1][j][1];
                }

                if (dp[i][j][p] != -1) {
                    dp[i][j][p] += 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, Math.max(dp[T][i][0], dp[T][i][1]));
        }
        System.out.println(answer);
    }
}
