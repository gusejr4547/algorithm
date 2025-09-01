package 코드트리.HSAT;

import java.util.Arrays;
import java.util.Scanner;

public class 채광_로봇_시뮬레이션 {
    static int MIN = -987654321;
    static int N, T;
    static int[][] grid, moveT;
    static int[][][] dp;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }


        // 어떤 위치에서 T초만큼 움직여서 얻을 수 있는 최대 값
        moveT = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                moveT[i][j] = MIN;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                move(i, j, i, j, 0, 0);
            }
        }

        for (int[] d : moveT) {
            System.out.println(Arrays.toString(d));
        }


        // 1번 T초 전으로 돌아갈 수 있다. 획득한 이익과 손해는 유지된다.
        // (0,0) -> (n-1,n-1) 이동. 오른쪽, 아래쪽만 가능

        dp = new int[N][N][2]; // 돌아간거 안돌아간거 따로 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], MIN);
            }
        }

        dp[0][0][0] = grid[0][0];
        for (int i = 1; i < N; i++) {
            dp[0][i][0] = dp[0][i - 1][0] + grid[0][i];
            // 시간을 돌려서 (i,j)로 돌아온 경우
            dp[0][i][1] = Math.max(dp[0][i][1], dp[0][i][0] + moveT[0][i]);
            dp[0][i][1] = Math.max(dp[0][i][1], dp[0][i - 1][1] + grid[0][i]);

            dp[i][0][0] = dp[i - 1][0][0] + grid[i][0];
            // 시간을 돌려서 (i,j)로 돌아온 경우
            dp[i][0][1] = Math.max(dp[i][0][1], dp[i][0][0] + moveT[i][0]);
            // 이미 시간을 돌렸던 경우
            dp[i][0][1] = Math.max(dp[i][0][1], dp[i - 1][0][1] + grid[i][0]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]) + grid[i][j];

                // 시간을 돌려서 (i,j)로 돌아온 경우
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j][0] + moveT[i][j]);

                dp[i][j][1] = Math.max(dp[i][j][1], Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) + grid[i][j]);
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j][1] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(Math.max(dp[N - 1][N - 1][0], dp[N - 1][N - 1][1]));
    }

    private static void move(int y, int x, int i, int j, int count, int value) {
        if (count == T) {
            moveT[i][j] = Math.max(moveT[i][j], value + grid[y][x]);
            return;
        }

        for (int d = 0; d < 2; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }

            move(ny, nx, i, j, count + 1, value + grid[y][x]);
        }
    }
}
