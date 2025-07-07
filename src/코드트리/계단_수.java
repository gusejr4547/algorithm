package 코드트리;

import java.util.Scanner;

public class 계단_수 {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[][] dp = new int[n+1][10]; // n번째에 j를 선택했을 때 경우의수
        for(int j=1; j<=9; j++){
            dp[1][j] = 1;
        }
        for(int i=2; i<=n; i++){
            for(int j=0; j<=9; j++){
                if(j>=1){
                    dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % MOD;
                }
                if(j<=8){
                    dp[i][j] = (dp[i][j] + dp[i-1][j+1]) % MOD;
                }
            }
        }

        int answer = 0;
        for(int j=0; j<=9; j++){
            answer = (answer + dp[n][j]) % MOD;
        }

        System.out.println(answer);
    }
}
