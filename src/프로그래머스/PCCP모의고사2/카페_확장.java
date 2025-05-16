package 프로그래머스.PCCP모의고사2;

import java.util.ArrayDeque;

public class 카페_확장 {
    public static void main(String[] args) {
        카페_확장 Main = new 카페_확장();
        int[] menu = {5, 12, 30};
        int[] order = {2, 1, 0, 0, 0, 1, 0};
        int k = 10;
        System.out.println(Main.solution(menu, order, k));
    }

    public int solution(int[] menu, int[] order, int k) {
        int answer = 0;

        int curTime = 0;
        int endTime = 0;

        ArrayDeque<Integer> orderQueue = new ArrayDeque<>();
        for (int orderedMenu : order) {
            endTime = Math.max(endTime, curTime) + menu[orderedMenu];
            // 주문 대기열 추가
            orderQueue.offer(endTime);

            // 현재 시간 이전 주문 처리
            while (!orderQueue.isEmpty() && orderQueue.peek() <= curTime) {
                orderQueue.poll();
            }

            // 기록
            answer = Math.max(answer, orderQueue.size());
            curTime += k;
        }

        return answer;
    }
}
