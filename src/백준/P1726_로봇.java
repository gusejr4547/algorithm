package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1726_로봇 {
    static int M, N;
    static int[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        Status start = new Status(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());
        Status end = new Status(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);

        int result = bfs(start, end);
        System.out.println(result);
    }

    // 방향 순서: 동, 서, 남, 북
    private static int bfs(Status start, Status end) {
        PriorityQueue<Status> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        boolean[][][] visit = new boolean[M + 1][N + 1][4];
        pq.offer(start);

        while (!pq.isEmpty()) {
            Status curr = pq.poll();
            int currDir = curr.direction;
            int currY = curr.y;
            int currX = curr.x;
            int currCost = curr.cost;

            if (currY == end.y && currX == end.x && currDir == end.direction) {
                return curr.cost;
            }

            if (visit[currY][currX][currDir]) continue;
            visit[currY][currX][currDir] = true;

//            System.out.println(currY + ", " + currX + ", " + currDir + " cost : " + currCost);

            // 방향
            for (int d = 0; d < 4; d++) {
                if (visit[currY][currX][d]) continue;

                int diffDir = 0;
                if (currDir == d) diffDir = 0;
                else if (currDir >= 2) {
                    if (d < 2) diffDir = 1;
                    else diffDir = 2;
                } else {
                    if (d < 2) diffDir = 2;
                    else diffDir = 1;
                }

                pq.offer(new Status(currY, currX, d, currCost + diffDir));
            }

            // 이동
            for (int k = 1; k <= 3; k++) {
                int ny = currY + dy[currDir] * k;
                int nx = currX + dx[currDir] * k;

                if (ny <= 0 || nx <= 0 || ny > M || nx > N || map[ny][nx] == 1) break;
                if (visit[ny][nx][currDir]) continue;

                pq.offer(new Status(ny, nx, currDir, currCost + 1));
            }
        }

        return -1;
    }

    static class Status {
        int y;
        int x;
        int direction;
        int cost;

        public Status(int y, int x, int direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }

        public Status(int y, int x, int direction, int cost) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.cost = cost;
        }
    }
}
