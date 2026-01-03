package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P16933_벽_부수고_이동하기3 {
    // (0,0) -> (N-1, M-1)
    // 이동할 때 마다 낮, 밤 교대
    // 낮에는 벽을 부술 수 있다. 최대 K개까지
    // N,M <= 1000  K <= 10

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int N, M, K, answer;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        answer = -1;
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        boolean[][][] visit = new boolean[N][M][K + 1];
        ArrayDeque<State> queue = new ArrayDeque<>();
        // count%2 == 0 낮, 1이면 밤
        queue.offer(new State(0, 0, 0, 0));
        visit[0][0][0] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.y == N - 1 && cur.x == M - 1) {
                answer = cur.depth + 1;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                // 벽
                if (map[ny][nx] == '1') {
                    // 부수기
                    if (cur.count < K) {
                        // 낮
                        if (cur.depth % 2 == 0) {
                            if (!visit[ny][nx][cur.count + 1]) {
                                visit[ny][nx][cur.count + 1] = true;
                                queue.offer(new State(ny, nx, cur.count + 1, cur.depth + 1));
                            }
                        }
                        // 밤
                        else {
                            queue.offer(new State(cur.y, cur.x, cur.count, cur.depth + 1));
                        }
                    }
                }
                // 그냥 이동
                else {
                    if (!visit[ny][nx][cur.count]) {
                        visit[ny][nx][cur.count] = true;
                        queue.offer(new State(ny, nx, cur.count, cur.depth + 1));
                    }
                }
            }
        }
    }

    private static class State {
        int y, x, count, depth;

        public State(int y, int x, int count, int depth) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.depth = depth;
        }
    }
}
