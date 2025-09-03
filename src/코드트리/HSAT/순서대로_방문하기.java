package 코드트리.HSAT;

import java.util.Scanner;

public class 순서대로_방문하기 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int n, m, answer;
    static int[][] grid;
    static int[][] targets;
    static boolean[][] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        targets = new int[m][2];
        for (int i = 0; i < m; i++) {
            targets[i][0] = sc.nextInt() - 1;
            targets[i][1] = sc.nextInt() - 1;
        }

        visit = new boolean[n][n];
        visit[targets[0][0]][targets[0][1]] = true;
        dfs(targets[0][0], targets[0][1], 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int targetIdx) {
        if (targetIdx == m) {
            answer++;
            return;
        }

        int ty = targets[targetIdx][0];
        int tx = targets[targetIdx][1];

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n || grid[ny][nx] == 1 || visit[ny][nx]) {
                continue;
            }

            visit[ny][nx] = true;
            if (ty == ny && tx == nx) {
                dfs(ny, nx, targetIdx + 1);
            } else {
                dfs(ny, nx, targetIdx);
            }
            visit[ny][nx] = false;
        }
    }
}
