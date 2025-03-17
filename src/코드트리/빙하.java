package 코드트리;

import java.util.*;

public class 빙하 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[][] grid = new int[N][M];
        List<Pair> iceList = new ArrayList<>();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    iceList.add(new Pair(i, j));
                }
            }
        }
        // Please write your code here.

        // 반복
        int time = 1;
        int lastIce = 0;
        while (!iceList.isEmpty()) {
            // 녹을 빙하 구하기
            boolean[][] hotWater = new boolean[N][M];
            getHotWaterMap(hotWater, grid);

            // 빙하 녹이기
            lastIce = meltIce(hotWater, grid, iceList);
            time++;
        }

        System.out.println(time + " " + lastIce);
    }

    private static int meltIce(boolean[][] hotWater, int[][] grid, List<Pair> iceList) {
        int meltIceCnt = 0;
        List<Pair> notMeltIceList = new ArrayList<>();
        for (Pair ice : iceList) {
            // 4방향확인하고 hotWater가 true이면 녹는다.
            if (isMelt(hotWater, ice)) {
                grid[ice.y][ice.x] = 0;
                meltIceCnt++;
            } else {
                notMeltIceList.add(ice);
            }
        }
        iceList.clear();
        iceList.addAll(notMeltIceList);
        return meltIceCnt;
    }

    private static boolean isMelt(boolean[][] hotWater, Pair ice) {
        for (int d = 0; d < 4; d++) {
            int y = ice.y + dy[d];
            int x = ice.x + dx[d];

            if (y < 0 || x < 0 || y >= N || x >= M) continue;

            if (hotWater[y][x])
                return true;
        }
        return false;
    }

    private static void getHotWaterMap(boolean[][] hotWater, int[][] grid) {
        // 격자의 바깥부분은 항상 물이다
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(0, 0));

        boolean[][] visit = new boolean[N][M];

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;
            hotWater[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || grid[ny][nx] == 1)
                    continue;

                queue.offer(new Pair(ny, nx));
            }
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
