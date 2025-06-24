package 코드트리;

import java.util.Scanner;

public class 정수_사각형_최소_합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();
        // Please write your code here.
        int[][] dp = new int[n][n];
        dp[0][n-1] = matrix[0][n-1];
        for(int j=n-2; j>=0; j--){
            dp[0][j] = dp[0][j+1] + matrix[0][j];
        }
        for(int i=1; i<n; i++){
            dp[i][n-1] = dp[i-1][n-1] + matrix[i][n-1];
        }

        for(int i=1; i<n; i++){
            for(int j=n-2; j>=0; j--){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j+1]) + matrix[i][j];
            }
        }

        System.out.println(dp[n-1][0]);
    }
}
