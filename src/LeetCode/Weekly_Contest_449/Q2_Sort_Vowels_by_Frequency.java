package LeetCode.Weekly_Contest_449;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2_Sort_Vowels_by_Frequency {
    public static void main(String[] args) {

    }

    // a e i o u 만 재배치


    public String sortVowels(String s) {
        // 빈도
        Map<Character, Integer> freq = new HashMap<>();
        // 처음등장 위치
        Map<Character, Integer> firstIdx = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                if (!freq.containsKey(c)) {
                    freq.put(c, 1);
                    firstIdx.put(c, i);
                } else {
                    freq.put(c, freq.get(c) + 1);
                }
            }
        }

        List<Character> vowels = new ArrayList<>(freq.keySet());
        vowels.sort((o1, o2) ->
                freq.get(o1).equals(freq.get(o2)) ?
                        Integer.compare(firstIdx.get(o1), firstIdx.get(o2))
                        : Integer.compare(freq.get(o2), freq.get(o1)));

        StringBuilder sb = new StringBuilder();
        int p = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 모음이면?
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                char curV = vowels.get(p);
                sb.append(curV);

                // freq 차감
                freq.put(curV, freq.get(curV) - 1);

                // 다쓰면 다음 vowels로
                if (freq.get(curV) == 0) {
                    p++;
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
