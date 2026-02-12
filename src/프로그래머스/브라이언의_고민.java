package 프로그래머스;

import java.util.*;

public class 브라이언의_고민 {
    public static void main(String[] args) {
        브라이언의_고민 Main = new 브라이언의_고민();
        String sentence = "SpIpGpOpNpGJqOqA";
        System.out.println(Main.solution(sentence));
    }

    boolean[] used; // 사용된 소문자
    List<String> result; // 결과
    List<Integer>[] pos; // 소문자 인덱스 위치

    public String solution(String sentence) {
        // sentence의 소문자는 특수기호를 뜻한다.
        // 특수기호는 중복되지 않음
        // 소문자를 없애야함
        // sentence.length() <= 1000

        // 광고글?
        // 규칙 1 : 특정 단어의 글자 사이마다 특수기호를 넣기
        // 규칙 2 : 특정 단어 앞뒤에 특수기호를 넣기
        // 1,2가 한 단어에 모두 적용될 수 있음. 같은 규칙은 2번 적용 x
        // 한번 쓰인 소문자는 다시 쓸 수 없다.
        // 공백제거

        int len = sentence.length();

        // 각 소문자 idx 기록
        pos = new List[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            char c = sentence.charAt(i);
            if (Character.isLowerCase(c)) {
                pos[c - 'a'].add(i);
            }
        }

        used = new boolean[26];
        result = new ArrayList<>();
        int idx = 0;
        while (idx < len) {
            char c = sentence.charAt(idx);

            // 공백 => invalid
            if (' ' == c) {
                return "invalid";
            }

            // 현재 문자가 소문자 => 규칙2
            if (Character.isLowerCase(c)) {
                int x = c - 'a';
                // 이미 사용된 소문자
                if (used[x]) {
                    return "invalid";
                }
                // 규칙2는 소문자가 2개뿐
                if (pos[x].size() != 2) {
                    return "invalid";
                }

                // 다음 소문자
                int end = pos[x].get(1);
                // 바로 다음에 오면 x
                if (end == idx + 1) {
                    return "invalid";
                }

                // 소문자 사이 단어
                String word = sentence.substring(idx + 1, end);
                word = rule1InRule2(word);
                if (word == null) {
                    return "invalid";
                }
                result.add(word);
                used[x] = true;
                idx = end + 1;
            }
            // 대문자
            else {
                // 다음이 소문자?
                if (idx + 1 < len && Character.isLowerCase(sentence.charAt(idx + 1))) {
                    char lowC = sentence.charAt(idx + 1);
                    int x = lowC - 'a';

                    // 정확히 2번만 나오면 다음 루프에서 rule2로 처리하도록 만든다.
                    if (pos[x].size() == 2) {
                        result.add(String.valueOf(c));
                        idx += 1;
                        continue;
                    }

                    // rule1
                    if (used[x]) {
                        return "invalid";
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    int j = idx;
                    while (true) {
                        // 같은 소문자가 더 있나?
                        if (j + 1 >= len || sentence.charAt(j + 1) != lowC) {
                            break;
                        }

                        // 소문자는 있는데 소문자 뒤에 대문자가 없는 경우
                        if (j + 2 >= len || Character.isLowerCase(sentence.charAt(j + 2))) {
                            return "invalid";
                        }
                        sb.append(sentence.charAt(j + 2));
                        j += 2;
                    }

                    used[x] = true;
                    result.add(sb.toString());
                    idx = j + 1;

                }
                // 다음이 대문자?
                else {
                    // 대문자 여러개면 하나의 단어로 취급
                    int j = idx;
                    while (j < len && Character.isUpperCase(sentence.charAt(j))) {
                        j++;
                    }

                    // 대문자 여러개 뒤에 소문자가 나옴?
                    if (j < len && Character.isLowerCase(sentence.charAt(j))) {
                        // 소문자 개수가 2번?
                        int x = sentence.charAt(j) - 'a';
                        if (pos[x].size() != 2) {
                            // rule1
                            j--;
                        }
                    }

                    result.add(sentence.substring(idx, j));
                    idx = j;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append(s).append(' ');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private String rule1InRule2(String word) {
        int len = word.length();
        // 전부 대문자
        boolean isAllUpper = true;
        for (int i = 0; i < len; i++) {
            if (Character.isLowerCase(word.charAt(i))) {
                isAllUpper = false;
            }
        }
        if (isAllUpper) {
            return word;
        }

        // rule1 적용
        // 적어도 길이 3, 홀수
        if (len < 3 || len % 2 == 0) {
            return null;
        }
        if (Character.isLowerCase(word.charAt(0))) {
            return null;
        }
        if (Character.isUpperCase(word.charAt(1))) {
            return null;
        }

        char c = word.charAt(1);
        int x = c - 'a';
        if (used[x]) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // 대문자
            if (i % 2 == 0) {
                // 소문자면 x
                if (Character.isLowerCase(word.charAt(i))) {
                    return null;
                }
                sb.append(word.charAt(i));
            }
            // 소문자
            else {
                // 처음 구한 소문자랑 같아야함
                if (c != word.charAt(i)) {
                    return null;
                }
            }
        }
        used[x] = true;
        return sb.toString();
    }
}
