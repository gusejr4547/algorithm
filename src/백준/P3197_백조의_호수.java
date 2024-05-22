package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3197_백조의_호수 {
    static int R, C;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] visit = new boolean[R][C];
        Point[] swans = new Point[2];

        Queue<Point> waterQueue = new ArrayDeque<>();
        Queue<Point> searchQueue = new ArrayDeque<>();
        // R, C <= 1500 이면 2,250,000
        int swanIdx = 0;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    swans[swanIdx++] = new Point(i, j);
                }
                if (map[i][j] != 'X') {
                    waterQueue.add(new Point(i, j));
                }
            }
        }

        // bfs
        searchQueue.add(swans[0]);
//        visit[swans[0].y][swans[0].x] = true;

        int day = 0;

        while (true) {
            // bfs로 만날수있는지 확인
            if (isMeet(searchQueue, swans[1], map, visit)) {
                break;
            }

            // 얼음 녹이기
            melting(waterQueue, map);

            day++;
        }


        System.out.println(day);
    }

    public static boolean isMeet(Queue<Point> searchQueue, Point end, char[][] map, boolean[][] visit) {
        Queue<Point> nextSearchQueue = new ArrayDeque<>();
        while (!searchQueue.isEmpty()) {
            Point now = searchQueue.poll();

            if (now.y == end.y && now.x == end.x) {
                return true;
            }

            if (visit[now.y][now.x]) {
                continue;
            }
            visit[now.y][now.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C || visit[ny][nx]) {
                    continue;
                }

                // 얼음이면 다음 탐색
                if (map[ny][nx] == 'X') {
                    nextSearchQueue.add(new Point(ny, nx));
                }
                // 물이면 계속 탐색
                else {
                    searchQueue.add(new Point(ny, nx));
                }
            }
        }
        searchQueue.addAll(nextSearchQueue);

        return false;
    }

    public static void melting(Queue<Point> waterQueue, char[][] map) {
        Queue<Point> nextWaterQueue = new ArrayDeque<>();
        while (!waterQueue.isEmpty()) {
            Point now = waterQueue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] != 'X') {
                    continue;
                }

                map[ny][nx] = '.';
                nextWaterQueue.add(new Point(ny, nx));
            }
        }
        waterQueue.addAll(nextWaterQueue);
    }

    public static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
