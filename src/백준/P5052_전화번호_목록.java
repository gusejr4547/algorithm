package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P5052_전화번호_목록 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] phoneNums = new String[N];
            for (int i = 0; i < N; i++) {
                phoneNums[i] = br.readLine();
            }

            Arrays.sort(phoneNums);
//            solution1(N, phoneNums);
            solution2(N, phoneNums);
        }
    }

    // 트라이
    private static void solution2(int N, String[] phoneNums) {
        Trie head = new Trie();
        for (int i = 0; i < N; i++) {
            head.insert(phoneNums[i]);
        }

        boolean consistent = true;
        for (int i = 0; i < N; i++) {
            if (head.contains(phoneNums[i])) {
                consistent = false;
                break;
            }
        }

        if (consistent) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static class Trie {
        boolean isLeafNode;
        Map<Character, Trie> child = new HashMap<>();

        void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!node.child.containsKey(c)) {
                    node.child.put(c, new Trie());
                }
                node = node.child.get(c);
            }

            node.isLeafNode = true;
        }

        boolean contains(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                node = node.child.get(c);
                if (node == null) {
                    return false;
                }
            }

            if (node.isLeafNode && node.child.isEmpty()) {
                return false;
            }
            return true;
        }
    }

    // 정렬, 문자열 비교
    private static void solution1(int N, String[] phoneNums) {
        boolean consistent = true;
        for (int i = 0; i < N - 1; i++) {
            if (phoneNums[i + 1].startsWith(phoneNums[i])) {
                consistent = false;
                break;
            }
        }

        if (consistent) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
