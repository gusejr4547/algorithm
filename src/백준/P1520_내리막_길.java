package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1520_내리막_길 {
    static int H, W;
    static int[][] map, cnt;
    static boolean[][] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        cnt = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[H][W];
        int result = dfs(0, 0);
        System.out.println(result);
    }

    private static int dfs(int curY, int curX) {
        if (curY == H - 1 && curX == W - 1) {
            return 1;
        }
        if (visit[curY][curX]) {
            return cnt[curY][curX];
        }
        visit[curY][curX] = true;
        int way = 0;
        for (int d = 0; d < 4; d++) {
            int ny = curY + dy[d];
            int nx = curX + dx[d];
            if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
                continue;
            }
            if (map[curY][curX] > map[ny][nx]) {
                way += dfs(ny, nx);
            }
        }

        return cnt[curY][curX] = way;
    }
}
