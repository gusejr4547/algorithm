package 백준._20260103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A_트윈_타워_Easy {
    // 타워 건설 비용 1, i위치 지으면 tower[i] 만큼 가치
    // i, i+1 둘다 타워 있으면 통로를 만들어서 트윈타워 만들수있다. load[i] 만큼 가치를 얻음. 비용x
    // 하나의 타워는 하나의 통로만... i,i+1 있으면 i+1,i+2 못함.

    // 첫 번째 줄부터 N개의 줄에 걸쳐 i번째 줄에는 비용 i로 얻을 수 있는 최대 가치를 출력하라.

    // N <= 2000, 건물가치,통로가치 <= 1 000 000 000
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] tower = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }
        long[] load = new long[N - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            load[i] = Integer.parseInt(st.nextToken());
        }

        // [i][j][state] = 지금까지 최대 value. i는 건물위치, j는 건물 건설 횟수
        long[][][] dp = new long[N][N + 1][3]; // 0 : i에 건물x, 1 : i에 건물o, 2 : i에 건물o i-1과 통로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        // 0번 위치
        dp[0][0][0] = 0; // 타워 없는 상태
        dp[0][1][1] = tower[0]; // 타워 있는 상태

        for (int i = 1; i < N; i++) {
            // i번 위치
            for (int j = 0; j <= i + 1; j++) {
                // 타워 x
                long maxValue = -1;
                if (dp[i - 1][j][0] != -1) {
                    maxValue = Math.max(maxValue, dp[i - 1][j][0]);
                }
                if (dp[i - 1][j][1] != -1) {
                    maxValue = Math.max(maxValue, dp[i - 1][j][1]);
                }
                if (dp[i - 1][j][2] != -1) {
                    maxValue = Math.max(maxValue, dp[i - 1][j][2]);
                }
                dp[i][j][0] = maxValue;

                // 타워 o
                if (j >= 1) {
                    // 연결 x
                    maxValue = -1;
                    if (dp[i - 1][j - 1][0] != -1) {
                        maxValue = Math.max(maxValue, dp[i - 1][j - 1][0]);
                    }
                    if (dp[i - 1][j - 1][1] != -1) {
                        maxValue = Math.max(maxValue, dp[i - 1][j - 1][1]);
                    }
                    if (dp[i - 1][j - 1][2] != -1) {
                        maxValue = Math.max(maxValue, dp[i - 1][j - 1][2]);
                    }
                    // 앞에 상태 전부 불가능이면 갱신 x
                    if (maxValue != -1) {
                        dp[i][j][1] = maxValue + tower[i];
                    }


                    // 연결 o
                    if (dp[i - 1][j - 1][1] != -1) {
                        dp[i][j][2] = dp[i - 1][j - 1][1] + tower[i] + load[i - 1];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            long answer = -1;
            answer = Math.max(answer, dp[N - 1][i][0]);
            answer = Math.max(answer, dp[N - 1][i][1]);
            answer = Math.max(answer, dp[N - 1][i][2]);

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }
}
