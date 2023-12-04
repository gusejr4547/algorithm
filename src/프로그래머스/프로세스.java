package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

public class 프로세스 {
    public static void main(String[] args) {
        프로세스 Main = new 프로세스();
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        System.out.println(Main.solution(priorities, location));
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int priority : priorities) {
            pq.offer(priority);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (pq.peek() == priorities[i]) {
                    pq.poll();
                    answer += 1;

                    if (location == i) {
                        return answer;
                    }
                }
            }
        }

        return answer;
    }
}
