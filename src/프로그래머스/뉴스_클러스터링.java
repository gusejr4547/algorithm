package 프로그래머스;

import java.util.*;

public class 뉴스_클러스터링 {
    public static void main(String[] args) {
        뉴스_클러스터링 Main = new 뉴스_클러스터링();
        String str1 = "___";
        String str2 = "+++";
        System.out.println(Main.solution(str1, str2));
    }

    // 자카드 유사도
    // J(A,B) = 교집합/합집합
    // 교집합이 공집합이면 1

    public final int OFFSET = 65536;

    public int solution(String str1, String str2) {
        // str 2글자씩 끊어서 집합을 만든다.
        // 영문자만 유효하다. ab+ 주어지면 ab만 유효 b+는 버림
        // 대문자 소문자는 구분하지 않는다.
        // 자카드 유사도를 계산한다.

        // str1, str2 length() <= 1000

        Map<String, Integer> A = new HashMap<>();
        Map<String, Integer> B = new HashMap<>();
        makeStringSet(str1, A);
        makeStringSet(str2, B);

        // 교집합 원소 개수
        int interCnt = 0;
        for (String k : A.keySet()) {
            if (B.containsKey(k)) {
                interCnt += Math.min(A.get(k), B.get(k));
            }
        }

        // 합집합 원소 개수
        int outerCnt = A.values().stream().mapToInt(Integer::intValue).sum()
                + B.values().stream().mapToInt(Integer::intValue).sum() - interCnt;

        if (outerCnt == 0) {
            return OFFSET;
        }
        return OFFSET * interCnt / outerCnt;
    }

    public void makeStringSet(String str, Map<String, Integer> map) {
        for (int i = 0; i < str.length() - 1; i++) {
            // i, i+1 쌍 만들기
            if (Character.isAlphabetic(str.charAt(i)) && Character.isAlphabetic(str.charAt(i + 1))) {
                // 둘다 영문자면 => 대문자로
                String k = str.substring(i, i + 2).toUpperCase();
                map.put(k, map.getOrDefault(k, 0) + 1);
            }
        }
    }
}
