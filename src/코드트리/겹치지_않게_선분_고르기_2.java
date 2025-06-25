package 코드트리;

import java.util.*;

public class 겹치지_않게_선분_고르기_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(segments, (o1, o2)->o1[1] - o2[1]);

        int[] dp = new int[n];
        dp[0] = 1;
        int max = segments[0][1];

        for(int i = 1; i < n; i++){
            if(segments[i][0] <= max){
                dp[i] = dp[i-1];
            }else{
                dp[i] = dp[i-1] + 1;
                max = segments[i][1];
            }
        }

        System.out.println(dp[n-1]);
    }
}
