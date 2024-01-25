package 프로그래머스;

import java.util.HashMap;
import java.util.Map;

public class 자동완성_3차 {
    public static void main(String[] args) {
        자동완성_3차 Main = new 자동완성_3차();
        String[] words = {"go", "gone", "guild"};
        System.out.println(Main.solution(words));
    }

    // 트라이(Trie) 자료구조
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            answer += trie.search(word);
        }

        return answer;
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node currNode = this.root;
            for (int i = 0; i < word.length(); i++) {
                currNode = currNode.child.computeIfAbsent(word.charAt(i), k -> new Node());
                currNode.count++;
            }
            currNode.isLeaf = true;
        }

        public int search(String word) {
            Node currNode = this.root;
            int maxChar = 1;
            for (int i = 0; i < word.length(); i++) {
                currNode = currNode.child.getOrDefault(word.charAt(i), null);
                if (currNode == null) {
                    return -1;
                }
                if (currNode.count == 1) {
                    return maxChar;
                }
                maxChar++;
            }
            return maxChar - 1;
        }
    }

    static class Node {
        boolean isLeaf;
        Map<Character, Node> child;
        int count = 0;


        public Node() {
            child = new HashMap<>();
        }
    }
}
