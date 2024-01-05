package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 두 문자열의 LCS 수열을 찾아냄 찾아낸 것과 나머지의 LCS를 구함 => 반례 존재
/*
A: dababcf
B: ababdef
C: df
 */

// 한번에 해야함 3차원 배열

public class P1958_LCS3 {

    static String first, second, third;
    static int[][][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        first = br.readLine();
        second = br.readLine();
        third = br.readLine();

        memo = new int[first.length() + 1][second.length() + 1][third.length() + 1];
        LCS(first, second, third);
        System.out.println(memo[first.length()][second.length()][third.length()]);
    }

    public static void LCS(String s1, String s2, String s3) {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 1; k <= s3.length(); k++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1))
                        memo[i][j][k] = memo[i - 1][j - 1][k - 1] + 1;
                    else
                        memo[i][j][k] = Math.max(memo[i - 1][j][k], Math.max(memo[i][j - 1][k], memo[i][j][k - 1]));
                }

            }
        }
    }

//    public static String findLCS(String first, String second, int i, int j) {
//        char[] result = new char[memo[i][j]];
//        int idx = memo[i][j];
//        while (idx > 0) {
//            // 1. 위쪽 같은지, 2. 오른쪽 같은지 => 같은 쪽으로 이동 순서는 상관없음
//            // 3. 전부 다르면 왼쪽 위 대각선으로 이동
//            if (idx == memo[i - 1][j]) {
//                i = i - 1;
//            } else if (idx == memo[i][j - 1]) {
//                j = j - 1;
//            } else {
//                result[idx - 1] = first.charAt(i - 1);
//                i -= 1;
//                j -= 1;
//                idx -= 1;
//            }
//        }
//
//        return new String(result);
//    }
}
