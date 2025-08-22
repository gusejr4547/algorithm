package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P31860_열심히_일하는_중 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> workQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < N; i++) {
            workQ.offer(Integer.parseInt(br.readLine()));
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        // 일하면 중요도 M만큼 감소. K 이하이면 완료
        // 만족감 = floor(Y/2) + 중요도
        int satisfaction = 0;
        while (!workQ.isEmpty()) {
            int work = workQ.poll();

            satisfaction = satisfaction / 2 + work;
            sb.append(satisfaction).append("\n");

            if (work - M > K) {
                workQ.offer(work - M);
            }
            count++;
        }
        System.out.println(count);
        System.out.println(sb);
    }
}
