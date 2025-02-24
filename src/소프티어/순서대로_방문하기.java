package 소프티어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순서대로_방문하기 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Point[] visitPointOrder = new Point[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            visitPointOrder[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        // 1은 벽. 한번 지난 곳은 다시 방문 불가능
        boolean[][] visit = new boolean[N][N];
        visit[visitPointOrder[0].y][visitPointOrder[0].x] = true;
        int result = getVisitOrderCnt(visitPointOrder[0], 0, visit, map, visitPointOrder);

        System.out.println(result);
    }

    private static int getVisitOrderCnt(Point current, int currentTargetIdx, boolean[][] visit, int[][] map, Point[] visitPointOrder) {
        // 마지막까지 성공.
        if (currentTargetIdx == visitPointOrder.length) {
            return 1;
        }

        if (visitPointOrder[currentTargetIdx].y == current.y && visitPointOrder[currentTargetIdx].x == current.x) {
            // target에 도달함.
            return getVisitOrderCnt(current, currentTargetIdx + 1, visit, map, visitPointOrder);
        }

        // 아직 target에 도달하지 못함.
        // 인접한 방향으로 나아간다.
        int sum = 0;
        for (int d = 0; d < 4; d++) {
            int ny = current.y + dy[d];
            int nx = current.x + dx[d];

            // 범위 벗어나는거, 벽인거, 방문했던거 제외
            if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length || map[ny][nx] == 1 || visit[ny][nx]) continue;

            // 방문 체크
            visit[ny][nx] = true;
            sum += getVisitOrderCnt(new Point(ny, nx), currentTargetIdx, visit, map, visitPointOrder);
            // 방문 해제
            visit[ny][nx] = false;
        }
        return sum;

    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
