package 코드트리;

import java.util.*;

public class 방향에_맞춰_최대로_움직이기 {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int n, answer;
    static int[][] num, moveDir;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        moveDir = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moveDir[i][j] = sc.nextInt() - 1;
            }
        }
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        // Please write your code here.

        dfs(r, c, 0);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int count) {
        answer = Math.max(answer, count);

        int value = num[y][x];
        int dir = moveDir[y][x];

        int ny = y;
        int nx = x;

        while (true) {
            ny = ny + dy[dir];
            nx = nx + dx[dir];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                break;
            }

            if (num[ny][nx] > value) {
                dfs(ny, nx, count + 1);
            }
        }

        return;
    }
}
