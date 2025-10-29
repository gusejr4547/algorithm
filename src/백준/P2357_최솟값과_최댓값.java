package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2357_최솟값과_최댓값 {
    static int N, M, size;
    static int[] num, maxSeg, minSeg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        int[][] query = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 2개 구성해서 하나는 구간 최대값, 구간 최소값
        // 리프 노드가 최소 N개 있어야함.
        size = 1;
        while (size < N) {
            size = size << 1;
        }
        size = size << 1;
        maxSeg = new int[size]; // N*4 하는게 전부 커버 가능함.
        minSeg = new int[size];
        initMinSeg(1, 1, N);
        initMaxSeg(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int[] q : query) {
            sb.append(findMin(1, 1, N, q[0], q[1])).append(" ").append(findMax(1, 1, N, q[0], q[1])).append("\n");
        }

        System.out.println(sb);
    }

    private static int findMin(int node, int left, int right, int s, int e) {
        // 범위 벗어남
        if (right < s || e < left) {
            return Integer.MAX_VALUE;
        }

        // left, right가 s,e의 범위 안에 있으면
        if (s <= left && right <= e) {
            return minSeg[node];
        }

        int mid = (left + right) / 2;

        int leftMin = findMin(node * 2, left, mid, s, e);
        int rightMin = findMin(node * 2 + 1, mid + 1, right, s, e);

        return Math.min(leftMin, rightMin);
    }

    private static int findMax(int node, int left, int right, int s, int e) {
        // 범위 벗어남
        if (right < s || e < left) {
            return Integer.MIN_VALUE;
        }

        // left, right가 s,e의 범위 안에 있으면
        if (s <= left && right <= e) {
            return maxSeg[node];
        }

        int mid = (left + right) / 2;

        int leftMax = findMax(node * 2, left, mid, s, e);
        int rightMax = findMax(node * 2 + 1, mid + 1, right, s, e);

        return Math.max(leftMax, rightMax);
    }

    private static void initMinSeg(int node, int left, int right) {
        if (left == right) {
            minSeg[node] = num[left];
            return;
        }

        int mid = (left + right) / 2;

        // 구간의 최솟값을 node에 저장
        initMinSeg(node * 2, left, mid);
        initMinSeg(node * 2 + 1, mid + 1, right);

        minSeg[node] = Math.min(minSeg[node * 2], minSeg[node * 2 + 1]);
    }

    private static void initMaxSeg(int node, int left, int right) {
        if (left == right) {
            maxSeg[node] = num[left];
            return;
        }

        int mid = (left + right) / 2;

        // 구간의 최댓값을 node에 저장
        initMaxSeg(node * 2, left, mid);
        initMaxSeg(node * 2 + 1, mid + 1, right);

        maxSeg[node] = Math.max(maxSeg[node * 2], maxSeg[node * 2 + 1]);
    }
}
