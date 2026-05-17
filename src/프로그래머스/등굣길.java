package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 등굣길 {
    public static void main(String[] args) {
        등굣길 Main = new 등굣길();
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        System.out.println(Main.solution(m, n, puddles));
    }

    int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        boolean[][] block = new boolean[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            block[puddle[1]][puddle[0]] = true;
        }

        // 오른쪽과 아래쪽으로만 움직인다.
        // (1,1) -> (n,m)
        dp[1][1] = 1;

        // 위쪽 왼쪽 경로 더한거
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (block[i][j]) {
                    continue;
                }

                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        // for(int[] d : dp){
        //     System.out.println(Arrays.toString(d));
        // }

//        System.out.println(dp[n][m]);

        return dp[n][m];
    }

//    public int solution(int m, int n, int[][] puddles) {
//        int[][] dp = new int[n][m];
//
//        for (int i = 0; i < puddles.length; i++) {
//            dp[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
//        }
//        dp[0][0] = 1;
//
//        int sum = 1;
//        int max = m + n - 2;
//        while (sum <= max) {
//            int minI = sum >= m ? sum - (m - 1) : 0;
//            int maxI = Math.min(sum, n - 1);
//            for (int i = minI; i <= maxI; i++) {
//                int j = sum - i;
//
//                if (dp[i][j] == -1) {
//                    continue;
//                }
//
//                int a = 0, b = 0;
//                if (j - 1 >= 0) {
//                    a = dp[i][j - 1] == -1 ? 0 : dp[i][j - 1];
//                }
//                if (i - 1 >= 0) {
//                    b = dp[i - 1][j] == -1 ? 0 : dp[i - 1][j];
//                }
////                System.out.println("a, b = " + a + ", " + b);
//                dp[i][j] = (a + b) == 0 ? -1 : (a + b) % 1_000_000_007;
//            }
//
//            sum++;
////            System.out.println("=================");
////            for (int[] qqq : dp) {
////                System.out.println(Arrays.toString(qqq));
////            }
////            System.out.println("=================");
//        }
//
//
//        return dp[n - 1][m - 1] == -1 ? 0 : dp[n - 1][m - 1];
//    }
}
