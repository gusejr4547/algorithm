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

        Point red, blue, endPoint;
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


    }

    static int bfs(Point red, Point blue) {
        ArrayDeque<State> queue = new ArrayDeque<>();
        Set<Point[]> visit = new HashSet<>();
        queue.offer(new State(red, blue, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            Point currRed = curr.red;
            Point currBlue = curr.blue;

            if (visit.contains(new Point[]{currRed, currBlue})) continue;
            visit.add(new Point[]{red, blue});


            // 4방향 기울이기
            for (int d = 0; d < 4; d++) {
                // red, blue
                int rY = currRed.y;
                int rX = currRed.x;
                int bY = currBlue.y + dy[d];
                int bX = currBlue.x + dx[d];
                while (board[rY + dy[d]][rX + dx[d]] != '#') {

                }
            }
        }
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

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
