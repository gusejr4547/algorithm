package 코드트리;

import java.util.*;

public class 적절한_옷_고르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] s = new int[n];
        int[] e = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
            e[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[m][n]; // m일에 n옷을 입는 경우 최대 만족도
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 초기
        for (int j = 0; j < n; j++) {
            if (s[j] == 1) {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) { // 이번에 선택한 옷
                for (int k = 0; k < n; k++) { // 이전에 선택한 옷
                    if (dp[i - 1][k] != -1) {
                        if (s[j] <= i + 1 && i + 1 <= e[j]) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Math.abs(v[k] - v[j]));
                        }
                    }
                }
            }
        }

        // for(int[] d : dp){
        //     System.out.println(Arrays.toString(d));
        // }

        int answer = 0;
        for (int j = 0; j < n; j++) {
            answer = Math.max(answer, dp[m - 1][j]);
        }

        System.out.println(answer);
    }
}
