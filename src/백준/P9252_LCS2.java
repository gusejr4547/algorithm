package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9252_LCS2 {
    static String first, second;
    static int[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        first = br.readLine();
        second = br.readLine();

        memo = new int[first.length() + 1][second.length() + 1];
        LCS(first, second);
        int length = memo[first.length()][second.length()];
        System.out.println(length);
        if (length > 0) {
            System.out.println(findLCS(first, second, first.length(), second.length()));
        }
    }

    private static String findLCS(String first, String second, int i, int j) {
        char[] result = new char[memo[i][j]];
        int idx = memo[i][j];

        while (idx > 0) {
            if (idx == memo[i - 1][j])
                i -= 1;
            else if (idx == memo[i][j - 1])
                j -= 1;
            else {
                result[idx - 1] = first.charAt(i - 1);
                idx -= 1;
                i -= 1;
                j -= 1;
            }
        }
        return new String(result);
    }

    private static void LCS(String s1, String s2) {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                else
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }
    }
}
