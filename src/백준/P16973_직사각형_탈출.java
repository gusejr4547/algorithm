package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P16973_직사각형_탈출 {
    static int N, M, H, W, sy, sx, ey, ex;
    static int[][] grid, isBlock;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;

        // 직사각형의 왼쪽위칸 (sy,sx) => (ey, ex) 로 옮기기 위한 최소 이동 횟수

        // grid에서 벽있는 부분 포함하는 H,W 위치 전부 못가게 막기
        // prefix sum으로 벽이 있는지 없는지 확인하는게 가장 최적
        isBlock = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                isBlock[i][j] = grid[i - 1][j - 1] + isBlock[i - 1][j] + isBlock[i][j - 1] - isBlock[i - 1][j - 1];
            }
        }

        int maxY = N - H + 1;
        int maxX = M - W + 1;
        int answer = -1;

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(sy, sx, 0));
        boolean[][] visit = new boolean[N][M];
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.y == ey && cur.x == ex) {
                answer = cur.count;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= maxY || nx >= maxX || visit[ny][nx]) {
                    continue;
                }
                if (hasWall(ny, nx)) {
                    continue;
                }

                visit[ny][nx] = true;
                queue.offer(new State(ny, nx, cur.count + 1));
            }
        }

        System.out.println(answer);
    }

    private static boolean hasWall(int y, int x) {
        // (y,x) 기준으로 사각형을 늫으면 범위안에 벽이 있나?
        int y2 = y + H;
        int x2 = x + W;

        int wallCount = isBlock[y2][x2] - isBlock[y][x2] - isBlock[y2][x] + isBlock[y][x];

        return wallCount > 0;
    }

    private static class State {
        int y, x, count;

        public State(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
}
