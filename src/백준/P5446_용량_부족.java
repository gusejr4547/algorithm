package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class P5446_용량_부족 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            Trie trie = new Trie();
            // 지워야 하는 파일
            int N1 = Integer.parseInt(br.readLine());
            for (int i = 0; i < N1; i++) {
                String file = br.readLine();
                trie.insert(file);
            }

            // 지우면 안 되는 파일
            int N2 = Integer.parseInt(br.readLine());
            for (int i = 0; i < N2; i++) {
                String file = br.readLine();
                trie.setNotDelete(file);
            }

            // 결과
            // 전부 지울 수 있는지 확인
            boolean canRemoveAll = true;
            for (Character key : trie.root.child.keySet()) {
                if (!trie.root.child.get(key).canDelete) {
                    canRemoveAll = false;
                    break;
                }
            }

            if (canRemoveAll) {
                System.out.println(1);
            } else {
                System.out.println(trie.getDelete());
            }
        }
    }

    public static class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                node = node.child.computeIfAbsent(word.charAt(i), k -> new Node());
            }
            node.isEnd = true;
        }

        void setNotDelete(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                node = node.child.getOrDefault(word.charAt(i), null);
                if (node == null) {
                    return;
                }
                node.canDelete = false;
            }
        }

        int getDelete() {
            ArrayDeque<Node> queue = new ArrayDeque<>();
            Node node = this.root;
            queue.offer(node);
            int result = 0;
            while (!queue.isEmpty()) {
                Node curr = queue.poll();

                if (curr.isEnd) {
                    result++;
//                    System.out.println("END");
                }

                for (Character key : curr.child.keySet()) {
                    Node next = curr.child.get(key);
                    if (next.canDelete) {
                        result++;
//                        System.out.println(key);
                    } else {
                        queue.offer(next);
                    }
                }
            }
            return result;
        }
    }

    public static class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
        boolean canDelete = true;
    }
}
