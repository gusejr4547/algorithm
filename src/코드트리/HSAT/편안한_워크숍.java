package 코드트리.HSAT;

import java.util.Arrays;
import java.util.Scanner;

public class 편안한_워크숍 {
    static int N, K;
    static int[][] map;
    static int[][][] dp;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        // 여러 등산로를 만들 수 있는데, 인접한 높이의 차들 간의 최댓값이 최소가 되는 등산로를 선택 = 편안한 등산로
        // 시작은 아무칸이나 상관없다.
        // 시작칸으로 부터 상하좌우 인접한 칸으로 이동. 이동할 때마다 높이가 높아져야함.
        // 등산로의 길이는 K 이상 == 지나가는 칸의 수가 K 이상이어야 함.

        dp = new int[N][N][K + 1]; // (i,j)위치에서 길이가 k인 등산로의 높이의 차 최대의 최소
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.min(answer, selectWay(i, j, K));
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static int selectWay(int y, int x, int count) {
        // 이미 계산한 적 있는 경우
        if (dp[y][x][count] != -1) {
            return dp[y][x][count];
        }

        // 마지막
        if (count == 1) {
            dp[y][x][count] = 0;
            return dp[y][x][count];
        }

        int best = Integer.MAX_VALUE;
        // 4방향 탐색
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            // 현재 위치의 높이 보다 높은곳으로 이동가능
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= map[y][x]) {
                continue;
            }

            best = Math.min(best, Math.max(map[ny][nx] - map[y][x], selectWay(ny, nx, count - 1)));
        }

        return dp[y][x][count] = best;
    }
}
