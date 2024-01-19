package 프로그래머스;

import java.util.Collections;
import java.util.PriorityQueue;

public class 야근_지수 {
    public static void main(String[] args) {
        야근_지수 Main = new 야근_지수();
        int n = 3;
        int[] works = {1, 1};
        System.out.println(Main.solution(n, works));
    }

    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        while (n > 0 && !pq.isEmpty()) {
            int work = pq.poll();
            if (work > 1) {
                pq.offer(work - 1);
            }
            n--;
        }

        while (!pq.isEmpty()) {
            answer += (long) Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
