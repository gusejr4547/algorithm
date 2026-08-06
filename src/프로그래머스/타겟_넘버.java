package 프로그래머스;

import java.util.Arrays;

public class 타겟_넘버 {
    public static void main(String[] args) {

    }

    int[][] memo;
    int offset;

    public int solution(int[] numbers, int target) {

        // numbers.length <= 20
        // numbers[] <= 50
        // target <= 1000

        // 전부 해보면 2^20
        // 중복되는거 memo

        int totalSum = 0;
        for (int n : numbers) {
            totalSum += n;
        }
        offset = totalSum;

        memo = new int[numbers.length][totalSum * 2 + 1];
        for (int i = 0; i < numbers.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(0, 0, numbers, target);
    }

    private int dfs(int idx, int cur, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (cur == target) {
                return 1;
            }
            return 0;
        }

        int sumIdx = cur + offset;

        if (memo[idx][sumIdx] != -1) {
            return memo[idx][sumIdx];
        }


        // +
        int plus = dfs(idx + 1, cur + numbers[idx], numbers, target);

        // -
        int minus = dfs(idx + 1, cur - numbers[idx], numbers, target);

        return memo[idx][sumIdx] = plus + minus;
    }
}
