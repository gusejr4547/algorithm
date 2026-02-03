package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1261_알고스팟 {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // (0,0) -> (N-1, M-1) 이동
        // 벽을 부술수 있는데 최소 값

        System.out.println(bfs());
    }

    private static int bfs() {
        boolean[][] visit = new boolean[N][M];
        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> o1.destroyCnt - o2.destroyCnt);
        pq.offer(new State(0, 0, 0));
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.y == N - 1 && cur.x == M - 1) {
                return cur.destroyCnt;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) {
                    continue;
                }
                visit[ny][nx] = true;
                if (map[ny][nx] == 0) {
                    pq.offer(new State(ny, nx, cur.destroyCnt));
                } else {
                    pq.offer(new State(ny, nx, cur.destroyCnt + 1));
                }
            }
        }
        return -1;
    }

    private static class State {
        int y, x, destroyCnt;

        public State(int y, int x, int destroyCnt) {
            this.y = y;
            this.x = x;
            this.destroyCnt = destroyCnt;
        }
    }
}
