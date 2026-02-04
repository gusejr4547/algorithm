package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AI_로봇청소기 {
    // p > 0 => 먼지
    // p = 0 => 먼지 없음
    // p = -1 => 물건

    static int N, K, L;
    static int[][] grid;
    static boolean[][] cleanerGrid;
    static List<Point> cleanerList;
    static StringBuilder sb = new StringBuilder();

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        grid = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cleanerList = new ArrayList<>();
        cleanerGrid = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cleanerList.add(new Point(r, c));
            cleanerGrid[r][c] = true;
        }

        while (L-- > 0) {
            test();
        }
        System.out.println(sb);
    }

    private static void test() {
        // 1. 청소기 이동
        move();

        // 2. 청소
        clean();

        // 3. 먼지 축적
        addDust();

        // 4. 먼지 확산
        spreadDust();

        // 5. 출력
        sb.append(getTotalDust()).append('\n');
    }

    private static int getTotalDust() {
        // 먼지 총량
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] > 0) {
                    sum += grid[i][j];
                }
            }
        }
        return sum;
    }

    private static void spreadDust() {
        // 먼지 없는 곳
        // + 주변 4 격자의 먼지 합 / 10
        int[][] newGrid = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] == 0) {
                    int sum = 0;
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] > 0) {
                            sum += grid[ny][nx];
                        }
                    }
                    newGrid[i][j] = sum / 10;
                }
            }
        }

        grid = newGrid;
    }

    private static void addDust() {
        // 먼지 있는 곳 +5
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] > 0) {
                    grid[i][j] += 5;
//                    grid[i][j] = Math.min(grid[i][j], 100);
                }
            }
        }
    }

    private static void clean() {
        // 청소기 순서대로 청소
        // 바라보는 방향 기준.
        // 위치한 격자, 좌, 우, 상 => 청소
        // 4가지 방향 중 청소 가능 먼지량 가장 많은 방향 청소
        // 격자마다 청소 최대 먼지량 20.
        // 합이 같은것 여러개 => 우, 하, 좌 ,상 순서
        for (Point p : cleanerList) {
            int d = getTargetDirection(p);
            // 위치
            grid[p.y][p.x] -= Math.min(grid[p.y][p.x], 20);
            // 좌
            int ny = p.y + dy[(d + 3) % 4];
            int nx = p.x + dx[(d + 3) % 4];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                grid[ny][nx] -= Math.min(grid[ny][nx], 20);
            }
            // 우
            ny = p.y + dy[(d + 1) % 4];
            nx = p.x + dx[(d + 1) % 4];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                grid[ny][nx] -= Math.min(grid[ny][nx], 20);
            }
            // 상
            ny = p.y + dy[d];
            nx = p.x + dx[d];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                grid[ny][nx] -= Math.min(grid[ny][nx], 20);
            }
        }
    }

    private static int getTargetDirection(Point p) {
        int maxDust = 0;
        int dir = 0;
        for (int d = 0; d < 4; d++) {
            int sum = 0;
            // 위치
            sum += Math.min(grid[p.y][p.x], 20);
            // 좌
            int ny = p.y + dy[(d + 3) % 4];
            int nx = p.x + dx[(d + 3) % 4];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                sum += Math.min(grid[ny][nx], 20);
            }
            // 우
            ny = p.y + dy[(d + 1) % 4];
            nx = p.x + dx[(d + 1) % 4];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                sum += Math.min(grid[ny][nx], 20);
            }
            // 상
            ny = p.y + dy[d];
            nx = p.x + dx[d];
            if (ny >= 1 && nx >= 1 && ny <= N && nx <= N && grid[ny][nx] >= 0) {
                sum += Math.min(grid[ny][nx], 20);
            }

            if (maxDust < sum) {
                maxDust = sum;
                dir = d;
            }
        }
        return dir;
    }

    private static void move() {
        // 각 청소기 순서대로.
        // 이동거리가 가장 가까운 오염된 격자로 이동.
        // 물건이 있거나, 청소기가 있는 곳은 못지나감.
        // 순서 => 행번호 작은거, 열번호 작은거
//        System.out.println("move -------------------");
        for (Point p : cleanerList) {
            Point next = getDestination(p);
//            System.out.printf("%d %d -> %d %d\n", p.y, p.x, next.y, next.x);
            // cleanerGrid 갱신
            cleanerGrid[p.y][p.x] = false;
            cleanerGrid[next.y][next.x] = true;
            // cleanerList 갱신
            p.y = next.y;
            p.x = next.x;
        }
    }

    private static Point getDestination(Point p) {
        boolean[][] visit = new boolean[N + 1][N + 1];
        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(p.y, p.x, 0));
        visit[p.y][p.x] = true;

        int minDist = Integer.MAX_VALUE;
        Point dest = new Point(p.y, p.x);

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // 이동 거리가 최소 이동 거리보다 크면 종료
            if (minDist < cur.dist) {
                continue;
            }

            // 먼지가 있는 칸
            if (grid[cur.y][cur.x] > 0) {
                // 최소 이동 갱신
                if (minDist > cur.dist) {
                    minDist = cur.dist;
                    dest.y = cur.y;
                    dest.x = cur.x;
                } else if (minDist == cur.dist) {
                    // 같으면 y 작은거
                    if (dest.y > cur.y) {
                        dest.y = cur.y;
                        dest.x = cur.x;
                    } else if (dest.y == cur.y) {
                        // 같으면 x 작은거
                        if (dest.x > cur.x) {
                            dest.x = cur.x;
                        }
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny <= 0 || nx <= 0 || ny > N || nx > N || grid[ny][nx] == -1 || cleanerGrid[ny][nx]) {
                    continue;
                }
                if (!visit[ny][nx]) {
                    visit[ny][nx] = true;
                    queue.offer(new State(ny, nx, cur.dist + 1));
                }
            }
        }

        return dest;
    }

    private static class State {
        int y, x, dist;

        public State(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
