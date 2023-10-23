package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P17501_수식_트리 {

    static int N;
    static int[] nums;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        nodes = new Node[2 * N];
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = Integer.parseInt(br.readLine());
            nodes[i] = new Node();
        }
        for (int i = N + 1; i <= 2 * N - 1; i++) {

        }
    }

    static class Node {
        int op;
        int count;
        int left;
        int right;

        public Node() {
        }

        public Node(int op, int count, int left, int right) {
            this.op = op;
            this.count = count;
            this.left = left;
            this.right = right;
        }
    }
}
