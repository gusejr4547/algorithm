package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 아이템_줍기 {
    public static void main(String[] args) {
        아이템_줍기 Main = new 아이템_줍기();
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        System.out.println(Main.solution(rectangle, characterX, characterY, itemX, itemY));
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = new int[101][101];

        // 길 만들기, 테두리가 좌표에서 1칸 차이나면 탐색할 때 오류 생김, 4각형 2배 늘리고 나중에 2배 줄임
        for (int[] rect : rectangle) {
            int[] newRect = new int[4];
            for (int i = 0; i < 4; i++) {
                newRect[i] = rect[i] * 2;
            }
            drawOuterLine(newRect, board);
        }
        return findPath(characterX * 2, characterY * 2, itemX * 2, itemY * 2, board) / 2;
    }

    private int findPath(int characterX, int characterY, int itemX, int itemY, int[][] board) {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[board.length][board[0].length];
        queue.offer(new Point(characterY, characterX, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.y == itemY && now.x == itemX) {
                return now.len;
            }
            visit[now.y][now.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= 101 || nx >= 101 || visit[ny][nx] || board[ny][nx] != 1) {
                    continue;
                }
                queue.offer(new Point(ny, nx, now.len + 1));
            }
        }
        return -1;
    }

    private void drawOuterLine(int[] newRect, int[][] board) {
        for (int x = newRect[0]; x <= newRect[2]; x++) {
            for (int y = newRect[1]; y <= newRect[3]; y++) {
                // 테두리
                if (x == newRect[0] || x == newRect[2] || y == newRect[1] || y == newRect[3]) {
                    if (board[y][x] != 2) {
                        board[y][x] = 1;
                    }
                } else {
                    board[y][x] = 2;
                }
            }
        }
    }

    static class Point {
        int y;
        int x;
        int len;

        public Point(int y, int x, int len) {
            this.y = y;
            this.x = x;
            this.len = len;
        }
    }
}
