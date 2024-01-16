package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class 최적의_행렬_곱셈 {
    public static void main(String[] args) {
        최적의_행렬_곱셈 Main = new 최적의_행렬_곱셈();
        int[][] matrix_sizes = {{5, 3}, {3, 10}, {10, 6}};
        System.out.println(Main.solution(matrix_sizes));
    }

    public int solution(int[][] matrix_sizes) {
        int size = matrix_sizes.length;
        int[][] dp = new int[size][size];

        int answer = dfs(0, size - 1, dp, matrix_sizes);

        return answer;
    }

    public int dfs(int start, int end, int[][] dp, int[][] matrix_sizes) {
        if (start == end) return 0;
        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int result = Integer.MAX_VALUE;

        for (int mid = start; mid < end; mid++) {
            int left = dfs(start, mid, dp, matrix_sizes);
            int right = dfs(mid + 1, end, dp, matrix_sizes);
            int curr = matrix_sizes[start][0] * matrix_sizes[mid][1] * matrix_sizes[end][1];
            int sum = left + right + curr;
            result = Math.min(result, sum);
        }

        return dp[start][end] = result;
    }

}
