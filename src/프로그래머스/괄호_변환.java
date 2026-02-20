package 프로그래머스;

import java.util.*;

public class 괄호_변환 {
    public static void main(String[] args) {

    }
    // ( ) 로 이루어진 문자열
    // (와 ) 개수가 같으면 => 균형잡힌 괄호 문자열
    // + (와 ) 짝도 맞으면 => 올바른 괄호 문자열

    public String solution(String p) {
        String answer = remake(p);
        return answer;
    }

    private String remake(String str) {
        StringBuilder res = new StringBuilder();

        if (str.isBlank()) {
            return "";
        }

        int s = 0;
        int e = 0;
        int count = 0;
        while (e < str.length()) {
            if ('(' == str.charAt(e)) {
                count++;
            }
            if (')' == str.charAt(e)) {
                count--;
            }

            e++;

            if (count == 0) {
                String sub = str.substring(s, e);
                // 올바른 괄호 문자열이면 그대로
                if (isValid(sub)) {
                    res.append(sub);
                    s = e;
                }
                // 아니라면
                else {
                    StringBuilder u = new StringBuilder();
                    u.append("(").append(remake(str.substring(e))).append(")");
                    // sub의 첫번째 마지막 제거하고,
                    sub = sub.substring(1, sub.length() - 1);
                    // 괄호 전부 뒤집어서 붙이기
                    for (int i = 0; i < sub.length(); i++) {
                        u.append(sub.charAt(i) == ')' ? '(' : ')');
                    }
                    res.append(u.toString());
                    break;
                }
            }
        }
        return res.toString();
    }

    private boolean isValid(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if ('(' == str.charAt(i)) {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

}
