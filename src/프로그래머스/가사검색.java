package 프로그래머스;

import java.util.Arrays;
import java.util.HashMap;

public class 가사검색 {
    public static void main(String[] args) {
        가사검색 Main = new 가사검색();
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString(Main.solution(words, queries)));
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie frontHead = new Trie();
        Trie backHead = new Trie();

        // words 추가
        for (String word : words) {
            add(word, frontHead);
            add(reverse(word), backHead);
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i].charAt(0) == '?') {
                answer[i] = find(reverse(queries[i]), backHead);
            } else {
                answer[i] = find(queries[i], frontHead);
            }
        }

        return answer;
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private int find(String query, Trie node) {
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == '?') {
                return node.childLenMap.getOrDefault(query.length(), 0);
            }

            if (!node.child.containsKey(query.charAt(i))) {
                return 0;
            }

            node = node.child.get(query.charAt(i));
        }
        return 0;
    }

    private void add(String word, Trie node) {
        int len = word.length();
        node.childLenMap.put(len, node.childLenMap.getOrDefault(len, 0) + 1);

        for (int i = 0; i < word.length(); i++) {
            if (!node.child.containsKey(word.charAt(i))) {
                node.child.put(word.charAt(i), new Trie());
            }
            node = node.child.get(word.charAt(i));
            node.childLenMap.put(len, node.childLenMap.getOrDefault(len, 0) + 1);
        }

        node.isEnd = true;
    }

    private class Trie {
        HashMap<Integer, Integer> childLenMap = new HashMap<>();
        boolean isEnd;
        HashMap<Character, Trie> child = new HashMap<>();
    }
}
