package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P16973_직사각형_탈출 {
    static int N, M, H, W, sy, sx, ey, ex;
    static int[][] grid;
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    // (i,j)좌표를 포함하는 직사각형 좌표
                    for (int r = i - H + 1; r <= i; r++) {
                        for (int c = j - W + 1; c <= j; c++) {
                            if (r >= 0 && c >= 0) {
                                grid[r][c] = 1;
                            }
                        }
                    }
                }
            }
        }

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
                if (ny < 0 || nx < 0 || ny >= N - H + 1 || nx >= M - W + 1 || grid[ny][nx] == 1 || visit[ny][nx]) {
                    continue;
                }

                visit[ny][nx] = true;
                queue.offer(new State(ny, nx, cur.count + 1));
            }
        }

        System.out.println(answer);
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
