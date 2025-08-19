package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1701_Cubeditor {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();

        int answer = 0;
        // KMP 알고리즘 => 접두사, 접미사를 이용해서 중복을 줄인다.
        for (int i = 0; i < len; i++) {
            String subP = str.substring(i);
            answer = Math.max(answer, find(subP));
        }

        System.out.println(answer);
    }

    private static int find(String pattern) {
        int n = pattern.length();
        int[] pi = new int[n];
        int idx = 0;
        int max = 0;

        for (int i = 1; i < n; i++) {
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = pi[idx - 1];
            }

            // 새로추가된 문자랑 비교
            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx++;
                pi[i] = idx;
                max = Math.max(max, idx);
            }
        }

        return max;
    }

}
