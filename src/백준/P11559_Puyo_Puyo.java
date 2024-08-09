package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P11559_Puyo_Puyo {
    static char[][] field;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[12][6];
        for (int i = 0; i < 12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        int chain = 0;
        while (true) {
            visit = new boolean[12][6];
            int deletedPuyo = deletePuyo();
            if (deletedPuyo == 0) {
                break;
            }
            chain++;
            downPuyo();
        }

        System.out.println(chain);
    }

    private static void downPuyo() {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int j = 0; j < 6; j++) {
            stack.clear();
            for (int i = 11; i >= 0; i--) {
                if (field[i][j] != '.') {
                    stack.push(field[i][j]);
                }
            }
            int emptyCnt = 12 - stack.size();
            int idx = 0;
            while (emptyCnt-- > 0) {
                field[idx++][j] = '.';
            }
            for (int i = idx; i < 12; i++) {
                field[i][j] = stack.pop();
            }
        }
    }

    private static int deletePuyo() {
        int deletedCnt = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visit[i][j] || field[i][j] == '.') {
                    continue;
                }
                if (canDelete(i, j)) {
                    deletedCnt++;
                }
            }
        }

        return deletedCnt;
    }

    private static boolean canDelete(int y, int x) {
        ArrayDeque<Pos> posStack = new ArrayDeque<>();
        char color = field[y][x];
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(y, x));
        posStack.push(new Pos(y, x));

        int cnt = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (visit[cur.y][cur.x]) {
                continue;
            }
            visit[cur.y][cur.x] = true;
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6 || visit[ny][nx] || field[ny][nx] != color) {
                    continue;
                }
                queue.offer(new Pos(ny, nx));
                posStack.push(new Pos(ny, nx));

            }
        }

        // 지울수있음
        if (cnt >= 4) {
            while (!posStack.isEmpty()) {
                Pos cur = posStack.pop();
                field[cur.y][cur.x] = '.';
            }
            return true;
        }
        return false;
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
