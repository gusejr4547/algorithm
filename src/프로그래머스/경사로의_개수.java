package 프로그래머스;

import java.util.Arrays;

public class 경사로의_개수 {
    public static void main(String[] args) {
        경사로의_개수 Main = new 경사로의_개수();
        int[][] grid = {{3, 4, 6, 5, 3}, {3, 5, 5, 3, 6}, {5, 6, 4, 3, 6}, {7, 4, 3, 5, 0}};
        int[] d = {1, -2, -1, 0, 2};
        int k = 2;
        System.out.println(Main.solution(grid, d, k));
    }

    final int MOD = 1_000_000_007;
    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};
    long[][][] dp;
    int n, m;

    public int solution(int[][] grid, int[] d, int k) {

        n = grid.length;
        m = grid[0].length;
        dp = new long[d.length + 1][n * m][n * m]; // dp[a][b][c] a번 이동, b->c로 경우의수
        for (int i = 0; i < n * m; i++) {
            dp[0][i][i] = 1;
        }
        findWay(grid, d);
        long[][] result = kTimeIter(k);

        long answer = 0;
        for (int y = 0; y < n * m; y++) {
            for (int x = 0; x < n * m; x++) {
                answer = (answer + result[y][x]) % MOD;
            }
        }

        return (int) answer;
    }

    private void findWay(int[][] grid, int[] d) {
        for (int turn = 1; turn <= d.length; turn++) {
            // i에서 출발
            for (int i = 0; i < n * m; i++) {
                int y = i / m;
                int x = i % m;

                // 4방향 탐색
                for (int dir = 0; dir < 4; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= m)
                        continue;

                    // 기울기
                    int incline = grid[ny][nx] - grid[y][x];
                    if (d[turn - 1] == incline) {
                        // 기울기 같으면
                        for (int start = 0; start < n * m; start++) {
                            // start -> ny * m + nx 경우의 수에 start -> i 경우의 수 더하기
                            dp[turn][start][ny * m + nx] = (dp[turn][start][ny * m + nx] + dp[turn - 1][start][i]) % MOD;
                        }
                    }
                }
            }
        }
    }

    private long[][] kTimeIter(int k) {
        long[][] wayCntMatrix = dp[dp.length - 1]; // 1회전으로 갈 수 있는 출발 -> 도착 지점
        // k번 행렬 곱셈하면 됨.
        return matrixPow(wayCntMatrix, k);
    }

    private long[][] matrixPow(long[][] wayCntMatrix, int k) {
        if (k == 1) return wayCntMatrix;

        long[][] matrix = matrixPow(wayCntMatrix, k / 2);
        long[][] result = matrixMulti(matrix, matrix);

        if (k % 2 == 1) {
            result = matrixMulti(result, wayCntMatrix);
        }
        return result;
    }

    private long[][] matrixMulti(long[][] a, long[][] b) {
        int len = n * m;
        long[][] result = new long[len][len];

        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                for (int k = 0; k < len; k++) {
                    result[y][x] += (a[y][k] * b[k][x]) % MOD;
                    result[y][x] %= MOD;
                }
            }
        }

        return result;
    }
}
