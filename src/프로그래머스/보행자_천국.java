package 프로그래머스;

public class 보행자_천국 {
    public static void main(String[] args) {
        보행자_천국 Main = new 보행자_천국();
        int m = 3;
        int n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(Main.solution(m, n, cityMap));
    }

    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2]; // 0은 오른쪽이동, 1은 아래쪽이동

        dp[0][0][0] = 1; // 출발 init
        dp[0][0][1] = 1;

        // 0,0 -> 0,n 까지 채우기
        for (int j = 1; j < n; j++) {
            if (cityMap[0][j - 1] == 1) {
                dp[0][j][0] = -1;
            } else if (cityMap[0][j - 1] == 2) {
                dp[0][j][0] += dp[0][j - 1][0];
            } else {
                dp[0][j][0] += dp[0][j - 1][0];
            }
        }

        // 0,0 -> n,0까지 채우기
        for (int i = 1; i < m; i++) {
            if (cityMap[i - 1][0] == 1) {
                dp[i][0][1] = -1;
            } else if (cityMap[i - 1][0] == 2) {
                dp[i][0][1] += dp[i - 1][0][1];
            } else {
                dp[i][0][1] += dp[i - 1][0][1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 오른쪽
                int moveRight = 0;
                if (cityMap[i][j - 1] == 1) {
                    moveRight = -1;
                } else if (cityMap[i][j - 1] == 2) {
                    moveRight = dp[i][j - 1][0] == -1 ? 0 : dp[i][j - 1][0];
                } else {
                    moveRight = (dp[i][j - 1][0] == -1 ? 0 : dp[i][j - 1][0]) + (dp[i][j - 1][1] == -1 ? 0 : dp[i][j - 1][1]);
                }
                dp[i][j][0] = moveRight % MOD;
                // 아래쪽
                int moveDown = 0;
                if (cityMap[i - 1][j] == 1) {
                    moveDown = -1;
                } else if (cityMap[i - 1][j] == 2) {
                    moveDown = dp[i - 1][j][1] == -1 ? 0 : dp[i - 1][j][1];
                } else {
                    moveDown = (dp[i - 1][j][0] == -1 ? 0 : dp[i - 1][j][0]) + (dp[i - 1][j][1] == -1 ? 0 : dp[i - 1][j][1]);
                }
                dp[i][j][1] = moveDown % MOD;
            }
        }

        int answer = (dp[m - 1][n - 1][0] == -1 ? 0 : dp[m - 1][n - 1][0]) + (dp[m - 1][n - 1][1] == -1 ? 0 : dp[m - 1][n - 1][1]);

        return answer % MOD;
    }
}
