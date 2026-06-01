package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 구간최대합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int l = 0; l < N; l++) {
            PriorityQueue<Integer> hi = new PriorityQueue<>();
            PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());

            int sumHi = 0;

            for (int r = l; r < N; r++) {
                int x = A[r];
                if (lo.isEmpty() || x <= lo.peek()) {
                    lo.offer(x);
                } else {
                    hi.offer(x);
                    sumHi += x;
                }

                // hi, lo  크기 맞추기
                if (hi.size() > lo.size()) {
                    int num = hi.poll();
                    lo.offer(num);
                    sumHi -= num;
                } else if (lo.size() > hi.size() + 1) {
                    int num = lo.poll();
                    hi.offer(num);
                    sumHi += num;
                }

                // 길이가 짝수
                if ((r - l + 1) % 2 == 0) {
                    answer = Math.max(answer, sumHi);
                }
            }
        }

        System.out.println(answer);
    }
}
