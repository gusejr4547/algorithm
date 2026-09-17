package 프로그래머스;

import java.util.ArrayDeque;

public class 괄호_회전하기 {
    public int solution(String s) {
        // 회전시켜도 되는 것은 덩어리가 분리되면 안됨
        // 원래 괄호가 안되는게 회전시켜서 될 수 있음

        // s.length <= 1000
        // 1 000 000
        int answer = 0;
        int N = s.length();
        for(int start=0; start<N; start++){
            ArrayDeque<Character> stack = new ArrayDeque<>();
            boolean valid = true;

            for (int i = 0; i < N; i++) {
                int idx = (start + i) % N;
                char c = s.charAt(idx);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                    continue;
                }

                if (stack.isEmpty()) {
                    valid = false;
                    break;
                }

                char top = stack.peek();

                if ((c == ')' && top != '(')
                        || (c == ']' && top != '[')
                        || (c == '}' && top != '{')) {
                    valid = false;
                    break;
                }

                stack.pop();
            }

            if (valid && stack.isEmpty()) {
                answer++;
            }
        }

        return answer;
    }
}
