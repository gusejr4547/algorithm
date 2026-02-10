package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1715_카드_정렬하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        // 작은거 먼저 합치기

        int answer = 0;
        while (pq.size() > 1) {
            // 2개뽑아서 합치고 다시 넣기
            int sum = pq.poll() + pq.poll();
            answer += sum;
            pq.offer(sum);
        }

        System.out.println(answer);
    }
}
