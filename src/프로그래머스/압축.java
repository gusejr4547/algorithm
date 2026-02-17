package 프로그래머스;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    public static void main(String[] args) {

    }

    Map<String, Integer> dict;
//    Node trie;

    public int[] solution(String msg) {
        init();

        List<Integer> result = new ArrayList<>();
        int idx = 27;
        for (int i = 0; i < msg.length(); i++) {
            // 현재 위치에서 사전에 있는 가장 긴 문자열
            String word = "";
            int next = i;
            for (int j = i + 1; j <= msg.length(); j++) {
                String str = msg.substring(i, j);
                if (dict.containsKey(str)) {
                    word = str;
                    next = j;
                } else {
                    break;
                }
            }

            // 색인번호 출력
            result.add(dict.get(word));

            // 새 단어 등록
            if (next < msg.length()) {
                dict.put(word + String.valueOf(msg.charAt(next)), idx++);
            }

            // i 변경
            i += word.length() - 1;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public void init() {
        dict = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            dict.put(String.valueOf(i), i - 'A' + 1);
        }
    }

//    public class Node {
//        int index;
//        Map<Character, Node> child;
//    }
}
