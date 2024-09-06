package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중위순회 {
    // 트리는 완전 이진 트리 형식
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            sb.append("#").append(tc).append(" ");

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;

            char[] binaryTree = new char[N + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                char value = st.nextToken().charAt(0);
                binaryTree[node] = value;
            }

            inOrder(1, binaryTree);

            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void inOrder(int node, char[] binaryTree) {
        if(node >= binaryTree.length){
            return;
        }

        inOrder(node * 2, binaryTree);
        sb.append(binaryTree[node]);
        inOrder(node * 2 + 1, binaryTree);
    }
}
