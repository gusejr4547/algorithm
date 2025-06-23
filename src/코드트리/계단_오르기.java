package 코드트리;

import java.util.Scanner;

public class 계단_오르기 {
    static final int MOD = 10007;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        dp = new int[n+1];
        int answer = goStairs(n);
        System.out.println(answer);
    }

    private static int goStairs(int floor){
        if(floor < 0){
            return 0;
        }

        if(floor == 0){
            return dp[floor] = 1;
        }

        if(dp[floor] != 0){
            return dp[floor];
        }

        return dp[floor] = (goStairs(floor-2) + goStairs(floor-3)) % MOD;
    }
}
