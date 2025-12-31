package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class P16638_괄호_추가하기_2 {
    static int N, maxResult = Integer.MIN_VALUE;
    static char[] seq;
    static List<Integer> nums;
    static List<Character> ops;
    static boolean[] calFirst;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = br.readLine().toCharArray();

        // 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값을 출력한다. 정답은 2^31보다 작고, -2^31보다 크다.
        // 괄호 안에는 연산자가 하나만 들어 있어야 한다
        // 정수는 모두 0보다 크거나 같고, 9보다 작거나 같다
        nums = new ArrayList<>();
        ops = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums.add(seq[i] - '0');
            } else {
                ops.add(seq[i]);
            }
        }

        calFirst = new boolean[ops.size()];
        makeSeq(0);

        System.out.println(maxResult);
    }

    private static void makeSeq(int idx) {
        if (idx >= ops.size()) {
            // 식 계산
            calSeq();
            return;
        }

        // 괄호 사용 => 바로 다음 연산자는 괄호 사용x
        calFirst[idx] = true;
        makeSeq(idx + 2);
        calFirst[idx] = false;

        // 괄호 사용 x
        makeSeq(idx + 1);
    }

    private static void calSeq() {
        ArrayDeque<Integer> numList = new ArrayDeque<>();
        ArrayDeque<Character> opList = new ArrayDeque<>();

        numList.offerLast(nums.get(0));
        // 괄호 계산
        for (int i = 0; i < ops.size(); i++) {
            if (calFirst[i]) {
                int a = numList.pollLast();
                int b = nums.get(i + 1);
                int result = cal(a, b, ops.get(i));
                numList.offerLast(result);
            } else {
                opList.offerLast(ops.get(i));
                numList.offerLast(nums.get(i + 1));
            }
        }

        numList.offerLast(numList.pollFirst());
        int len = opList.size();
        // 곱셈 처리
        for (int i = 0; i < len; i++) {
            char op = opList.pollFirst();
            if (op == '*') {
                int a = numList.pollLast();
                int b = numList.pollFirst();
                int result = cal(a, b, op);
                numList.offerLast(result);
            } else {
                opList.offerLast(op);
                numList.offerLast(numList.pollFirst());
            }
        }

        // 더하기 빼기
        int result = numList.pollFirst();
        len = opList.size();
        for (int i = 0; i < len; i++) {
            result = cal(result, numList.pollFirst(), opList.pollFirst());
        }

        maxResult = Math.max(maxResult, result);
    }

    static int cal(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}

