package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17404_RGB거리_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N][3];
            // 1번째 집 선택
            for (int color = 0; color < 3; color++) {
                if (color == firstColor) {
                    dp[0][color] = cost[0][color];
                } else {
                    dp[0][color] = Integer.MAX_VALUE;
                }
            }

            // 2번째 ~ N번째
            for (int house = 1; house < N; house++) {
                dp[house][0] = Math.min(dp[house - 1][1], dp[house - 1][2]) == Integer.MAX_VALUE
                        ? Integer.MAX_VALUE : (Math.min(dp[house - 1][1], dp[house - 1][2]) + cost[house][0]);
                dp[house][1] = Math.min(dp[house - 1][0], dp[house - 1][2]) == Integer.MAX_VALUE
                        ? Integer.MAX_VALUE : (Math.min(dp[house - 1][0], dp[house - 1][2]) + cost[house][1]);
                dp[house][2] = Math.min(dp[house - 1][0], dp[house - 1][1]) == Integer.MAX_VALUE
                        ? Integer.MAX_VALUE : (Math.min(dp[house - 1][0], dp[house - 1][1]) + cost[house][2]);
            }

            // 마지막 firstColor와 같은 색은 제외
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    result = Math.min(result, dp[N - 1][lastColor]);
                }
            }
        }

        System.out.println(result);
    }
}
