package 코드트리;

import java.util.Scanner;

public class 동일하게_분할 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        // Please write your code here.
        boolean[][] dp = new boolean[n+1][sum+1];
        dp[0][0] = true;

        for(int i=1; i<=n; i++){
            int num = arr[i-1];
            for(int j=0; j <= sum; j++){
                if(j>=num)
                    dp[i][j] |= dp[i-1][j-num];

                dp[i][j] |= dp[i-1][j];
            }
        }

        if(sum % 2 == 0){
            System.out.println(dp[n][sum/2] ? "Yes" : "No");
        }else{
            System.out.println("No");
        }
    }
}
