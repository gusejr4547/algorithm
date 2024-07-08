package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 뒤에_있는_큰_수_찾기 {
    public static void main(String[] args) {
        뒤에_있는_큰_수_찾기 Main = new 뒤에_있는_큰_수_찾기();
        int[] numbers = {2, 3, 3, 5};
        System.out.println(Arrays.toString(Main.solution(numbers)));
    }

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        // stack에 [idx, 값] 저장
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[i] > stack.peek()[1]) {
                int[] updateInfo = stack.poll();
                answer[updateInfo[0]] = numbers[i];
            }
            stack.push(new int[]{i, numbers[i]});
//            if (stack.isEmpty()) {
//                stack.push(new int[]{i, numbers[i]});
//            } else {
//
//            }
        }

        return answer;
    }
}
