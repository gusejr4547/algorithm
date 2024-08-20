package 프로그래머스;

import java.util.PriorityQueue;

public class 미로_탈출LV2 {
    public static void main(String[] args) {
        미로_탈출LV2 Main = new 미로_탈출LV2();
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(Main.solution(maps));
    }

    // 출발 -> 레버 -> 출구
    char[][] map;

    public int solution(String[] maps) {
        int answer = -1;

        map = new char[maps.length][];
        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
            for (int j = 0; j < maps[i].length(); j++) {
                if (map[i][j] == 'S') {

                }
            }
        }

        int n = map.length;
        int m = map[0].length;

        // 시작지점 찾기
        int startY = 0;
        int startX = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    startX = j;
                    startY = i;
                }
            }
        }


        // [n][m][0] -> 레버 방문 X
        // [n][m][1] -> 레버 방문 O
        boolean[][][] visit = new boolean[n][m][2];

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        PriorityQueue<Pos> pq = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
        pq.offer(new Pos(startY, startX, 0, false));

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();

//            System.out.println("cur = " + cur);

            // 출구 도달
            if (cur.lever && map[cur.y][cur.x] == 'E') {
                answer = cur.cost;
                break;
            }

            // 방문체크
            if ((cur.lever && visit[cur.y][cur.x][1]) || (!cur.lever && visit[cur.y][cur.x][0])) {
                continue;
            }
            if (cur.lever) {
                visit[cur.y][cur.x][1] = true;
            } else {
                visit[cur.y][cur.x][0] = true;
            }

            if (map[cur.y][cur.x] == 'L') {
                cur.lever = true;
                visit[cur.y][cur.x][1] = true;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m || map[ny][nx] == 'X') {
                    continue;
                }

                if ((cur.lever && visit[ny][nx][1]) || (!cur.lever && visit[ny][nx][0])) {
                    continue;
                }

                if (!cur.lever) {
                    pq.offer(new Pos(ny, nx, cur.cost + 1, false));
                } else {
                    pq.offer(new Pos(ny, nx, cur.cost + 1, true));
                }
            }
        }

        return answer;
    }

    private class Pos {
        int y;
        int x;
        int cost;
        boolean lever;

        public Pos(int y, int x, int cost, boolean lever) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.lever = lever;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "y=" + y +
                    ", x=" + x +
                    ", cost=" + cost +
                    ", lever=" + lever +
                    '}';
        }
    }
}
