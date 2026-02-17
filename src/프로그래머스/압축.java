package 프로그래머스;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    public static void main(String[] args) {

    }

    Node trie;

    public int[] solution(String msg) {
        // 초기화
        trie = new Node(0);
        int idx = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            trie.child.put(c, new Node(idx++));
        }

        List<Integer> result = new ArrayList<>();
        // 문자열
        int i = 0;
        while (i < msg.length()) {
            Node cur = trie;
            int len = 0; // 일치하는 문자열 길이

            for (int j = i; j < msg.length(); j++) {
                char c = msg.charAt(j);

                // 자식노드 없으면 종료
                if (!cur.child.containsKey(c)) {
                    break;
                }

                cur = cur.child.get(c);
                len++;
            }

            // 찾은거 출력
            result.add(cur.index);

            // 새로운거 등록
            if (i + len < msg.length()) {
                char c = msg.charAt(i + len);
                cur.child.put(c, new Node(idx++));
            }

            // i 이동
            i += len;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public class Node {
        int index;
        Map<Character, Node> child = new HashMap<>();

        public Node(int index) {
            this.index = index;
        }
    }
}
