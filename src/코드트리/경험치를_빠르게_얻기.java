package 코드트리;

import java.util.*;

public class 경험치를_빠르게_얻기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] exp = new int[n];
        int[] time = new int[n];

        int totalTime = 0;
        for (int i = 0; i < n; i++) {
            exp[i] = sc.nextInt();
            time[i] = sc.nextInt();
            totalTime += time[i];
        }
        // Please write your code here.
        int[] dp = new int[totalTime+1]; // dp[i] i시간에 얻을 수 있는 최대 경험치
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i=0; i<n; i++){
            int questTime = time[i];
            int questExp = exp[i];
            for(int t = totalTime; t >= 0; t--){
                if(t>=questTime && dp[t-questTime] != -1){
                    dp[t] = Math.max(dp[t], dp[t-questTime] + questExp);
                }
            }
        }

        int answer = -1;
        for(int t=0; t<=totalTime; t++){
            if(dp[t] >= m){
                answer = t;
                break;
            }
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(answer);
    }
}
