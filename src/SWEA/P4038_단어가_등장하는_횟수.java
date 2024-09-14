package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P4038_단어가_등장하는_횟수 {
    static final int MOD = 100000007;
    static final int EXPONENT1 = 31;
    static final int EXPONENT2 = 37;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String book = br.readLine();
            String word = br.readLine();

            int matchCnt = getCount(book, word);

            sb.append("#").append(tc).append(" ").append(matchCnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 라빈 카프 알고리즘 사용
    private static int getCount(String string, String pattern) {
        int count = 0;
        int stringLength = string.length();
        int patternLength = pattern.length();

        int patternHash1 = 0;
        int subStringHash1 = 0;

        int patternHash2 = 0;
        int subStringHash2 = 0;

        int power1 = 1;
        int power2 = 1;

        for (int i = 0; i <= stringLength - patternLength; i++) {
            if (i == 0) {
                for (int j = 0; j < patternLength; j++) {
                    subStringHash1 += hashing(string.charAt(patternLength - 1 - j), power1);
                    patternHash1 += hashing(pattern.charAt(patternLength - 1 - j), power1);

                    subStringHash2 += hashing(string.charAt(patternLength - 1 - j), power2);
                    patternHash2 += hashing(pattern.charAt(patternLength - 1 - j), power2);

                    if (j < patternLength - 1) {
                        power1 *= EXPONENT1;
                        power2 *= EXPONENT2;
                    }
                }
            } else {
                subStringHash1 = string.charAt(i + patternLength - 1) +
                        EXPONENT1 * (subStringHash1 - hashing(string.charAt(i - 1), power1));

                subStringHash2 = string.charAt(i + patternLength - 1) +
                        EXPONENT2 * (subStringHash2 - hashing(string.charAt(i - 1), power2));
            }

            // hash값 같은가?
            if (subStringHash1 == patternHash1 && subStringHash2 == patternHash2) {
                count++;
            }
        }
        return count;
    }

    private static int hashing(int value, int power) {
        return value * power;
    }
}
