package 코드트리.HSAT;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Garage_Game {
    static int N, answer;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[][] grid = new int[3 * N][N];
        for (int i = 3 * N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // N<=15
        answer = 0;
        deleteCar(0, 0, grid);
        System.out.println(answer);
    }

    private static void deleteCar(int turn, int score, int[][] grid) {
        if (turn == 3) {
            answer = Math.max(answer, score);
            return;
        }

        boolean[][] visit = new boolean[3 * N][N];

        // 다음 삭제할 곳 선택
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    int color = grid[i][j];
                    // 선택한 영역에서 확장영역 찾기
                    List<Point> deleteList = spreadArea(i, j, grid, visit);

                    // grid 복제
//                    int[][] copyGrid = copy(grid);

                    // 점수 계산
                    int sum = deleteList.size();
                    int maxY = i, minY = i, maxX = j, minX = j;
                    for (Point p : deleteList) {
                        maxY = Math.max(maxY, p.y);
                        minY = Math.min(minY, p.y);
                        maxX = Math.max(maxX, p.x);
                        minX = Math.min(minX, p.x);

                        // 삭제
                        grid[p.y][p.x] = 0;
                    }
                    sum += (maxY - minY + 1) * (maxX - minX + 1);

                    // 사라진 영역 채우기
                    List<Change> changeList = dropCar(grid);

                    // 다음 턴으로
                    deleteCar(turn + 1, score + sum, grid);

                    // 복구
                    recovery(grid, deleteList, changeList, color);
                }
            }
        }
    }

    private static void recovery(int[][] grid, List<Point> deleteList, List<Change> changeList, int deletedColor) {
        // changeList에서 역순으로 복구
        for (int i = changeList.size() - 1; i >= 0; i--) {
            Change c = changeList.get(i);
            grid[c.ny][c.nx] = grid[c.y][c.x];
        }

        // deleteList 복구
        for (Point p : deleteList) {
            grid[p.y][p.x] = deletedColor;
        }
    }

    private static List<Change> dropCar(int[][] grid) {
        List<Change> changeList = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (grid[i][j] == 0) {
                    int next = i;
                    while (next < 3 * N && grid[next][j] == 0) {
                        next++;
                    }
                    if (next == 3 * N) {
                        break;
                    }

                    changeList.add(new Change(i, j, next, j));
                    grid[i][j] = grid[next][j];
                    grid[next][j] = 0;
                }
            }
        }
        return changeList;
    }

    private static int[][] copy(int[][] grid) {
        int[][] temp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                temp[i][j] = grid[i][j];
            }
        }
        return temp;
    }

    private static List<Point> spreadArea(int y, int x, int[][] grid, boolean[][] visit) {
        List<Point> deleteList = new ArrayList<>();
        int color = grid[y][x];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(y, x));
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            deleteList.add(cur);

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || grid[ny][nx] != color) {
                    continue;
                }
                visit[ny][nx] = true;
                queue.offer(new Point(ny, nx));
            }
        }

        return deleteList;
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Change {
        int y, x;
        int ny, nx;

        public Change(int y, int x, int ny, int nx) {
            this.y = y;
            this.x = x;
            this.ny = ny;
            this.nx = nx;
        }
    }
}
