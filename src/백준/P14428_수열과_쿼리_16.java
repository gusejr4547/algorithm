package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14428_수열과_쿼리_16 {
    static int N;
    static int[] A, seg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 => 크기가 작은 값, 인덱스
        seg = new int[4 * N];
        init(1, 0, N - 1);

//        System.out.println(Arrays.toString(seg));

        // 쿼리
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (1 == op) {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());

                update(1, 0, N - 1, i, v);

            } else if (2 == op) {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;

                int result = query(1, 0, N - 1, left, right);
                sb.append(result + 1).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int query(int node, int start, int end, int left, int right) {
        // 범위 밖
        if (end < left || right < start) {
            return -1;
        }

        // 범위 안
        if (left <= start && end <= right) {
            return seg[node];
        }

        // 걸침
        int mid = (start + end) / 2;
        int leftIdx = query(node * 2, start, mid, left, right);
        int rightIdx = query(node * 2 + 1, mid + 1, end, left, right);

        // left, right 중 더 작은거
        return getMinIdx(leftIdx, rightIdx);
    }

    private static void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            A[idx] = value;
            return;
        }

        int mid = (start + end) / 2;
        if (start <= idx && idx <= mid) {
            update(node * 2, start, mid, idx, value);
        } else if (mid + 1 <= idx && idx <= end) {
            update(node * 2 + 1, mid + 1, end, idx, value);
        }

        seg[node] = getMinIdx(seg[node * 2], seg[node * 2 + 1]);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            seg[node] = start;
            return;
        }

        int mid = (start + end) / 2;
        init(node * 2, start, mid);
        init(node * 2 + 1, mid + 1, end);
        seg[node] = getMinIdx(seg[node * 2], seg[node * 2 + 1]);
    }

    private static int getMinIdx(int idx1, int idx2) {
        if (idx1 == -1) return idx2;
        if (idx2 == -1) return idx1;

        if (A[idx1] < A[idx2]) {
            return idx1;
        } else if (A[idx1] > A[idx2]) {
            return idx2;
        } else {
            return Math.min(idx1, idx2);
        }
    }
}
