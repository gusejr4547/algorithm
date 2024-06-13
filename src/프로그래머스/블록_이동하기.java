package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 블록_이동하기 {
    public static void main(String[] args) {
        블록_이동하기 Main = new 블록_이동하기();
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(Main.solution(board));
    }

    // 로봇은 2 x 1 크기의 로봇
    // 회전 가능, 회전 하는 방향에 벽이 없어야함

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    int[][] map;
    int N;

    public int solution(int[][] board) {
        N = board.length;
        map = new int[N + 2][N + 2];

        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i + 1][j + 1] = board[i][j];
            }
        }

        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }


        int answer = bfs();
        return answer;
    }

    public int bfs() {
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        boolean[][][] visit = new boolean[N + 2][N + 2][2];
        Queue<Robot> queue = new ArrayDeque<>();
        // (1,1)에 가로방향
        Robot robot = new Robot(1, 1, HORIZONTAL, 0);
        queue.offer(robot);

        // 이동 => 4가지
        // 회전 => 4가지
        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            // 종료
            if (cur.y == N - 1 && cur.x == N && cur.direction == VERTICAL ||
                    cur.y == N && cur.x == N - 1 && cur.direction == HORIZONTAL) {
                return cur.time;
            }

            // 방문
            if (visit[cur.y][cur.x][cur.direction]) {
                continue;
            }
            visit[cur.y][cur.x][cur.direction] = true;

            // 이동
            if (cur.direction == HORIZONTAL) {
                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];

                    // 방문
                    if (canGo(ny, nx, cur.direction)) {

                    }
                }
            } else {

            }


            // 회전
        }
        return -1;
    }

    public boolean canGo(int y, int x, int direction) {
        if (direction == HORIZONTAL) {
            // map 범위 벗어나는지

            // 벽이 있는지

        } else {
            // map 범위 벗어나는지

            // 벽이 있는지

        }
    }

    public class Robot {
        int y;
        int x;
        int direction;
        int time;

        public Robot(int y, int x, int direction, int time) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.time = time;
        }
    }
}
