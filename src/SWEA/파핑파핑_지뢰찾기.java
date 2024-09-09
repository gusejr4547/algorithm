package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class 파핑파핑_지뢰찾기 {
    static int N, cnt;
    static char[][] map;
    static StringBuilder sb;
    // 8방향, 위 부터 시계방향
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }
            solution();
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solution() {
        // 0인거 열기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.' && isZero(i, j)) {
                    map[i][j] = '0';
                }
            }
        }

        boolean[][] visit = new boolean[N][N];

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 주변 지뢰 없는거 먼저 제거
                if (map[i][j] == '0' && !visit[i][j]) {
                    cnt++;
                    bfs(i, j, visit);
                }
            }
        }

        // 남은 . 세기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.') {
                    cnt++;
                }
            }
        }
    }

    private static boolean isZero(int i, int j) {
        for (int d = 0; d < 8; d++) {
            int ny = i + dy[d];
            int nx = j + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }

            if (map[ny][nx] == '*') {
                return false;
            }
        }
        return true;
    }

    private static void bfs(int i, int j, boolean[][] visit) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(i, j));

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (visit[cur.y][cur.x]) {
                continue;
            }
            visit[cur.y][cur.x] = true;

            if (map[cur.y][cur.x] == '0') {
                for (int d = 0; d < 8; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) {
                        continue;
                    }
                    queue.offer(new Pos(ny, nx));
                }
            } else {
                map[cur.y][cur.x] = 'V';
            }
        }

//        System.out.println("=======" + cnt + "======");
//        for (char[] d : map) {
//            System.out.println(Arrays.toString(d));
//        }
//        System.out.println("===============");
    }

    private static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
