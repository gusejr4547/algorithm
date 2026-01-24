package 백준._20260124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_광역기_맞으면_서렌하는_게임 {
    // 수직선 위에 있음.
    // 처음 위치는 내 마음대로
    // 1초에 1만큼 움직이는것 가능.
    // L <= 나 <= R 위치에 있으면 ok.
    // 1번은 맞아도 됨
    // 끝까지 살아남는것 가능?

    // 1 <= L <= R <= 1000

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] safe = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            safe[i][0] = L;
            safe[i][1] = R;
        }

        boolean[][][] dp = new boolean[N][1002][2];

        // 초기 위치 0~1000까지 L,R 범위 체크
        for (int i = 0; i <= 1001; i++) {
            if (safe[0][0] <= i && i <= safe[0][1]) {
                dp[0][i][0] = true;
            } else {
                dp[0][i][1] = true;
            }
        }

        for (int time = 1; time < N; time++) {
            for (int place = 1; place <= 1000; place++) {
                // time, place에 위치 가능함?
                // 안전구역?
                if (safe[time][0] <= place && place <= safe[time][1]) {
                    // 보호막 사용 x
                    dp[time][place][0] = dp[time - 1][place - 1][0] || dp[time - 1][place][0] || dp[time - 1][place + 1][0];
                    // 보호막 사용 o
                    dp[time][place][1] = dp[time - 1][place - 1][1] || dp[time - 1][place][1] || dp[time - 1][place + 1][1];
                }
                // 안전구역 아님 => 보호막 써야함
                else {
                    dp[time][place][1] = dp[time - 1][place - 1][0] || dp[time - 1][place][0] || dp[time - 1][place + 1][0];
                }
            }
        }

        // N-1 시간에 true인게 있으면 됨
        boolean answer = false;
        for (int p = 0; p <= 1000; p++) {
            if (dp[N - 1][p][0] || dp[N - 1][p][1]) {
                answer = true;
                break;
            }
        }

        System.out.println(answer ? "World Champion" : "Surrender");
    }
}
