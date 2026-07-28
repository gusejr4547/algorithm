package 프로그래머스;

import java.util.ArrayDeque;

public class 짝지어_제거하기 {
    public static void main(String[] args) {

    }

    public int solution(String s) {
        int answer = 0;

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // stack에 같은 거 찾아
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if(stack.isEmpty()){
            answer = 1;
        }

        return answer;
    }
}
