package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class _110옮기기 {
    public static void main(String[] args) {
        _110옮기기 Main = new _110옮기기();
        String[] s = {"1110", "100111100", "0111111010"};
        System.out.println(Arrays.toString(Main.solution(s)));
    }

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        // 0을 최대한 앞으로 보내야함

        for (int t = 0; t < s.length; t++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            String str = s[t];
            int count110 = 0;

            for (int i = 0; i < str.length(); i++) {
                stack.push(str.charAt(i));

                if (stack.size() >= 3) {
                    char first = stack.pop();
                    if (first != '0') {
                        stack.push(first);
                        continue;
                    }
                    char second = stack.pop();
                    if (second != '1') {
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    char third = stack.pop();
                    if (third != '1') {
                        stack.push(third);
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    count110++;
                }
            }


            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty()) {
                char val = stack.pop();
                if (val == '0') {
                    while (count110-- > 0) {
                        result.insert(0, "110");
                    }
                }
                result.insert(0, val);
            }

            while (count110-- > 0) {
                result.insert(0, "110");
            }

            answer[t] = result.toString();
        }

        return answer;
    }
}
