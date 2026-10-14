package 코드트리.ACPC2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
        int INF = Integer.MAX_VALUE;
        int[][][] dist = new int[N + 1][N + 1][4];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        ArrayDeque<State> queue = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            queue.offerLast(new State(2, 2, d, 0));
            dist[2][2][d] = 0;
        }

        // 가중치가 달라서 dist 씀
        while (!queue.isEmpty()) {
            State cur = queue.pollFirst();

            if (dist[cur.y][cur.x][cur.dir] < cur.count) {
                continue;
            }

            // 전진
            int ny = cur.y + dy[cur.dir];
            int nx = cur.x + dx[cur.dir];

            // 범위 검사
            if (ny < 1 || ny > N || nx < 1 || nx > N) {
                continue;
            }

            if (grid[ny][nx] == 0) {
                if (dist[ny][nx][cur.dir] > cur.count + 1) {
                    queue.offerLast(new State(ny, nx, cur.dir, cur.count + 1));
                    dist[ny][nx][cur.dir] = cur.count + 1;
                }
            } else {
                // 벽을 만난 경우 전진 불가능 -> 방향 전환
                for (int d = 0; d < 4; d++) {
                    if (dist[cur.y][cur.x][d] > cur.count) {
                        queue.offerFirst(new State(cur.y, cur.x, d, cur.count));
                        dist[cur.y][cur.x][d] = cur.count;
                    }
                }
            }
        }

        int answer = INF;

        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[N-1][N-1][d]);
        }

        return answer == INF ? -1 : answer;
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
