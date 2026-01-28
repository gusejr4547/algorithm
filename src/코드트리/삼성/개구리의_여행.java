package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 개구리의_여행 {
    // . 안전한 돌, S 미끄러운 돌, # 천적이 있는 돌
    static int MAX = 999_999_999;
    static int MAX_JUMP_POWER = 5;
    static int N;
    static char[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            // (r1,c1) -> (r2,c2) 최소시간
            int minTime = trip(r1, c1, r2, c2);
            sb.append(minTime).append('\n');
        }

        System.out.println(sb);
    }

    private static int trip(int r1, int c1, int r2, int c2) {
        int[][][] time = new int[N][N][MAX_JUMP_POWER + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(time[i][j], MAX);
            }
        }
        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.offer(new State(r1, c1, 1, 0));
        time[r1][c1][1] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // 마지막
            if (cur.y == r2 && cur.x == c2) {
//                continue;
                return cur.time;
            }

            if (cur.time > time[cur.y][cur.x][cur.jumpPower]) {
                continue;
            }

            // 행동
            // 1. 점프
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d] * cur.jumpPower;
                int nx = cur.x + dx[d] * cur.jumpPower;
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != '.') {
                    continue;
                }
                // 점프 경로에 # 있으면 불가능
                boolean enemy = false;
                for (int p = 1; p < cur.jumpPower; p++) {
                    int py = cur.y + dy[d] * p;
                    int px = cur.x + dx[d] * p;
                    if (map[py][px] == '#') {
                        enemy = true;
                        break;
                    }
                }
                if (enemy) {
                    continue;
                }

                if (time[ny][nx][cur.jumpPower] > cur.time + 1) {
                    time[ny][nx][cur.jumpPower] = cur.time + 1;
                    pq.offer(new State(ny, nx, cur.jumpPower, time[ny][nx][cur.jumpPower]));
                }
            }

            // 2. 점프력 증가
            if (cur.jumpPower < 5 &&
                    time[cur.y][cur.x][cur.jumpPower + 1] > cur.time + (cur.jumpPower + 1) * (cur.jumpPower + 1)) {
                time[cur.y][cur.x][cur.jumpPower + 1] = cur.time + (cur.jumpPower + 1) * (cur.jumpPower + 1);
                pq.offer(new State(cur.y, cur.x, cur.jumpPower + 1, time[cur.y][cur.x][cur.jumpPower + 1]));
            }

            // 3. 점프력 감소
            if (cur.jumpPower > 1) {
                // k인 경우 k-1 ~ 1 까지 변경 가능
                for (int k = cur.jumpPower - 1; k >= 1; k--) {
                    if (time[cur.y][cur.x][k] > cur.time + 1) {
                        time[cur.y][cur.x][k] = cur.time + 1;
                        pq.offer(new State(cur.y, cur.x, k, time[cur.y][cur.x][k]));
                    }
                }
            }
        }

        // 최소 시간
//        int minTime = MAX;
//        for (int k = 1; k <= 5; k++) {
//            minTime = Math.min(minTime, time[r2][c2][k]);
//        }
//
//        return minTime == MAX ? -1 : minTime;
        return -1;
    }

    private static class State {
        int y, x, jumpPower, time;

        public State(int y, int x, int jumpPower, int time) {
            this.y = y;
            this.x = x;
            this.jumpPower = jumpPower;
            this.time = time;
        }
    }
}
