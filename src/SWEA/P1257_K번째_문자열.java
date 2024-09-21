package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 문자열의 길이는 최대 400
// substring은 최대
public class P1257_K번째_문자열 {
    static StringBuilder sb;
    static int k;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            Trie head = new Trie();
            k = Integer.parseInt(br.readLine());
            String word = br.readLine();

            Set<String> set = new HashSet<>();

            for (int i = 0; i < word.length(); i++) {
                for (int j = i; j < word.length(); j++) {
                    Trie node = head;
                    // i부터 j까지 substring
                    String subWord = word.substring(i, j + 1);
                    if (!set.contains(subWord)) {
                        set.add(subWord);
                    } else {
                        continue;
                    }

                    for (char c : subWord.toCharArray()) {
                        if (!node.child.containsKey(c)) {
                            Trie nextNode = new Trie();
                            node.child.put(c, nextNode);
                        }

                        node = node.child.get(c);
                        node.cnt++;
                    }

                    node.end = true;
                }
            }

            dfs(head, 0, new char[word.length()]);

            if (k > 0) sb.append("none");

            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(Trie trie, int depth, char[] result) {
        if (k <= 0) return;

        if (trie.end) {
            k--;
            if (k == 0) {
                // 결과
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < depth; i++) {
                    res.append(result[i]);
                }
                sb.append(res.toString());
                return;
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (trie.child.containsKey(c)) {
                Trie child = trie.child.get(c);

                if (k > child.cnt) {
                    k -= child.cnt;
                } else {
                    result[depth] = c;
                    dfs(child, depth + 1, result);
                }
            }
        }
    }

    private static class Trie {
        boolean end;
        int cnt; // 현재를 root로 하는 문자개수
        HashMap<Character, Trie> child = new HashMap<>();

        Trie() {
        }
    }

}
