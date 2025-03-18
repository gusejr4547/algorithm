package 코드트리;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 돌_잘_치우기 {
    static int M, maxMoveArea;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        M = sc.nextInt();
        int[][] grid = new int[n][n];
        List<Point> rockList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 1) rockList.add(new Point(i, j));
            }
        }
        int[][] startPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            startPoints[i][0] = sc.nextInt() - 1;
            startPoints[i][1] = sc.nextInt() - 1;
        }
        // Please write your code here.

        // 돌 M 개를 선택해서 치운다.
        maxMoveArea = 0;
        selectRock(new int[M], 0, 0, rockList, grid, startPoints);

        System.out.println(maxMoveArea);
    }

    private static void move(int[][] grid, int[][] startPoints) {
        int area = 0;

        boolean[][] visit = new boolean[grid.length][grid[0].length];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        for (int[] point : startPoints) {
            queue.offer(new Point(point[0], point[1]));
            visit[point[0]][point[1]] = true;
            area++;
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= grid.length || nx >= grid[0].length || visit[ny][nx] || grid[ny][nx] == 1)
                    continue;

                area++;
                visit[ny][nx] = true;
                queue.offer(new Point(ny, nx));
            }
        }

        // 이동가능한 칸수를 구한다. 갱신
        maxMoveArea = Math.max(maxMoveArea, area);
    }

    private static void selectRock(int[] select, int selectIdx, int rockIdx, List<Point> rockList, int[][] grid, int[][] startPoints) {
        if (selectIdx == M) {
            // grid에서 rock 없애기
            for (int i : select) {
                grid[rockList.get(i).y][rockList.get(i).x] = 0;
            }

            // 시작점으로 부터 이동
            move(grid, startPoints);

            // rock 복구
            for (int i : select) {
                grid[rockList.get(i).y][rockList.get(i).x] = 1;
            }
            return;
        }

        for (int i = rockIdx; i < rockList.size(); i++) {
            select[selectIdx] = i;
            selectRock(select, selectIdx + 1, i + 1, rockList, grid, startPoints);
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
