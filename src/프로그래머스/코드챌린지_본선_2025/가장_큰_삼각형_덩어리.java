package 프로그래머스.코드챌린지_본선_2025;

import java.util.*;

public class 가장_큰_삼각형_덩어리 {
    public static void main(String[] args) {
        가장_큰_삼각형_덩어리 Main = new 가장_큰_삼각형_덩어리();
        int[][] grid = {{-1, -1, -1}, {1, 1, -1}, {1, 1, 1}};
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

    int[][] dy = {{-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
    int[][] dx = {{0, -1}, {0, 1}, {0, -1}, {0, 1}};

    public int solution(int[][] grid) {
        // 초기화
        Map<TriangleBlock, List<TriangleBlock>> adj = new HashMap<>();
//        Set<TriangleBlock> visit = new HashSet<>();
        int N = grid.length;
        int M = grid[0].length;
        boolean[][][] visit = new boolean[N][M][4];
        init(grid, N, M, adj);

        // 블럭 하나씩 선정해서 bfs를 통해 최대 넓이(길이)를 구한다
        int answer = 0;
        for (TriangleBlock triangleBlock : adj.keySet()) {
            if (visit[triangleBlock.y][triangleBlock.x][triangleBlock.dir]) continue;
            int result = bfs(triangleBlock, visit, adj, N, M);
            answer = Math.max(answer, result);
        }

        return answer;
    }

    private int bfs(TriangleBlock startBlock, boolean[][][] visit, Map<TriangleBlock, List<TriangleBlock>> adj, int N, int M) {
        ArrayDeque<TriangleBlock> queue = new ArrayDeque<>();
        queue.offer(startBlock);
        boolean[][] gridVisit = new boolean[N][M];
        int sum = 0;

        while (!queue.isEmpty()) {
            TriangleBlock cur = queue.poll();

            // 이미 방문한 정사각형칸에 도달하면 더이상 탐색 불가능 or 전체 삼각형 방문 횟수가 2를 넘은경우
            if (gridVisit[cur.y][cur.x]) {
                continue;
            } else {
                gridVisit[cur.y][cur.x] = true;
                visit[cur.y][cur.x][cur.dir] = true;
            }

            sum += 1;

            List<TriangleBlock> nextAdj = adj.get(cur);
            for (int nextIdx = 0; nextIdx < nextAdj.size(); nextIdx++) {
                TriangleBlock next = nextAdj.get(nextIdx);
                if (gridVisit[next.y][next.x]) continue;
                queue.offer(next);
            }
        }

        return sum;
    }

    private void init(int[][] grid, int N, int M, Map<TriangleBlock, List<TriangleBlock>> adj) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    TriangleBlock t1 = new TriangleBlock(i, j, 0);
                    List<TriangleBlock> l1 = new ArrayList<>();
                    for (int k = 0; k < 2; k++) {
                        int ny = i + dy[0][k];
                        int nx = j + dx[0][k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                            continue;
                        // 상
                        if (k == 0)
                            l1.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 1 : 2));
                        // 좌
                        if (k == 1)
                            l1.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 1 : 3));
                    }

                    TriangleBlock t2 = new TriangleBlock(i, j, 1);
                    List<TriangleBlock> l2 = new ArrayList<>();
                    for (int k = 0; k < 2; k++) {
                        int ny = i + dy[1][k];
                        int nx = j + dx[1][k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                            continue;
                        // 하
                        if (k == 0)
                            l2.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 0 : 3));
                        // 우
                        if (k == 1)
                            l2.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 0 : 2));
                    }

                    adj.put(t1, l1);
                    adj.put(t2, l2);
                } else {
                    TriangleBlock t1 = new TriangleBlock(i, j, 2);
                    List<TriangleBlock> l1 = new ArrayList<>();
                    for (int k = 0; k < 2; k++) {
                        int ny = i + dy[2][k];
                        int nx = j + dx[2][k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                            continue;
                        // 하
                        if (k == 0)
                            l1.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 0 : 3));
                        // 좌
                        if (k == 1)
                            l1.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 1 : 3));
                    }

                    TriangleBlock t2 = new TriangleBlock(i, j, 3);
                    List<TriangleBlock> l2 = new ArrayList<>();
                    for (int k = 0; k < 2; k++) {
                        int ny = i + dy[3][k];
                        int nx = j + dx[3][k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                            continue;
                        // 상
                        if (k == 0)
                            l2.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 1 : 2));
                        // 우
                        if (k == 1)
                            l2.add(new TriangleBlock(ny, nx, grid[ny][nx] == 1 ? 0 : 2));
                    }

                    adj.put(t1, l1);
                    adj.put(t2, l2);
                }
            }
        }
    }

    private class TriangleBlock {
        int y, x, dir;

        public TriangleBlock(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TriangleBlock that = (TriangleBlock) o;
            return y == that.y && x == that.x && dir == that.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x, dir);
        }
    }
}
