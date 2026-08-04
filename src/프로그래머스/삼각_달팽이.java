package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 삼각_달팽이 {
    public static void main(String[] args) {
        삼각_달팽이 Main = new 삼각_달팽이();
        int n = 4;
        System.out.println(Arrays.toString(Main.solution(n)));
    }

    public int[] solution(int n) {
        // 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 1부터 증가 값 채우기
        /*
        n = 5
        1
        2 12
        3 13 11
        4 14 15 10
        5 6  7  8  9
        */

        int[][] grid = new int[n][n];

        int[][] d = {
                {1, 0},    // 아래
                {0, 1},    // 오른쪽
                {-1, -1}   // 왼쪽 위
        };

        int max = n * (n + 1) / 2;

        int r = 0;
        int c = 0;
        int dir = 0;

        for (int id = 1; id <= max; id++) {
            grid[r][c] = id;

            int nr = r + d[dir][0];
            int nc = c + d[dir][1];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n
                    || grid[nr][nc] != 0) {
                dir = (dir + 1) % 3;
                nr = r + d[dir][0];
                nc = c + d[dir][1];
            }

            r = nr;
            c = nc;
        }

        int[] answer = new int[max];
        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = grid[i][j];
            }
        }

        return answer;
    }
}
