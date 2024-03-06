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

    public int solution(int[][] board, int r, int c) {
        cardPos = new Point[7][2];
        isNum = new boolean[7];
        int size = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                if (!isNum[board[i][j]]) {
                    isNum[board[i][j]] = true;
                    size++;
                    cardPos[board[i][j]][0] = new Point(i, j);
                } else {
                    cardPos[board[i][j]][1] = new Point(i, j);
                }
            }
        }

        permutation(board, r, c, 0, new int[size], new boolean[size]);

        int answer = 0;
        return answer;
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int bfs(int[][] board, int sy, int sx, int ey, int ex) {
        ArrayDeque<State> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[4][4];
        boolean[][] flip = new boolean[4][4];
        int idx = 0;
        queue.offer(new State(y, x, 0));

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                State curr = queue.poll();
                int currY = curr.y;
                int currX = curr.x;
            }


            if (visit[currY][currX])
                continue;
            visit[currY][currX] = true;

            // 카드 뒤집기
            if (board[currY][currX])

                // 4방향 방향키
                for (int d = 0; d < 4; d++) {
                    int ny = currY + dy[d];
                    int nx = currX + dx[d];

                    if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || visit[ny][nx])
                        continue;


                }

            // 4방향 ctrl+방향키


        }
    }

    // 카드 뒤집는 순서 순열
    public void permutation(int[][] board, int r, int c, int count, int[] numArr) {
        if (count == numArr.length) {
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (!isNum[i])
                continue;

            int moveCnt1 = bfs(board, r, c, cardPos[i][0].y, cardPos[i][0].x)
                    + bfs(board, cardPos[i][0].y, cardPos[i][0].x, cardPos[i][1].y, cardPos[i][1].x);
            int moveCnt2 = bfs(board, r, c, cardPos[i][1].y, cardPos[i][1].x)
                    + bfs(board, cardPos[i][1].y, cardPos[i][1].x, cardPos[i][0].y, cardPos[i][0].x);


            isNum[i] = false;
            numArr[count] = i;
            permutation(board, r, c, count + 1, numArr);
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

    class State implements Comparable<State> {
        int y;
        int x;
        int controlCnt;

        public State(int y, int x, int controlCnt) {
            this.y = y;
            this.x = x;
            this.controlCnt = controlCnt;
        }

        @Override
        public int compareTo(State o) {
            return this.controlCnt - o.controlCnt;
        }
    }
}
