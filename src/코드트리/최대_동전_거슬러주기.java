package 코드트리;

import java.util.*;

public class 최대_동전_거슬러주기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int p = coin[i];
            for (int sum = p; sum <= m; sum++) {
                if (dp[sum - p] != -1) {
                    dp[sum] = Math.max(dp[sum], dp[sum - p] + 1);
                }
            }
        }

        System.out.println(dp[m] == 0 ? -1 : dp[m]);
    }
}
