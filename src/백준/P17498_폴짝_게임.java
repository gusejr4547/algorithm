package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17498_폴짝_게임 {

    static int N, M, D;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, game(0, i));
        }

//        System.out.println(Arrays.deepToString(map));
//        System.out.println("################################");
//        System.out.println(Arrays.deepToString(dp));
//        System.out.println("################################");
        System.out.println(ans);

    }

    private static int game(int startRow, int startCol) {
        if (dp[startRow][startCol] != Integer.MIN_VALUE) {
            return dp[startRow][startCol];
        }

        if (startRow == N - 1) {
            return dp[startRow][startCol] = 0;
        }

        // 최대 값을 구해서 return
        for (int i = startRow + 1; i <= (startRow + D >= N ? N - 1 : startRow + D); i++) {
            for (int j = (startCol - D < 0 ? 0 : startCol - D); j < startCol; j++) {
                // 이동 가능한 범위 확인
                int move = Math.abs(i - startRow) + Math.abs(j - startCol);
                if (move <= D) {
                    dp[startRow][startCol] = Math.max(dp[startRow][startCol], game(i, j) + map[i][j] * map[startRow][startCol]);
                }
            }
            for (int j = startCol; j <= (startCol + D >= M ? M - 1 : startCol + D); j++) {
                int move = Math.abs(i - startRow) + Math.abs(j - startCol);
                if (move <= D) {
                    dp[startRow][startCol] = Math.max(dp[startRow][startCol], game(i, j) + map[i][j] * map[startRow][startCol]);
                }
            }
        }
        return dp[startRow][startCol];
    }


}
