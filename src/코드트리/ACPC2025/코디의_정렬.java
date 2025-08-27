package 코드트리.ACPC2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 코디의_정렬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val == 0 ? o1.idx - o2.idx : o1.val - o2.val);
        for (int i = 1; i < N - 1; i++) {
            if (arr[i - 1] > arr[i] && arr[i + 1] > arr[i]) {
                pq.offer(new Pair(i, arr[i]));
            }
        }

        int s = 0;
        while (P-- > 0) {
            // 꺼낼 값 확인
            while (!pq.isEmpty()) {
                Pair p = pq.peek();
                if (p.idx <= s || arr[p.idx] != p.val || !(arr[p.idx - 1] > arr[p.idx] && arr[p.idx + 1] > arr[p.idx])) {
                    pq.poll();
                } else {
                    break;
                }
            }
            // swap
            if (!pq.isEmpty()) {
                Pair p = pq.poll();
                arr[p.idx] = arr[s];
                arr[s] = p.val;

                // 조건 변경된 부분 갱신
                // s+1
                if (1 <= s + 1 && s + 1 < N - 1 && arr[s] > arr[s + 1] && arr[s + 2] > arr[s + 1]) {
                    pq.offer(new Pair(s + 1, arr[s + 1]));
                }
                // p.idx - 1
                if (1 <= p.idx - 1 && p.idx - 1 < N - 1 && arr[p.idx - 2] > arr[p.idx - 1] && arr[p.idx] > arr[p.idx - 1]) {
                    pq.offer(new Pair(p.idx-1, arr[p.idx-1]));
                }
                // p.idx
                if (1 <= p.idx && p.idx < N - 1 && arr[p.idx - 1] > arr[p.idx] && arr[p.idx + 1] > arr[p.idx]) {
                    pq.offer(new Pair(p.idx, arr[p.idx]));
                }
                // p.idx + 1
                if (1 <= p.idx + 1 && p.idx + 1 < N - 1 && arr[p.idx] > arr[p.idx + 1] && arr[p.idx + 2] > arr[p.idx + 1]) {
                    pq.offer(new Pair(p.idx + 1, arr[p.idx + 1]));
                }
            }

            s++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static class Pair {
        int idx, val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
