package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1799_비숍 {
    static int N, blackCnt, whiteCnt;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {1, 1, -1, -1};
    static int[] dx = {1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[N][N];
        dfs(0, 0, 0, 0);
        visit = new boolean[N][N];
        dfs(0, 1, 1, 0);

        System.out.println(blackCnt + whiteCnt);
    }

    private static void dfs(int r, int c, int color, int cnt) {
        if (c >= N) {
            r += 1;
            c = r % 2 == color ? 0 : 1;
        }

        if (r >= N) {
            if (color == 0) {
                blackCnt = Math.max(blackCnt, cnt);
            } else {
                whiteCnt = Math.max(whiteCnt, cnt);
            }
            return;
        }

        if (isValid(r, c)) {
            visit[r][c] = true;
            dfs(r, c + 2, color, cnt + 1);
            visit[r][c] = false;
        }

        dfs(r, c + 2, color, cnt);
    }


    private static boolean isValid(int y, int x) {
        if (map[y][x] == 0) {
            return false;
        }

        for (int d = 0; d < 4; d++) {
            int ny = y;
            int nx = x;

            while (true) {
                ny += dy[d];
                nx += dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    break;
                }

                if (visit[ny][nx]) {
                    return false;
                }
            }
        }

        return true;
    }
}
