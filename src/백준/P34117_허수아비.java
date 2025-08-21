package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P34117_허수아비 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] defence = new long[N];
        for (int i = 0; i < N; i++) {
            defence[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Long> using = new PriorityQueue<>((o1, o2) -> Long.compare(o1, o2));
        long sum = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            long d = defence[i];

            using.offer(d);
            sum += d;

            while (!using.isEmpty() && sum - using.peek() >= P) {
                sum = sum - using.poll();
            }

            // 결과 저장
            if (sum >= P) {
                sb.append(using.size());
            } else {
                sb.append(-1);
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
