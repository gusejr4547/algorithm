package 프로그래머스.코드챌린지_본선_2025;

import java.util.*;

public class 가장_큰_삼각형_덩어리 {
    public static void main(String[] args) {
        가장_큰_삼각형_덩어리 Main = new 가장_큰_삼각형_덩어리();
//        int[][] grid = {{-1, -1, -1}, {1, 1, -1}, {1, 1, 1}};
        int[][] grid = {{1, -1, 1, -1}, {1, 1, -1, -1}, {-1, -1, 1, 1}, {-1, -1, 1, -1}};
        System.out.println(Main.solution(grid));
    }

    /*
    격자의 각 칸은 한 변의 길이가 √2인 정사각형
    각 칸 안에는 대각선이 하나 그어져 있습니다. 이 대각선은 / 방향(1) 또는 \ 방향(-1) 중 하나입니다.
    각 칸에서 두 삼각형 중 정확히 하나만 색칠할 수 있습니다.
    색칠된 삼각형들은 한 '변'을 공유해야 서로 연결되며, 이렇게 연결된 삼각형들의 집합을 하나의 삼각형 덩어리

    가장큰 삼각형 덩어리 넓이?

    N*M <= 200_000 --> 전체 삼각형 블럭 최대 400_000이다

    그래프로 변환 가능?
    dir = 방향 1의 왼쪽 0, 오른쪽 1, 방향 -1의 왼쪽 2, 오른쪽 3
     */

    int N, M;
    int[][][] map;
    int[][] visit;
    // 우, 좌, 하, 상
    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(int[][] grid) {
        N = grid.length;
        M = grid[0].length;
        map = new int[N][M][2];
        visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    // /
                    map[i][j][0] = 1;
                    map[i][j][1] = 2;
                } else {
                    // \
                    map[i][j][0] = 3;
                    map[i][j][1] = 4;
                }
            }
        }

        int searchCnt = 1;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 2; k++) {
                    if (visit[i][j] == 0) {
                        int result = getSize(i, j, k, searchCnt, grid);
                        answer = Math.max(answer, result);

                        searchCnt++;
                    }
                }
            }
        }

        return answer;
    }

    private int getSize(int startY, int startX, int startDir, int searchCnt, int[][] grid) {
        ArrayDeque<TriangleBlock> queue = new ArrayDeque<>();
        queue.offer(new TriangleBlock(startY, startX, startDir));
        visit[startY][startX] = searchCnt;

        int sum = 0;
        while (!queue.isEmpty()) {
            TriangleBlock cur = queue.poll();

            sum++;

            int trianglePos = map[cur.y][cur.x][cur.dir];
            List<int[]> nextArr = getNext(cur, trianglePos, grid);

            for (int[] next : nextArr) {
                int nextY = next[0];
                int nextX = next[1];
                int nextPos = next[2]; // 1, 2, 3, 4
                int nextDir = nextPos % 2 == 1 ? 0 : 1;

                if (!isValid(nextY, nextX)) continue;

                if (visit[nextY][nextX] == searchCnt) continue;

                visit[nextY][nextX] = searchCnt;
                queue.offer(new TriangleBlock(nextY, nextX, nextDir));
            }
        }

        return sum;
    }

    private List<int[]> getNext(TriangleBlock triangleBlock, int trianglePos, int[][] grid) {
        int y = triangleBlock.y;
        int x = triangleBlock.x;
        int dir = triangleBlock.dir;
        List<int[]> nextDir = new ArrayList<>(); // 최대 2가지 방향으로 갈 수 있음. (y,x,Pos)

        if (trianglePos == 1) {
            // 좌, 상
            int nextY = y + dy[1];
            int nextX = x + dx[1];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 2 : 4;
                next[2] = nextPos;
                nextDir.add(next);
            }

            nextY = y + dy[3];
            nextX = x + dx[3];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 2 : 3;
                next[2] = nextPos;
                nextDir.add(next);
            }

        } else if (trianglePos == 2) {
            // 우, 하
            int nextY = y + dy[0];
            int nextX = x + dx[0];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 1 : 3;
                next[2] = nextPos;
                nextDir.add(next);
            }

            nextY = y + dy[2];
            nextX = x + dx[2];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 1 : 4;
                next[2] = nextPos;
                nextDir.add(next);
            }
        } else if (trianglePos == 3) {
            // 좌, 하
            int nextY = y + dy[1];
            int nextX = x + dx[1];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 2 : 4;
                next[2] = nextPos;
                nextDir.add(next);
            }

            nextY = y + dy[2];
            nextX = x + dx[2];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 1 : 4;
                next[2] = nextPos;
                nextDir.add(next);
            }
        } else {
            // 우, 상
            int nextY = y + dy[0];
            int nextX = x + dx[0];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 1 : 3;
                next[2] = nextPos;
                nextDir.add(next);
            }

            nextY = y + dy[3];
            nextX = x + dx[3];
            if (isValid(nextY, nextX)) {
                int[] next = new int[3];
                next[0] = nextY;
                next[1] = nextX;
                int nextPos = grid[nextY][nextX] == 1 ? 2 : 3;
                next[2] = nextPos;
                nextDir.add(next);
            }
        }

        return nextDir;
    }

    private boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    private class TriangleBlock {
        int y, x, dir;

        public TriangleBlock(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}
