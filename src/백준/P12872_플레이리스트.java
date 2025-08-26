package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 N개의 노래
// 플레이리스트 P개의 노래 선택. 플레이리스트에 같은 노래를 여러번 넣어도 된다.
// 모든 노래를 플레이리스트에 추가해야함.
// 같은 노래를 추가하려면, 사이에 M개의 노래가 있어야한다.

// N <= 100, M <= N <= P <= 100
// 만들 수 있는 플레이리스트의 수?
public class P12872_플레이리스트 {
    static int N, M, P;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        long[][] dp = new long[P + 1][N + 1]; // dp[i][j] 길이가 i개 플레이리스트, j개의 곡 사용
        dp[0][0] = 1;

        for (int list = 1; list <= P; list++) {
            for (int i = 1; i <= N; i++) {
                // 새로운 곡 추가
                dp[list][i] = dp[list][i] + dp[list - 1][i - 1] * (N - (i - 1));
                dp[list][i] %= MOD;

                // 중복 곡 추가
                if (i > M) {
                    dp[list][i] = dp[list][i] + dp[list - 1][i] * (i - M);
                    dp[list][i] %= MOD;
                }
            }
        }

        System.out.println(dp[P][N]);
    }
}
