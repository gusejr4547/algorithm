package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P16933_벽_부수고_이동하기3 {
    // (0,0) -> (N-1, M-1)
    // 이동할 때 마다 낮, 밤 교대
    // 낮에는 벽을 부술 수 있다. 최대 K개까지
    // N,M <= 1000  K <= 10

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        boolean[][][] visit = new boolean[N][M][K + 1];
        PriorityQueue<State> queue = new PriorityQueue<>((o1, o2) -> o1.depth - o2.depth);
        // count%2 == 0 낮, 1이면 밤
        queue.offer(new State(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.y == N - 1 && cur.x == M - 1) {
                answer = cur.depth + 1;
                break;
            }

            if (visit[cur.y][cur.x][cur.count]) {
                continue;
            }
            visit[cur.y][cur.x][cur.count] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                // 벽
                if (map[ny][nx] == '1') {
                    // 부수기
                    if (cur.count < K && !visit[ny][nx][cur.count + 1]) {
                        // 낮
                        if (cur.depth % 2 == 0) {
                            queue.offer(new State(ny, nx, cur.count + 1, cur.depth + 1));
                        }
                        // 밤
                        else {
                            queue.offer(new State(ny, nx, cur.count + 1, cur.depth + 2));
                        }
                    }
                }
                // 그냥 이동
                else {
                    if (!visit[ny][nx][cur.count]) {
                        queue.offer(new State(ny, nx, cur.count, cur.depth + 1));
                    }
                }
            }
        }

        System.out.println(answer == 0 ? -1 : answer);
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
