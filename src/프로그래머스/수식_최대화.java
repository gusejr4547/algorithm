package 프로그래머스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 수식_최대화 {
    public static void main(String[] args) {
        수식_최대화 Main = new 수식_최대화();
        String expression = "100-200*300-500+20";
        System.out.println(Main.solution(expression));
    }

    // 3가지 연산자가 주어짐.
    // 연산자의 계산 우선순위는 마음대로. 6가지 존재

    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                sb.append(expression.charAt(i));
            } else {
                nums.add(Long.parseLong(sb.toString()));
                ops.add(expression.charAt(i));
                sb.setLength(0);
            }
        }
        // 마지막 숫자 추가
        nums.add(Long.parseLong(sb.toString()));


        // 연산자 우선순위 미리 만들어놓기
        char[][] priorities = {
                {'+', '-', '*'}, {'+', '*', '-'},
                {'-', '+', '*'}, {'-', '*', '+'},
                {'*', '+', '-'}, {'*', '-', '+'}
        };

        long answer = 0;
        // 계산하기
        for (char[] priority : priorities) {
            List<Long> numList = new ArrayList<>(nums);
            List<Character> opList = new ArrayList<>(ops);

            for (char op : priority) {
                int i = 0;
                while (i < opList.size()) {
                    // 현재 연산자와 같으면 계산
                    if (opList.get(i) == op) {
                        long result = cal(numList.get(i), numList.get(i + 1), op);

                        // 기존꺼 지우고
                        numList.remove(i + 1);
                        opList.remove(i);
                        // 결과 넣기
                        numList.set(i, result);
                    } else {
                        i++;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(numList.get(0)));
        }

        return answer;
    }

    private static long cal(long a, long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}
