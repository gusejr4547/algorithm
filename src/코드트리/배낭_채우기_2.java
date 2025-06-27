package 코드트리;

import java.util.Scanner;

public class 배낭_채우기_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] dp = new int[m + 1];

        for (int w = 1; w <= m; w++) {
            for (int i = 0; i < n; i++) {
                if (w >= weight[i]) {
                    dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
                }
            }
        }

        System.out.println(dp[m]);
    }
}
