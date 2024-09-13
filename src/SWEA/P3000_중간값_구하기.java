package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P3000_중간값_구하기 {
    static final int MOD = 20171109;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
            PriorityQueue<Integer> minPQ = new PriorityQueue<>((o1, o2) -> o1 - o2);
            minPQ.offer(A);

            int sum = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                if (X < Y) {
                    maxPQ.offer(X);
                    minPQ.offer(Y);
                } else {
                    maxPQ.offer(Y);
                    minPQ.offer(X);
                }

                while (maxPQ.peek() > minPQ.peek()) {
                    int a = maxPQ.poll();
                    int b = minPQ.poll();
                    maxPQ.offer(b);
                    minPQ.offer(a);
                }

                sum = (sum + minPQ.peek()) % MOD;
            }

            sb.append(sum % MOD).append("\n");
        }

        System.out.println(sb.toString());
    }
}
