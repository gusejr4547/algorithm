package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P12946_육각_보드 {
    // X 는 색칠해야하는 곳
    // N <= 50
    // 인접한 곳은 같은색으로 칠할 수 없다

    static int N, answer;
    static char[][] grid;
    static int[][] coloring;
    // 인접한 6방향
    static int[] dy = {-1, -1, 0, 0, 1, 1};
    static int[] dx = {0, 1, 1, -1, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        coloring = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(coloring[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 'X' && coloring[i][j] == -1) {
                    // 이러면 무조건 1가지 색 필요
                    answer = Math.max(answer, 1);

                    // 2가지 색으로 칠해봄
                    dfs(i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }


    private static void dfs(int r, int c, int color) {
        coloring[r][c] = color;

        // 6방향
        for (int d = 0; d < 6; d++) {
            int nr = r + dy[d];
            int nc = c + dx[d];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || grid[nr][nc] != 'X') {
                continue;
            }
            // 아직 칠하지 않은 인접
            if (coloring[nr][nc] == -1) {
                answer = Math.max(answer, 2);
                dfs(nr, nc, 1 - color);
            }
            // 칠한 인접
            else {
                // 같은 색? => 3가지 색 필요
                if (coloring[nr][nc] == color) {
                    answer = Math.max(answer, 3);
                    // 더 탐색 x
                    return;
                }
            }
        }
    }
}