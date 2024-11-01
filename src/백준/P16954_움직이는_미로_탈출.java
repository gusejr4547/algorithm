package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P16954_움직이는_미로_탈출 {
    static char[][] map;
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];

        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 처음 위치 => (7,0)
        int answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(7, 0));
        boolean[][] visit = new boolean[8][8];

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (cur.y <= 0) {
                return 1;
            }

            if (visit[cur.y][cur.x]) {
                continue;
            }
            visit[cur.y][cur.x] = true;

            for (int d = 0; d < 9; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= 8 || nx >= 8 || map[ny][nx] == '#') {
                    continue;
                }

                // 벽 이동
                ny = ny - 1;
                if (ny < 0 || map[ny][nx] != '#') {
                    queue.offer(new Pos(ny, nx));
                }
            }
        }

        return 0;
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
