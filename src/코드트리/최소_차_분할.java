package 코드트리;

import java.util.*;

public class 최소_차_분할 {
    static final int MAX = 100_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        boolean[][] dp = new boolean[n+1][MAX+1]; // n번 수까지 고려했을 때 차이 j를 만들수 있음?
        dp[0][0] = true;
        for(int i=1; i<=n; i++){
            int num = arr[i-1];
            for(int j=0; j<=MAX; j++){
                if(j >= num){
                    dp[i][j] |= dp[i-1][j-num];
                }else{
                    dp[i][j] |= dp[i-1][num-j];
                }

                if(j<=MAX-num){
                    dp[i][j] |= dp[i-1][j+num];
                }
            }
        }

        int answer = 0;
        for(int i=0; i<=MAX; i++){
            if(dp[n][i]){
                answer = i;
                break;
            }
        }

        // for(boolean[] a : dp){
        //     System.out.println(Arrays.toString(a));
        // }
        System.out.println(answer);
    }
}
