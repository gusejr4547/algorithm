package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P16234_인구_이동 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean[][] visit = new boolean[N][N];
            boolean isMove = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) continue;
                    isMove |= openBorder(i, j, map, L, R, visit);
                }
            }
            if (!isMove) break;

            answer++;
        }

        System.out.println(answer);
    }

    private static boolean openBorder(int i, int j, int[][] map, int L, int R, boolean[][] visit) {
        int N = map.length;
        List<Pair> areaList = new ArrayList<>();
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));

        int area = 0;
        int totalPeople = 0;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;
            area++;
            totalPeople += map[cur.y][cur.x];
            areaList.add(cur);

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;

                int diff = Math.abs(map[cur.y][cur.x] - map[ny][nx]);
                if (L <= diff && diff <= R) {
                    queue.offer(new Pair(ny, nx));
                }
            }
        }

        // 이동 불가
        if (area == 1) {
            return false;
        }

        // 인구 이동
        movePeople(areaList, map, area, totalPeople);

        return true;
    }

    private static void movePeople(List<Pair> areaList, int[][] map, int area, int totalPeople) {
        int avgPeople = totalPeople / area;

        for (Pair pos : areaList) {
            map[pos.y][pos.x] = avgPeople;
        }
    }

    private static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
