package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P12767_Ceiling_Function {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Set<String> shapeSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Node root = null;
            for (int j = 0; j < K; j++) {
                int val = Integer.parseInt(st.nextToken());
                root = insert(root, val);
            }

            String shape = getShape(root);
            shapeSet.add(shape);
        }

        System.out.println(shapeSet.size());
    }

    private static String getShape(Node node) {
        List<String> paths = new ArrayList<>();

        dfs(node, "T", paths);

        Collections.sort(paths);

        return String.join(" ", paths);
    }

    private static void dfs(Node node, String current, List<String> paths) {
        if (node == null) {
            return;
        }

        paths.add(current);

        // 왼쪽
        dfs(node.left, current + "L", paths);

        // 오른쪽
        dfs(node.right, current + "R", paths);
    }

    private static Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    private static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }
}
