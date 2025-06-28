package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1509_팰린드롬_분할 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        // i~j까지 팰린드롬인지 구하기
        boolean[][] isPel = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPel[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPel[i][i + 1] = true;
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (isPel[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    isPel[i][j] = true;
                }
            }
        }

        // dp[i] i번째 문자까지 팰린드롬 최소 개수
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            // 0~i가 팰린드롬
            if (isPel[0][i]) {
                dp[i] = 1;
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPel[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
//        System.out.println(Arrays.toString(isPel[0]));
        System.out.println(dp[n - 1]);
    }
}
