package 코드트리;

import java.util.*;

public class 축구와_야구 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] soccer = new int[N + 1];
        int[] baseball = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            soccer[i] = sc.nextInt();
            baseball[i] = sc.nextInt();
        }
        // Please write your code here.
        int soccerPlayer = 11;
        int baseballPlayer = 9;
        int[][][] dp = new int[N + 1][soccerPlayer + 1][baseballPlayer + 1];


        for (int i = 1; i <= N; i++) {
            for (int s = 0; s <= soccerPlayer; s++) {
                for (int b = 0; b <= baseballPlayer; b++) {
                    // 선택안함
                    dp[i][s][b] = Math.max(dp[i][s][b], dp[i - 1][s][b]);
                    // 축구
                    if (s >= 1) {
                        dp[i][s][b] = Math.max(dp[i][s][b], dp[i - 1][s - 1][b] + soccer[i]);
                    }
                    // 야구
                    if (b >= 1) {
                        dp[i][s][b] = Math.max(dp[i][s][b], dp[i - 1][s][b - 1] + baseball[i]);
                    }
                }
            }
        }

        System.out.println(dp[N][soccerPlayer][baseballPlayer]);
    }
}
