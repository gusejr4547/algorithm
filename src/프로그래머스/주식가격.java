package 프로그래머스;

import java.util.ArrayDeque;

public class 주식가격 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // stack에서 더 큰거 빼기
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            // 넣기
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = N - idx - 1;
        }

        return answer;
    }
}
