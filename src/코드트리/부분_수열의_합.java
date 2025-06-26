package 코드트리;

import java.util.Scanner;

public class 부분_수열의_합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        boolean[] dp = new boolean[m+1];
        dp[0] = true;
        for(int i=0; i<n; i++){
            int num = arr[i];
            for(int sum=m; sum >= num; sum--){
                if(dp[sum-num]){
                    dp[sum] = true;
                }
            }
        }

        System.out.println(dp[m] ? "Yes" : "No");
    }
}
