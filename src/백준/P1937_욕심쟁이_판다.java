package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1937_욕심쟁이_판다 {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][N];

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }
//        for (int[] d : dp) {
//            System.out.println(Arrays.toString(d));
//        }
        System.out.println(result);
    }

    private static int dfs(int curY, int curX) {
        if (dp[curY][curX] != 0) {
            return dp[curY][curX];
        }
        int max = 0;
        for (int d = 0; d < 4; d++) {
            int ny = curY + dy[d];
            int nx = curX + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }
            if (map[curY][curX] < map[ny][nx]) {
                max = Math.max(max, dfs(ny, nx));
            }
        }
        return dp[curY][curX] = max + 1;
    }
}
