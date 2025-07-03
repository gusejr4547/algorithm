package 코드트리;

import java.util.Scanner;

public class 둘_중_하나_잘_고르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] red = new int[n * 2 + 1];
        int[] blue = new int[n * 2 + 1];
        for (int i = 1; i <= 2 * n; i++) {
            red[i] = sc.nextInt();
            blue[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[2 * n + 1][2 * n + 1];

        for (int i = 1; i <= 2 * n; i++) {
            for (int redCnt = 0; redCnt <= i; redCnt++) {
                int blueCnt = i - redCnt;
                // 빨강선택
                if (redCnt > 0) {
                    dp[redCnt][blueCnt] = Math.max(dp[redCnt][blueCnt], dp[redCnt - 1][blueCnt] + red[i]);
                }

                // 파랑선택
                if (blueCnt > 0) {
                    dp[redCnt][blueCnt] = Math.max(dp[redCnt][blueCnt], dp[redCnt][blueCnt - 1] + blue[i]);
                }
            }
        }

        System.out.println(dp[n][n]);
    }
}
