package 프로그래머스;

import java.util.Arrays;

public class 거스름돈 {
    public static void main(String[] args) {
        거스름돈 Main = new 거스름돈();
        int n = 5;
        int[] money = {1, 2, 5};
        System.out.println(Main.solution(n, money));
    }

    public int solution(int n, int[] money) {
        int answer = 0;
        int[] memo = new int[n + 1];
        memo[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                memo[i] += memo[i - m];
            }
        }

        answer = memo[n];
        return answer;
    }
}
