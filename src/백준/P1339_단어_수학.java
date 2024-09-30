package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class P1339_단어_수학 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] alpha = new int[26];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                alpha[word.charAt(j) - 'A'] += (int) Math.pow(10, word.length() - 1 - j);
            }
        }

        Arrays.sort(alpha);

        int sum = 0;
        int num = 9;
        for (int i = alpha.length - 1; i >= 0; i--) {
            if (alpha[i] == 0) break;

            sum += alpha[i] * num;
            num--;
        }

        System.out.println(sum);
    }
}
