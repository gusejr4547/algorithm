package 코드트리;

import java.util.Scanner;

public class 수들_중_최솟값_최대화하기 {
    static int answer;

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
        // 1~N까지 1개씩 선택해서 줄세우기

        answer = 0;
        selectLine(new int[n], 0, n, grid, 0);
        System.out.println(answer);
    }

    private static void selectLine(int[] select, int row, int max, int[][] grid, int visitBitmask) {
        if (row == max) {
            // 최소값을 구함.
            int minValue = 99999;

            for (int i = 0; i < max; i++) {
                minValue = Math.min(minValue, grid[i][select[i]]);
            }

            answer = Math.max(answer, minValue);
            return;
        }

        for (int col = 0; col < max; col++) {
            // 이미 선택한 col 이면
            if ((visitBitmask & (1 << col)) != 0) continue;

            select[row] = col;
            selectLine(select, row + 1, max, grid, visitBitmask | (1 << col));
        }
    }
}

