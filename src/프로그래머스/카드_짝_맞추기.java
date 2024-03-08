package 프로그래머스;

import java.util.ArrayDeque;

public class 카드_짝_맞추기 {
    public static void main(String[] args) {
        카드_짝_맞추기 Main = new 카드_짝_맞추기();
        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r = 1;
        int c = 0;
        System.out.println(Main.solution(board, r, c));
    }

    boolean[] isNum;
    Point[][] cardPos;
    int totalCardCnt;
    int answer;

    public int solution(int[][] board, int r, int c) {
        answer = Integer.MAX_VALUE;
        cardPos = new Point[7][2];
        isNum = new boolean[7];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                if (!isNum[board[i][j]]) {
                    isNum[board[i][j]] = true;
                    totalCardCnt++;
                    cardPos[board[i][j]][0] = new Point(i, j);
                } else {
                    cardPos[board[i][j]][1] = new Point(i, j);
                }
            }
        }

        permutation(board, r, c, 0, 0);

        return answer;
    }


    public int bfs(int[][] board, int sy, int sx, int ey, int ex) {
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        ArrayDeque<State> queue = new ArrayDeque<>();

        boolean[][] visit = new boolean[4][4];
        queue.offer(new State(sy, sx, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int currY = curr.y;
            int currX = curr.x;

            if (currY == ey && currX == ex)
                return curr.controlCnt;

            if (visit[currY][currX])
                continue;
            visit[currY][currX] = true;

            // 4방향 방향키
            for (int d = 0; d < 4; d++) {
                int ny = currY + dy[d];
                int nx = currX + dx[d];

                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || visit[ny][nx])
                    continue;
                queue.offer(new State(ny, nx, curr.controlCnt + 1));
            }

            // 4방향 ctrl+방향키
            for (int d = 0; d < 4; d++) {
                int y = currY;
                int x = currX;
                while (true) {
                    y += dy[d];
                    x += dx[d];
                    if (y < 0 || x < 0 || y >= 4 || x >= 4) {
                        y -= dy[d];
                        x -= dx[d];
                        break;
                    }
                    if (board[y][x] > 0)
                        break;
                }

                if (visit[y][x])
                    continue;
                queue.offer(new State(y, x, curr.controlCnt + 1));
            }
        }

        return -1;
    }

    // 카드 뒤집는 순서 순열
    public void permutation(int[][] board, int r, int c, int result, int count) {
        if (count == totalCardCnt) {
            answer = Math.min(answer, result);
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (!isNum[i])
                continue;

            int moveCnt1 = bfs(board, r, c, cardPos[i][0].y, cardPos[i][0].x)
                    + bfs(board, cardPos[i][0].y, cardPos[i][0].x, cardPos[i][1].y, cardPos[i][1].x) + 2;
            int moveCnt2 = bfs(board, r, c, cardPos[i][1].y, cardPos[i][1].x)
                    + bfs(board, cardPos[i][1].y, cardPos[i][1].x, cardPos[i][0].y, cardPos[i][0].x) + 2;


            isNum[i] = false;
            board[cardPos[i][0].y][cardPos[i][0].x] = 0;
            board[cardPos[i][1].y][cardPos[i][1].x] = 0;
            if (moveCnt1 < moveCnt2)
                permutation(board, cardPos[i][1].y, cardPos[i][1].x, result + moveCnt1, count + 1);
            else
                permutation(board, cardPos[i][0].y, cardPos[i][0].x, result + moveCnt2, count + 1);
            board[cardPos[i][0].y][cardPos[i][0].x] = i;
            board[cardPos[i][1].y][cardPos[i][1].x] = i;
            isNum[i] = true;
        }
    }

    class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    class State {
        int y;
        int x;
        int controlCnt;

        public State(int y, int x, int controlCnt) {
            this.y = y;
            this.x = x;
            this.controlCnt = controlCnt;
        }
    }
}
