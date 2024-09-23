package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1890_점프 {
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0) continue;

                int jumpV = map[i][j];
                if (jumpV == 0) break; // 종료

                if (i + jumpV < N) {
                    dp[i + jumpV][j] += dp[i][j];
                }
                if (j + jumpV < N) {
                    dp[i][j + jumpV] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
