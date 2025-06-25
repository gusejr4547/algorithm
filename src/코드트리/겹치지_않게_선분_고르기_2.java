package 코드트리;

import java.util.*;

public class 겹치지_않게_선분_고르기_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(segments, (o1, o2) -> o1[1] - o2[1]);

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (segments[j][1] < segments[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                    break;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
