package 코드트리;

import java.util.*;
import java.util.Scanner;

public class 강력한_폭발 {
    static int[][][] boom = {
            {{-2, 0}, {-1, 0}, {1, 0}, {2, 0}},
            {{-1, 0}, {0, 1}, {1, 0}, {0, -1}},
            {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        List<int[]> boomPos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    boomPos.add(new int[]{i, j});
                }
            }
        }

        // 1, 2, 3 폭탄 중에 선택해야함.
        int area = selectBoom(new int[boomPos.size()], 0, grid, boomPos);

        System.out.println(area);
    }

    private static int selectBoom(int[] select, int selectIdx, int[][] grid, List<int[]> boomPos) {
        if (selectIdx == boomPos.size()) {
            return explode(select, grid, boomPos);
        }

        int maxExplodeArea = 0;
        for (int boom = 0; boom < 3; boom++) {
            select[selectIdx] = boom;
            int area = selectBoom(select, selectIdx + 1, grid, boomPos);
            maxExplodeArea = Math.max(maxExplodeArea, area);
        }

        return maxExplodeArea;
    }

    private static int explode(int[] select, int[][] grid, List<int[]> boomPos) {
        boolean[][] map = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < boomPos.size(); i++) {
            int[] pos = boomPos.get(i);
            int y = pos[0];
            int x = pos[1];
            int boomIdx = select[i];
            map[y][x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = y + boom[boomIdx][d][0];
                int nx = x + boom[boomIdx][d][1];
                if (ny < 0 || nx < 0 || ny >= grid.length || nx >= grid[0].length) continue;

                map[ny][nx] = true;
            }
        }

        // 계산
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (map[i][j]) area++;
            }
        }

        return area;
    }
}
