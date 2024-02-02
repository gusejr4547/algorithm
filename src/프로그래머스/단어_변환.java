package 프로그래머스;

import java.util.HashSet;
import java.util.PriorityQueue;

public class 단어_변환 {
    public static void main(String[] args) {
        단어_변환 Main = new 단어_변환();
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(Main.solution(begin, target, words));
    }

    public int solution(String begin, String target, String[] words) {
        boolean isValid = false;
        for (String word : words) {
            if (target.equals(word)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) return 0;

        return bfs(begin, target, words);
    }

    public int bfs(String begin, String target, String[] words) {
        int len = words.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<String> use = new HashSet<>();
        pq.offer(new Node(begin, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            String currWord = curr.word;

            if (target.equals(currWord)) {
                return curr.depth;
            }

            if (use.contains(currWord)) continue;
            use.add(currWord);

            for (String word : words) {
                int diff = 0;
                for (int cIdx = 0; cIdx < currWord.length(); cIdx++) {
                    if (currWord.charAt(cIdx) != word.charAt(cIdx))
                        diff++;
                }
                if (diff == 1) {
                    pq.offer(new Node(word, curr.depth + 1));
                }
            }
        }
        return 0;
    }

    static class Node implements Comparable<Node> {
        String word;
        int depth;

        public Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.depth, o.depth);
        }
    }
}
