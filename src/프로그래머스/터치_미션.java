package 프로그래머스;

import java.util.ArrayDeque;

public class 터치_미션 {
    public static void main(String[] args) {
        터치_미션 Main = new 터치_미션();
        int N = 6;
        String[][] board = {
                {"B", "O", "B", "B", "B", "B"},
                {"X", "X", "X", "X", "B", "B"},
                {"B", "B", "O", "B", "P", "B"},
                {"X", "X", "B", "X", "B", "B"},
                {"B", "B", "O", "X", "B", "B"},
                {"B", "B", "B", "B", "B", "B"}
        };
        System.out.println(Main.solution(N, board));
    }

    // NxN 보드판
    // 2개 이상의 타겟 지점
    // 내 위치는 P, 벽은 X, 비어있는공간 B, 타겟지점 O
    // 이동은 상하좌우
    // 도달할 수 있는 가장 최단거리가 먼 타겟지점에 도달해야 미션 클리어

    // 2가지 기술 중 1개를 사용할 수 있다. 이거 사용 이후에 이제 이동시작함.
    // 1. 특정 타겟 제거. 원하는 타겟을 한개 없앨 수 있다.
    // 2. 벽 없애기. 벽을 하나 없앨 수 있다.

    // N <= 40

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};
    Point start;

    public int solution(int N, String[][] board) {
        int answer = Integer.MAX_VALUE;

        start = new Point(0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("P")) {
                    start.y = i;
                    start.x = j;
                }
            }
        }

        // 타켓 제거
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("O")) {
                    board[i][j] = "B";
                    int res = bfs(N, board);
                    if (res != -1) {
                        answer = Math.min(answer, bfs(N, board));
                    }

                    board[i][j] = "O";
                }
            }
        }
        // 벽 제거
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("X")) {
                    board[i][j] = "B";
                    int res = bfs(N, board);
                    if (res != -1) {
                        answer = Math.min(answer, bfs(N, board));
                    }
                    board[i][j] = "X";
                }
            }
        }

        return answer == Integer.MAX_VALUE? -1 : answer;
    }

    private int bfs(int n, String[][] board) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
            }
        }

        ArrayDeque<Point> queue = new ArrayDeque<>();
        // 시작점 넣기
        queue.offer(start);
        dist[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx].equals("X") || dist[ny][nx] != -1) {
                    continue;
                }

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                queue.offer(new Point(ny, nx));
            }
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].equals("O")) {
                    max = Math.max(max, dist[i][j]);
                }
            }
        }

        return max;
    }

    private class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
