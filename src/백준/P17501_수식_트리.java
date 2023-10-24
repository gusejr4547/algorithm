package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17501_수식_트리 {

    static int N;
    static int[] nums;
    static Node[] nodes;
    static int ans = 0;
    static int leftP, rightP;

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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = st.nextToken().equals("+") ? 1 : -1;
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(op, 0, left, right);
        }
        // 숫자가 들어갈 위치에 부호가 음수인지 양수인지 판단 필요
        // 1 ~ N 노드의 count 값을 확인
        // count가 홀수이면 음수
        // nums를 정렬해서 양수에는 큰값 할당, 음수에는 작은수 할당
        leftP = 0;
        rightP = N - 1;
        Arrays.sort(nums);
        dfs(2 * N - 1, 0);

        System.out.println(ans);
    }

    static void dfs(int start, int count) {
        nodes[start].count = count;

        // 리프노드
        if (nodes[start].op == 0) {
            if (nodes[start].count % 2 == 0) {
                ans += nums[rightP];
                rightP--;
            } else {
                ans -= nums[leftP];
                leftP++;
            }
            return;
        }

        int left = nodes[start].left;
        int right = nodes[start].right;

        if (nodes[start].op == 1) {
            dfs(left, count);
            dfs(right, count);
        } else if (nodes[start].op == -1) {
            dfs(left, count);
            dfs(right, count + 1);
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

        @Override
        public String toString() {
            return "Node{" +
                    "op=" + op +
                    ", count=" + count +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
