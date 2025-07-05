package 코드트리;

import java.util.*;

public class M개의_구간을_선택하기 {
    static int INF = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], -INF);
        }
        dp[0][0] = 0;


        for(int i=1; i<=n; i++){
            dp[i][0] = 0;

            // 연속 선택하기
            for(int j=1; j<=m; j++){
                if(dp[i-1][j] != -INF){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + arr[i]);
                }
            }

            // 새로 구간 만들기
            // j=1
            dp[i][1] = Math.max(dp[i][1], dp[i-1][0] + arr[i]);
            // j>1 앞에 구간이 있어야되니까 i-2 > 0
            if(i > 2){
                for(int j=2; j<=m; j++){
                    for(int k=i-2; k >=1; k--){
                        if(dp[k][j-1] != -INF){
                            dp[i][j] = Math.max(dp[i][j], dp[k][j-1] + arr[i]);
                        }
                    }
                }
            }
        }

//        for(int[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }

        int answer = -INF;
        for(int i=1; i<=n; i++){
            answer = Math.max(answer, dp[i][m]);
        }

        System.out.println(answer);
    }
}
