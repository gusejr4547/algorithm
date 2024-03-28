package 프로그래머스;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 더_맵게 {
    public static void main(String[] args) {
        더_맵게 Main = new 더_맵게();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(Main.solution(scoville, K));
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (int val : scoville) {
            pq.offer(val);
        }

        while (pq.peek() < K) {
            if (pq.size() == 1) {
                return -1;
            }
            int first = pq.poll();
            int second = pq.poll();
            answer++;
            pq.offer(first + second * 2);
        }

        return answer;
    }
}
