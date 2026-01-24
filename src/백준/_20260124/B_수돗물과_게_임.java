package 백준._20260124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_수돗물과_게_임 {
    // 게 0~3 0-상, 1-좌, 2-하, 3-우. 바다 s
    // 게는 바다로 가야함.
    // 게걸음으로는 자신이 바라보는 방향에 수직한 방향으로 한 칸 이동할 수 있고, 이동하는 데 1의 시간이 걸린다.
    // 방향 전환 반시계90도 T
    // 수돗물칸 -> 반시계90도 -> 한칸 앞으로이동 => 추가시간 x

    // 바다까지 이동 최단 시간? 못가면 -1

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int MAX = 999_999_999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        char[][] grid = new char[N][M];
        int sy = 0;
        int sx = 0;
        int sDir = 0;
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '0' || grid[i][j] == '1' || grid[i][j] == '2' || grid[i][j] == '3') {
                    sy = i;
                    sx = j;
                    sDir = grid[i][j] - '0';
                }
            }
        }

        int answer = MAX;
        int[][][] visit = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visit[i][j], MAX);
            }
        }
        PriorityQueue<State> queue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        queue.offer(new State(sy, sx, sDir, 0));
        visit[sy][sx][sDir] = 0;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // 도착
            if (grid[cur.y][cur.x] == 'S') {
                answer = Math.min(answer, cur.time);
                continue;
            }

            // 수돗물?
            if (grid[cur.y][cur.x] == 'T') {
                int nDir = cur.dir == 3 ? 0 : cur.dir + 1;
                int ny = cur.y + dy[nDir];
                int nx = cur.x + dx[nDir];
                if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
                    if (visit[ny][nx][nDir] > cur.time) {
                        visit[ny][nx][nDir] = cur.time;
                        queue.offer(new State(ny, nx, nDir, cur.time));
                    }
                }
                continue;
            }

            // 이동? 2가지 가능
            // 바라보는 방향 왼쪽, 오른쪽
            // 1
            int d = cur.dir == 0 ? 3 : cur.dir - 1;
            int ny = cur.y + dy[d];
            int nx = cur.x + dx[d];
            if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
                if (visit[ny][nx][cur.dir] > cur.time + 1) {
                    visit[ny][nx][cur.dir] = cur.time + 1;
                    queue.offer(new State(ny, nx, cur.dir, cur.time + 1));
                }
            }
            // 2
            d = cur.dir == 3 ? 0 : cur.dir + 1;
            ny = cur.y + dy[d];
            nx = cur.x + dx[d];
            if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
                if (visit[ny][nx][cur.dir] > cur.time + 1) {
                    visit[ny][nx][cur.dir] = cur.time + 1;
                    queue.offer(new State(ny, nx, cur.dir, cur.time + 1));
                }
            }

            // 방향전환?
            int nDir = cur.dir == 3 ? 0 : cur.dir + 1;
            if (visit[cur.y][cur.x][nDir] > cur.time + T) {
                visit[cur.y][cur.x][nDir] = cur.time + T;
                queue.offer(new State(cur.y, cur.x, nDir, cur.time + T));
            }
        }

        System.out.println(answer == MAX ? -1 : answer);
    }

    private static class State {
        int y, x, dir, time;

        public State(int y, int x, int dir, int time) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.time = time;
        }
    }
}
