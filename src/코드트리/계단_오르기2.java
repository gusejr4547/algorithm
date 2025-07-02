package 코드트리;

import java.util.*;

public class 계단_오르기2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n+1];
        for (int i = 1; i <= n; i++) {
            coins[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n+1][4];
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for(int i=0; i<n; i++){
            // 2칸
            for(int j=0; j<4; j++){
                if(dp[i][j] != -1 && i+2 <= n){
                    dp[i+2][j] = Math.max(dp[i+2][j], dp[i][j] + coins[i+2]);
                }
            }
            // 1칸
            for(int j=0; j<3; j++){
                if(dp[i][j] != -1){
                    dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + coins[i+1]);
                }
            }
        }

        int answer = 0;
        for(int j=0; j<4; j++){
            answer = Math.max(answer, dp[n][j]);
        }

        System.out.println(answer);
    }
}
