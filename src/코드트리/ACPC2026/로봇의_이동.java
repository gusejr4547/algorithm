package 코드트리.ACPC2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 로봇의_이동 {
    static int N;
    static int[][] grid;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N <= 1000
        // (2,2) -> (N-1, N-1) 도달 최소 시간.
        // 0은 빈공간 1은 벽
        // 처음 출발할때 방향을 자유롭게 정한다.
        // 뱡향으로 계속 직진
        // 벽을 만나면 방향전환 가능
        // 방향전환은 시간 0

        // bfs
        System.out.println(bfs());
    }

    private static int bfs() {
        boolean[][][] visit = new boolean[N + 1][N + 1][4]; // 바라보는 방향도 기록
        PriorityQueue<State> queue = new PriorityQueue<>((o1, o2)->Integer.compare(o1.count, o2.count));
        for (int d = 0; d < 4; d++) {
            queue.offer(new State(2, 2, d, 0));
            visit[2][2][d] = true;
        }

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            // N-1, N-1 도달
            if (cur.y == N - 1 && cur.x == N - 1) {
                return cur.count;
            }

            // 전진
            int ny = cur.y + dy[cur.dir];
            int nx = cur.x + dx[cur.dir];
            if (0 < ny && ny <= N && 0 < nx && nx <= N && !visit[ny][nx][cur.dir] && grid[ny][nx] == 0) {
                queue.offer(new State(ny, nx, cur.dir, cur.count + 1));
                visit[ny][nx][cur.dir] = true;
            }

            // 벽을 만난 경우 전진 불가능 -> 방향 전환
            if (grid[ny][nx] == 1) {
                // y, x 좌표에서 dir을 추가
                for (int d = 0; d < 4; d++) {
                    if (!visit[cur.y][cur.x][d]) {
                        queue.offer(new State(cur.y, cur.x, d, cur.count));
                        visit[cur.y][cur.x][d] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static class State {
        int y, x, dir, count;

        public State(int y, int x, int dir, int count) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.count = count;
        }
    }
}
