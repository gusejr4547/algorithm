package 코드트리;

import java.util.*;

public class 편집거리 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] A = sc.next().toCharArray();
        char[] B = sc.next().toCharArray();
        int n = A.length;
        int m = B.length;
        // Please write your code here.
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 교체, 삭제, 삽입 중 1개
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        // for(int[] d : dp){
        //     System.out.println(Arrays.toString(d));
        // }
        System.out.println(dp[n][m]);
    }
}
