package 코드트리;

import java.util.Scanner;

public class 신전_탐험하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] data = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            data[i][0] = sc.nextInt();
            data[i][1] = sc.nextInt();
            data[i][2] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            for (int d = 0; d < 3; d++) {
                for (int pd = 0; pd < 3; pd++) {
                    if (d != pd) {
                        dp[i][d] = Math.max(dp[i][d], dp[i - 1][pd] + data[i][d]);
                    }
                }
            }
        }

        int answer = 0;
        for (int d = 0; d < 3; d++) {
            answer = Math.max(answer, dp[N][d]);
        }

        System.out.println(answer);
    }
}
