package 프로그래머스;

import java.util.*;

public class 큰_수_만들기 {
    boolean[] isDelete;

    public String solution(String number, int k) {
        isDelete = new boolean[number.length()];

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < number.length(); i++) {
            while (!stack.isEmpty() && stack.peek() < number.charAt(i) && k > 0) {
                stack.pop();
                k--;
            }

            stack.push(number.charAt(i));
        }

        while (k-- > 0) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
