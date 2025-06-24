package 코드트리;

import java.util.Scanner;

public class 서로_다른_BST_개수_세기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[] dp = new int[20];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            // dp[i] = dp[i-1]*2;
            for(int j=0; j<i; j++){
                dp[i] += dp[j] * dp[i-1-j];
            }
        }

        System.out.println(dp[n]);
    }
}
