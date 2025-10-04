package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P2014_소수의_곱 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // K개의 소수
        int N = Integer.parseInt(st.nextToken()); // N번째 수

        st = new StringTokenizer(br.readLine());
        long[] prime = new long[K];
        for (int i = 0; i < K; i++) {
            prime[i] = Integer.parseInt(st.nextToken());
        }

        // arr은 오름차순으로 주어지는 소수
        // K <= 100
        // N <= 10만
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            pq.offer(prime[i]);
        }

        long answer = 0;
        while (N > 0 && !pq.isEmpty()) {
            long num = pq.poll();
            answer = num;

            // 숫자에 prime 곱하기
            for (int i = 0; i < K; i++) {
                long val = num * prime[i];
                pq.offer(val);
                // 중복되는 수 거르기
                if (num % prime[i] == 0) {
                    break;
                }
            }

            N--;
        }

        System.out.println(answer);
    }
}
