package 프로그래머스;

import java.util.HashSet;
import java.util.Set;

public class N으로_표현 {
    public static void main(String[] args) {
        N으로_표현 Main = new N으로_표현();
        int N = 5;
        int number = 12;
        System.out.println(Main.solution(N, number));
    }

    public int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];
        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }

        dp[1].add(N);

        if (dp[1].contains(number)) {
            return 1;
        }

        // i는 사용할 수 있는 N 개수
        for (int i = 2; i <= 8; i++) {
            // NN...형태의 숫자
            String numStr = String.valueOf(N).repeat(i);
            dp[i].add(Integer.parseInt(numStr));

            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int num1 : dp[j]) {
                    for (int num2 : dp[k]) {
                        // num1, num2 사칙연산
                        dp[i].add(num1 + num2);
                        dp[i].add(num1 - num2);
                        dp[i].add(num1 * num2);
                        if (num2 != 0) {
                            dp[i].add(num1 / num2);
                        }
                    }
                }
            }

            if (dp[i].contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
