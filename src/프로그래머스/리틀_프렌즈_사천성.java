package 프로그래머스;

import java.util.*;

public class 리틀_프렌즈_사천성 {
    public static void main(String[] args) {
        리틀_프렌즈_사천성 Main = new 리틀_프렌즈_사천성();
        int m = 3;
        int n = 3;
        String[] board = {"DBA", "C*A", "CDB"};
        System.out.println(Main.solution(m, n, board));
    }

    // 같은 글자로 이루어진 타일은 한 테스트 케이스에 항상 두 개씩만 존재한다.
    int M, N;
    char[][] map;
    boolean[] visit;
    Map<Character, int[]> block;

    public String solution(int m, int n, String[] board) {
        M = m;
        N = n;
        map = new char[m][n];

        block = new HashMap<>();
        // 남은 block 알파벳 true
        visit = new boolean[26];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if ('A' <= map[i][j] && map[i][j] <= 'Z') {
                    if (!visit[map[i][j] - 'A']) {
                        block.put(map[i][j], new int[]{i, j});
                        visit[map[i][j] - 'A'] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            boolean isRemoved = false;
            for (int i = 0; i < visit.length; i++) {
                if (visit[i]) {
                    // true이면 블럭 있음 => 지우기 시도
                    char target = (char) ('A' + i);
                    int[] s = block.get(target);
                    BlockPair blockPair = bfs(s[0], s[1]);
                    if (blockPair != null) {
                        map[blockPair.ay][blockPair.ax] = '.';
                        map[blockPair.by][blockPair.bx] = '.';
                        visit[target - 'A'] = false;
                        block.remove(target);
                        isRemoved = true;
                        sb.append(target);
                        break;
                    }
                }
            }

            // 지우는데 실패하면
            if (!isRemoved) {
                // 남은 블럭이 있는 경우 impossible
                if (!block.isEmpty()) {
                    sb.setLength(0);
                    sb.append("IMPOSSIBLE");
                }
                break;
            }
            // 남은 블럭있나?
            if (block.isEmpty()) {
                break;
            }

            // 다시 알파벳 처음부터 지워지는지 확인
        }

        return sb.toString();
    }

    private BlockPair bfs(int sy, int sx) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        char target = map[sy][sx];
        ArrayDeque<Path> queue = new ArrayDeque<>();
        // 출발점에서 4방향으로 이동
        for (int i = 0; i < 4; i++) {
            int ny = sy + dy[i];
            int nx = sx + dx[i];
            if (ny >= 0 && nx >= 0 && ny < M && nx < N && map[ny][nx] != '*')
                queue.offer(new Path(ny, nx, i, 0));
        }

        while (!queue.isEmpty()) {
            Path cur = queue.poll();

            if (!(cur.y == sy && cur.x == sx) && map[cur.y][cur.x] != '.') {
                // 출발점을 제외하고 문자를 만남
                if (map[cur.y][cur.x] == target) {
                    return new BlockPair(sy, sx, cur.y, cur.x, target);
                } else {
                    // 다음으로 이동하지 않음
                    continue;
                }
            }

            // 직진
            int ny = cur.y + dy[cur.direction];
            int nx = cur.x + dx[cur.direction];
            if (ny >= 0 && nx >= 0 && ny < M && nx < N && map[ny][nx] != '*') {
                queue.offer(new Path(ny, nx, cur.direction, cur.turnCnt));
            }

            // 꺾기
            if (cur.turnCnt == 0) {
                // direction - 1
                int nxtDir = cur.direction - 1 < 0 ? 3 : cur.direction - 1;
                ny = cur.y + dy[nxtDir];
                nx = cur.x + dx[nxtDir];
                if (ny >= 0 && nx >= 0 && ny < M && nx < N && map[ny][nx] != '*') {
                    queue.offer(new Path(ny, nx, nxtDir, cur.turnCnt + 1));
                }

                // direction + 1
                nxtDir = cur.direction + 1 > 3 ? 0 : cur.direction + 1;
                ny = cur.y + dy[nxtDir];
                nx = cur.x + dx[nxtDir];
                if (ny >= 0 && nx >= 0 && ny < M && nx < N && map[ny][nx] != '*') {
                    queue.offer(new Path(ny, nx, nxtDir, cur.turnCnt + 1));
                }
            }
        }

        return null;
    }

    private class Path {
        int y, x;
        int direction;
        int turnCnt;

        public Path(int y, int x, int direction, int turnCnt) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.turnCnt = turnCnt;
        }
    }

    private class BlockPair {
        int ay, ax;
        int by, bx;
        char block;

        public BlockPair(int ay, int ax, int by, int bx, char block) {
            this.ay = ay;
            this.ax = ax;
            this.by = by;
            this.bx = bx;
            this.block = block;
        }
    }
}
