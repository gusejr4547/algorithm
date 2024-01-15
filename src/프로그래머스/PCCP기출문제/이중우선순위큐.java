package 프로그래머스.PCCP기출문제;

import java.util.*;

public class 이중우선순위큐 {
    public static void main(String[] args) {
        이중우선순위큐 Main = new 이중우선순위큐();
        String[] operation = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(Main.solution(operation)));
    }

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String op = st.nextToken();

            if ("I".equals(op)) {
                int value = Integer.parseInt(st.nextToken());
                maxPQ.offer(value);
                minPQ.offer(value);
            } else {
                int select = Integer.parseInt(st.nextToken());

                if (maxPQ.isEmpty()) {
                    continue;
                }

                if (select == 1) {
                    int value = maxPQ.poll();
                    minPQ.remove(value);
                } else {
                    int value = minPQ.poll();
                    maxPQ.remove(value);
                }
            }
//            System.out.println(maxPQ);
//            System.out.println(minPQ);
        }
        int[] answer = new int[2];
        if (maxPQ.size() > 0) {
            answer[0] = maxPQ.poll();
            answer[1] = minPQ.poll();
        }

        return answer;
    }
}
