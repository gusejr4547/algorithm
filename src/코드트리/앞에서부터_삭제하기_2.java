package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 앞에서부터_삭제하기_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefixSum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) ->
                o1.num - o2.num == 0 ? o2.idx - o1.idx : o1.num - o2.num);
        for (int i = 1; i <= N; i++) {
            pq.offer(new Pair(i, arr[i]));
        }

        double answer = 0;

        // 뺀 숫자
        Pair min = pq.poll();

        for (int k = 1; k <= N - 2; k++) {
            // 현재 범위 k+1 ~ N

            // 뺀 숫자가 범위 밖에 있음
            if (min.idx <= k) {
                // 만족할때까지 찾기
                while (!pq.isEmpty() && pq.peek().idx <= k) {
                    pq.poll();
                }
                min = pq.poll();
            }

            // 평균 계산
            answer = Math.max(answer, (prefixSum[N] - prefixSum[k] - min.num) / (N - k - 1.0));
        }

        System.out.printf("%.2f%n", answer);
    }

    private static class Pair {
        int idx, num;

        public Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}
