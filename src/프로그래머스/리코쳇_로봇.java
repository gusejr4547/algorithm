package 프로그래머스;

import java.util.PriorityQueue;

public class 리코쳇_로봇 {
    public static void main(String[] args) {
        리코쳇_로봇 Main = new 리코쳇_로봇();
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(Main.solution(board));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(String[] board) {
        int answer = -1;
        int[] start = new int[2];

        char[][] map = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
        }

        int N = map.length;
        int M = map[0].length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    start = new int[]{i, j};
                }
            }
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>((e1, e2) -> e1.move - e2.move);
        boolean[][] visit = new boolean[N][M];
        pq.offer(new Pos(start[0], start[1], 0));

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();

            if (map[curr.y][curr.x] == 'G') {
                answer = curr.move;
                break;
            }

            if (visit[curr.y][curr.x]) {
                continue;
            }
            visit[curr.y][curr.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = curr.y;
                int nx = curr.x;
                while (true) {
                    ny += dy[d];
                    nx += dx[d];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 'D') {
                        pq.offer(new Pos(ny - dy[d], nx - dx[d], curr.move + 1));
                        break;
                    }
                }
            }
        }


        return answer;
    }

    class Pos {
        int y;
        int x;
        int move;

        Pos(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
