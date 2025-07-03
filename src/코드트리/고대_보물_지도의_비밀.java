package 코드트리;

import java.util.*;

public class 고대_보물_지도의_비밀 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(dp[i], -100000001);
        }

        int answer = Integer.MIN_VALUE;

        for(int i=1; i<=N; i++){
            dp[i][0] = 0;
            if(arr[i] >= 0){
                for(int j=0; j<=K; j++){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + arr[i]);
                    answer = Math.max(answer, dp[i][j]);
                }
            }else{
                for(int j=1; j<=K; j++){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + arr[i]);
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
        // for(int[] d : dp){
        //     System.out.println(Arrays.toString(d));
        // }

        System.out.println(answer);
    }
}
