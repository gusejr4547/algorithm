package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1517_버블_소트 {
    static int[] numArr;
    static long[] segTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        int size = getTreeSize(N);
        segTree = new long[size];

        Map<Integer, List<Integer>> pos = new HashMap<>(); // num에 대한 idx를 저장
        for (int i = 0; i < N; i++) {
            pos.computeIfAbsent(numArr[i], k -> new ArrayList<>()).add(i);
        }
        // 오름차순 정렬
        Arrays.sort(numArr);

        long swapCnt = 0;
        int prev = numArr[0];
        for (int value : numArr) {
            // 이전보다 더 큰 수인 경우
            if (value != prev) {
                // 세그먼트 트리 갱신
                for (int idx : pos.get(prev)) {
                    update(0, N - 1, 1, idx);
                }
                prev = value;

                // swap 횟수 계산
                for (int idx : pos.get(value)) {
                    swapCnt += sum(0, N - 1, 1, idx + 1, N - 1);
                }

            }
        }

        System.out.println(swapCnt);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        // left right 범위 벗어나는 구간 제외
        if (end < left || right < start) {
            return 0;
        }
        // 범위 들어오는거
        if (left <= start && end <= right) {
            return segTree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int idx) {
        // 리프 노드
        if (start == end) {
            segTree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(start, mid, node * 2, idx);
        } else {
            update(mid + 1, end, node * 2 + 1, idx);
        }

        // 구간합 갱신
        segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
    }

    private static int getTreeSize(int N) {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        return (int) Math.pow(2, h);
    }
}
