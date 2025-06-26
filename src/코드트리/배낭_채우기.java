package 코드트리;

import java.util.Scanner;

public class 배낭_채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] w = new int[n+1];
        int[] v = new int[n+1];
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            int weight = w[i];
            int value = v[i];
            for(int j=1; j<=m; j++){
                if(j < weight){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight] + value);
                }
            }
        }
        // for(int[] a : dp){
        //     System.out.println(Arrays.toString(a));
        // }
        System.out.println(dp[n][m]);
    }
}
