package 코드트리;

import java.util.Arrays;
import java.util.Scanner;

public class 패턴_매칭 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        char[] p = sc.next().toCharArray();
        int n = s.length;
        int m = p.length;

        // Please write your code here.

        boolean[][] isMatch = new boolean[n + 1][m + 1]; // dp[i][j] == true → s[0..i-1]과 p[0..j-1]이 매치됨
        isMatch[0][0] = true;

        for (int j = 2; j <= m; j++) {
            if (p[j - 1] == '*') {
                isMatch[0][j] = isMatch[0][j - 2];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // 일치한 경우
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    isMatch[i][j] = isMatch[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    // 0개 매칭
                    isMatch[i][j] = isMatch[i][j - 2];

                    // 1개 이상 매칭 => 앞 문자가 일치
                    if (p[j - 2] == s[i - 1] || p[j - 2] == '.') {
                        isMatch[i][j] |= isMatch[i - 1][j];
                    }
                }
            }
        }

//        for(boolean[] d : isMatch){
//            System.out.println(Arrays.toString(d));
//        }

        System.out.println(isMatch[n][m]);
    }
}
