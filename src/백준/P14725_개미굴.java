package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P14725_개미굴 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }
        trie.print(trie.root, 0);
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String str) {
            Node currNode = this.root;
            StringTokenizer st = new StringTokenizer(str);
            int len = Integer.parseInt(st.nextToken());
            for (int i = 0; i < len; i++) {
                currNode = currNode.child.computeIfAbsent(st.nextToken(), k -> new Node());
            }
        }

        public void print(Node curr, int depth) {
            Node currNode = curr;
            if (currNode.child.isEmpty()) return;

            List<String> keyList = new ArrayList<>(curr.child.keySet());
            Collections.sort(keyList);
            StringBuilder sb = new StringBuilder();
            for (String key : keyList) {
                for (int i = 0; i < depth; i++) {
                    sb.append("--");
                }
                sb.append(key);
                System.out.println(sb.toString());
                sb.setLength(0);
                print(curr.child.get(key), depth + 1);
            }
        }
    }

    static class Node {
        Map<String, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }
}
