package 코드트리;

import java.util.*;

public class 정수_사각형_최장_증가_수열 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j, dp, grid));
            }
        }

        System.out.println(answer);
    }

    private static int dfs(int i, int j, int[][] dp, int[][] grid) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int mV = 0;
        for (int d = 0; d < 4; d++) {
            int ny = i + dy[d];
            int nx = j + dx[d];

            if (ny < 0 || nx < 0 || ny >= grid.length || nx >= grid[0].length)
                continue;

            if (grid[i][j] < grid[ny][nx]) {
                mV = Math.max(mV, dfs(ny, nx, dp, grid));
            }
        }

        return dp[i][j] = mV + 1;
    }
}
