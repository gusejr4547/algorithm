package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P5427_불 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            Point start = new Point(0, 0);

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '@') {
                        start = new Point(i, j);
                        map[i][j] = '.';
                    }
                }
            }

            int result = bfs(start, map, h, w);

            System.out.println(result != -1 ? result : "IMPOSSIBLE");
        }
    }

    private static int bfs(Point start, char[][] map, int h, int w) {

        ArrayDeque<Point> fireQueue = new ArrayDeque<>();
        ArrayDeque<Point> personQueue = new ArrayDeque<>();
        boolean[][] pVisit = new boolean[h][w];
        boolean[][] fVisit = new boolean[h][w];

        pVisit[start.y][start.x] = true;
        personQueue.offer(start);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '*') {
                    fireQueue.offer(new Point(i, j));
                    fVisit[i][j] = true;
                }
            }
        }

        int time = 1;
        while (!personQueue.isEmpty()) {

            // 불 이동
            List<Point> nextFire = new ArrayList<>();
            while (!fireQueue.isEmpty()) {
                Point cur = fireQueue.poll();

                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= h || nx >= w || fVisit[ny][nx] || map[ny][nx] == '#' || map[ny][nx] == '*') {
                        continue;
                    }

                    map[ny][nx] = '*';
                    fVisit[ny][nx] = true;
                    nextFire.add(new Point(ny, nx));
                }
            }
            fireQueue.addAll(nextFire);

            // 사람 이동
            List<Point> nextPerson = new ArrayList<>();
            while (!personQueue.isEmpty()) {
                Point cur = personQueue.poll();

                if (cur.y == 0 || cur.y == h - 1 || cur.x == 0 || cur.x == w - 1) {
                    return time;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= h || nx >= w || pVisit[ny][nx] || map[ny][nx] == '#' || map[ny][nx] == '*') {
                        continue;
                    }

                    pVisit[ny][nx] = true;
                    nextPerson.add(new Point(ny, nx));
                }
            }
            personQueue.addAll(nextPerson);

            time++;
        }


        return -1;
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
