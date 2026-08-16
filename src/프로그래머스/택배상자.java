package 프로그래머스;

import java.util.ArrayDeque;

public class 택배상자 {
    public static void main(String[] args) {

    }

    public int solution(int[] order) {
        int answer = 0;
        int N = order.length;
        // 보조 컨테이너 벨트 => stack

        int num = 1;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int ord = order[i];

            while (num <= ord) {
                stack.push(num++);
            }

            if (!stack.isEmpty() && stack.peek() != ord) {
                break;
            }

            stack.pop();
            answer++;
        }

        return answer;
    }
}
