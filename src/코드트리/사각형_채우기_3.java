package 코드트리;

import java.util.Scanner;

public class 사각형_채우기_3 {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        long[] dp = new long[1001];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = dp[2] * 2 + dp[1] * 3 + 2;

        for(int i=4; i<=n; i++){
            dp[i] = (dp[i-1] * 2 + dp[i-2] * 3) % MOD;
            for(int j = i-3; j>=0; j--){
                dp[i] = (dp[i] + dp[j]*2) % MOD;
            }
        }

        System.out.println(dp[n]);
    }
}
