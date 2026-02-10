package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P13975_파일_합치기_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1, o2));
            for (int i = 0; i < K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long answer = 0;
            while (pq.size() > 1) {
                // 2개뽑아서 합치고 다시 넣기
                long sum = pq.poll() + pq.poll();
                answer += sum;
                pq.offer(sum);
            }

            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
