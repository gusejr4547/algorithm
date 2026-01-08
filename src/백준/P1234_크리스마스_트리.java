package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1234_크리스마스_트리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int rC = Integer.parseInt(st.nextToken());
        int gC = Integer.parseInt(st.nextToken());
        int bC = Integer.parseInt(st.nextToken());

        long[] factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        long[][][][] dp = new long[N + 1][rC + 1][gC + 1][bC + 1];
        dp[0][0][0][0] = 1;

        // 10 000 000
        for (int i = 1; i <= N; i++) {
            // i, i/2, i/3 3가지 경우 있다.
            // 이전 상태 탐색
            for (int r = 0; r <= rC; r++) {
                for (int g = 0; g <= gC; g++) {
                    for (int b = 0; b <= bC; b++) {
                        if (dp[i - 1][r][g][b] == 0) {
                            continue;
                        }

                        long cur = dp[i - 1][r][g][b];

                        // i r,g,b 중 1개 선택
                        if (r + i <= rC) {
                            dp[i][r + i][g][b] += cur;
                        }
                        if (g + i <= gC) {
                            dp[i][r][g + i][b] += cur;
                        }
                        if (b + i <= bC) {
                            dp[i][r][g][b + i] += cur;
                        }

                        // i/2 r,g,b 중 2개 선택
                        // 2로 나누어져야함
                        if (i % 2 == 0) {
                            int half = i / 2;

                            // 색 순서 따라서 몇가지 경우가 있나?
                            long way = factorial[i] / (factorial[half] * factorial[half]);

                            if (r + half <= rC && g + half <= gC) {
                                dp[i][r + half][g + half][b] += (cur * way);
                            }
                            if (r + half <= rC && b + half <= bC) {
                                dp[i][r + half][g][b + half] += (cur * way);
                            }
                            if (g + half <= gC && b + half <= bC) {
                                dp[i][r][g + half][b + half] += (cur * way);
                            }
                        }

                        // i/3 r,g,b 전부 사용
                        // 3으로 나누어져야함
                        if (i % 3 == 0) {
                            int third = i / 3;

                            long way = factorial[i] / (factorial[third] * factorial[third] * factorial[third]);

                            if (r + third <= rC && g + third <= gC && b + third <= bC) {
                                dp[i][r + third][g + third][b + third] += (cur * way);
                            }
                        }
                    }
                }
            }
        }

        long answer = 0;
        for (int r = 0; r <= rC; r++) {
            for (int g = 0; g <= gC; g++) {
                for (int b = 0; b <= bC; b++) {
                    answer += dp[N][r][g][b];
                }
            }
        }

        System.out.println(answer);
    }
}
