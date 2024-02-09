package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P13460_구슬_탈출2 {
    static int N, M;
    static char[][] board;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Point red = null, blue = null, endPoint = null;
        board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'B')
                    blue = new Point(i, j);
                else if (board[i][j] == 'R')
                    red = new Point(i, j);
                else if (board[i][j] == 'O')
                    endPoint = new Point(i, j);
            }
        }

        System.out.println(bfs(red, blue, endPoint));
    }

    static int bfs(Point red, Point blue, Point end) {
        ArrayDeque<State> queue = new ArrayDeque<>();
        Set<Point[]> visit = new HashSet<>();
        queue.offer(new State(red, blue, 0));
        visit.add(new Point[]{red, blue});

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            Point currRed = curr.red;
            Point currBlue = curr.blue;

            if(curr.time > 10)
                break;

            // 4방향 기울이기
            for (int d = 0; d < 4; d++) {
                // red, blue
                int rY = currRed.y;
                int rX = currRed.x;
                int bY = currBlue.y;
                int bX = currBlue.x;

                boolean redEnd = false;
                boolean blueEnd = false;

                while (board[rY + dy[d]][rX + dx[d]] != '#') {
                    rY = rY + dy[d];
                    rX = rX + dx[d];

                    if (rY == end.y && rX == end.x) {
                        redEnd = true;
                        break;
                    }
                }

                while (board[bY + dy[d]][bX + dx[d]] != '#') {
                    bY = bY + dy[d];
                    bX = bX + dx[d];

                    if (bY == end.y && bX == end.x) {
                        blueEnd = true;
                        break;
                    }
                }

                if (blueEnd) continue;
                if (redEnd && !blueEnd) return curr.time + 1;

                if (rY == bY && rX == bX) {
                    if (d == 0) {
                        if (currRed.x < currBlue.x)
                            rX -= dx[d];
                        else
                            bX -= dx[d];
                    } else if (d == 1) {
                        if (currRed.x < currBlue.x)
                            bX -= dx[d];
                        else
                            rX -= dx[d];
                    } else if (d == 2) {
                        if (currRed.y < currBlue.y)
                            rY -= dy[d];
                        else
                            bY -= dy[d];
                    } else if (d == 3) {
                        if (currRed.y < currBlue.y)
                            bY -= dy[d];
                        else
                            rY -= dy[d];
                    }
                }

                Point nextRed = new Point(rY, rX);
                Point nextBlue = new Point(bY, bX);
                if (!visit.contains(new Point[]{nextRed, nextBlue})) {
                    visit.add(new Point[]{nextRed, nextBlue});
                    queue.offer(new State(nextRed, nextBlue, curr.time + 1));
                }
            }
        }
        return -1;
    }

    static class State {
        Point red;
        Point blue;
        int time;

        public State(Point red, Point blue, int time) {
            this.red = red;
            this.blue = blue;
            this.time = time;
        }
    }

    static class Point {
        int y;
        int x;

        public Point() {
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
