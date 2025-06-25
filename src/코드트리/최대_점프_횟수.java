package 코드트리;

import java.util.*;

public class 최대_점프_횟수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int len = arr[i];
            if (dp[i] == -1) {
                continue;
            }
            for (int k = 1; k <= len && i + k < n; k++) {
                dp[i + k] = Math.max(dp[i + k], dp[i] + 1);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
