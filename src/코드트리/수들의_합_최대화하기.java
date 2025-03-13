package 코드트리;

import java.util.Scanner;

public class 수들의_합_최대화하기 {
    static int maxValue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        permutation(new int[n], 0, new boolean[n], n, grid);

        System.out.println(maxValue);
    }

    private static void permutation(int[] select, int selectIdx, boolean[] use, int n, int[][] grid) {
        if (selectIdx == n) {
            // 계산
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += grid[i][select[i]];
            }
            maxValue = Math.max(maxValue, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (use[i]) continue;
            select[selectIdx] = i;
            use[i] = true;
            permutation(select, selectIdx + 1, use, n, grid);
            use[i] = false;
        }
    }
}
