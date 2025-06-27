package 코드트리;

import java.util.Scanner;

public class 막대기_나누기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            profit[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] dp = new int[n+1];
        for(int len=1; len<=n; len++){
            for(int sub=1; sub<=len; sub++){
                dp[len] = Math.max(dp[len], dp[len-sub] + profit[sub-1]);
            }
        }

        System.out.println(dp[n]);
    }
}
