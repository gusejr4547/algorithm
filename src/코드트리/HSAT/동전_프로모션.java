package 코드트리.HSAT;

import java.util.Arrays;
import java.util.Scanner;

public class 동전_프로모션 {
    static int MAX = 987654321;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[] cType = new char[n + 1];
        int[] coin = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cType[i] = sc.next().charAt(0);
            coin[i] = sc.nextInt();
        }

        // A 타입, 같은 종류의 동전을 여러번 거슬러 줄 수 있다.
        // B 타입, 각 종류별로 1번 사용가능

        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], MAX);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][0] = 0; // 0은 0개로 만들 수 있음.
            int value = coin[i];
            // 여러번 사용 가능
            if (cType[i] == 'A') {
                for (int j = 1; j <= m; j++) {
                    if (j - value < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - value] + 1);
                    }
                }
            }
            // 한번만 사용 가능
            else {
                for (int j = m; j > 0; j--) {
                    if (j - value < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - value] + 1);
                    }
                }
            }
        }

//        for(int[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }

        System.out.println(dp[n][m] == MAX ? -1 : dp[n][m]);
    }
}
