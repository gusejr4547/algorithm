package 코드트리;

import java.util.Scanner;

public class _1_2_5_더하기 {
    static final int MOD = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i=1; i<=n; i++){
            if(i >= 1){
                dp[i] = (dp[i] + dp[i-1]) % MOD;
            }
            if(i >= 2){
                dp[i] = (dp[i] + dp[i-2]) % MOD;
            }
            if(i >= 5){
                dp[i] = (dp[i] + dp[i-5]) % MOD;
            }
        }

        System.out.println(dp[n]);
    }
}
