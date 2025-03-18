package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 3개의 벽을 세워야한다.
// 바이러스는 인접한 칸으로 퍼져나갈 수 있다. 상하좌우
// 빈칸 중에 3개를 선택해서 (경우의수) + bfs를 통해 안전영역을 구한다.
public class P14502_연구소 {
    static int N, M, maxArea;
    static int[][] map;
    static List<Point> wallList, virusList;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        wallList = new ArrayList<>();
        virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) wallList.add(new Point(i, j));
                if (map[i][j] == 2) virusList.add(new Point(i, j));
            }
        }

        // 빈 공간에서 3개 선택해서 벽세우기
        maxArea = 0;
        makeThreeWall();
        System.out.println(maxArea);
    }

    // bfs를 사용해 바이러스 퍼트리기
    private static void spreadVirus() {
        boolean[][] visit = new boolean[N][M];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        // 초기 바이러스 위치 전부 넣기
        for (Point virus : virusList) {
            queue.offer(virus);
            visit[virus.y][virus.x] = true;
        }


        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 1)
                    continue;

                visit[ny][nx] = true;
                queue.offer(new Point(ny, nx));
            }
        }

        // 안전영역 구하기
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visit[i][j])
                    area++;
            }
        }

        maxArea = Math.max(maxArea, area);
    }

    private static void makeThreeWall() {
        for (int i = 0; i < wallList.size(); i++) {
            for (int j = i + 1; j < wallList.size(); j++) {
                for (int k = j + 1; k < wallList.size(); k++) {
                    map[wallList.get(i).y][wallList.get(i).x] = 1;
                    map[wallList.get(j).y][wallList.get(j).x] = 1;
                    map[wallList.get(k).y][wallList.get(k).x] = 1;
                    // 바이러스 퍼트리기
                    spreadVirus();
                    map[wallList.get(i).y][wallList.get(i).x] = 0;
                    map[wallList.get(j).y][wallList.get(j).x] = 0;
                    map[wallList.get(k).y][wallList.get(k).x] = 0;
                }
            }
        }
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
