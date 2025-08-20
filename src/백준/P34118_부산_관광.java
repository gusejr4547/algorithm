package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class P34118_부산_관광 {
    static int N;
    static String A, B;
    static int[] ticketDayMask = {0, (1 << 1) - 1, (1 << 3) - 1, (1 << 5) - 1, (1 << 4) - 1};
    static int[] ticketPrice;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = br.readLine();
        B = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ticketPrice = new int[5];
        for (int i = 1; i <= 4; i++) {
            ticketPrice[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][1 << 5][1 << 5]; // 티켓 기간 최대 5일
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int day, int validA, int validB) {
        if (day == N) {
            return 0;
        }

        // day 지남 처리
        validA = validA >> 1;
        validB = validB >> 1;

        if (dp[day][validA][validB] != -1) {
            return dp[day][validA][validB];
        }

        boolean travelA = A.charAt(day) == '1';
        boolean travelB = B.charAt(day) == '1';

        int price = Integer.MAX_VALUE;

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                int nvA = validA | ticketDayMask[i];
                int nvB = validB | ticketDayMask[j];
                // A여행하는데 티켓없음 || B여행하는데 티켓없음
                if ((travelA && (nvA == 0)) || (travelB && nvB == 0)) {
                    continue;
                }

                int cost = ticketPrice[i] + ticketPrice[j];
                price = Math.min(price, dfs(day + 1, nvA, nvB) + cost);
            }
        }

        // 묶음권
        int nvA = validA | ticketDayMask[4];
        int nvB = validB | ticketDayMask[4];
        if (!(travelA && (nvA == 0)) && !(travelB && (nvB == 0))) {
            price = Math.min(price, dfs(day + 1, nvA, nvB) + ticketPrice[4]);
        }

        return dp[day][validA][validB] = price;
    }
}
