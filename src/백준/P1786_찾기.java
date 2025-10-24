package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P1786_찾기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();

        List<Integer> result = KMP(T, P);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (Integer i : result) {
            sb.append(i).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static List<Integer> KMP(String str, String pattern) {
        List<Integer> result = new ArrayList<>();
        int[] lps = makeLPS(pattern);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            if (str.charAt(i) == pattern.charAt(j)) {
                // 완성
                if (j == pattern.length() - 1) {
                    result.add(i - pattern.length() + 2);
                    j = lps[j];
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    private static int[] makeLPS(String pattern) {
        int[] lps = new int[pattern.length()]; // lps[i] = 패턴의 0~i 구간 중 접두사와 접미사가 일치하는 최대 길이
        int len = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (len > 0 && pattern.charAt(i) != pattern.charAt(len)) {
                len = lps[len - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
            }
        }
        return lps;
    }
}
