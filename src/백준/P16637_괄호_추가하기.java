package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// 연산자 우선순위는 동일함
// 괄호 안에 연산자는 1개만 있어야한다
public class P16637_괄호_추가하기 {
    static int N, answer;
    static List<Integer> nums;
    static List<Character> ops;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String seq = br.readLine();

        nums = new ArrayList<>();
        ops = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums.add(seq.charAt(i) - '0');
            } else {
                ops.add(seq.charAt(i));
            }
        }

        answer = Integer.MIN_VALUE;
        calTotal(0, nums.get(0));

        System.out.println(answer);
    }

    private static void calTotal(int idx, int total) {
        if (idx >= ops.size()) {
            answer = Math.max(answer, total);
            return;
        }

        // 다음 계산 괄호
        if (idx + 1 < ops.size()) {
            int nextVal = cal(nums.get(idx + 1), nums.get(idx + 2), ops.get(idx + 1));

            calTotal(idx + 2, cal(total, nextVal, ops.get(idx)));
        }

        // 다음 계산 괄호 x
        calTotal(idx + 1, cal(total, nums.get(idx + 1), ops.get(idx)));
    }

    private static int cal(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return 0;
    }

}
