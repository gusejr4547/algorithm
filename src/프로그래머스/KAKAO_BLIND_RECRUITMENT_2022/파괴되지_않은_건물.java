package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2022;

public class 파괴되지_않은_건물 {
    public static void main(String[] args) {
        파괴되지_않은_건물 Main = new 파괴되지_않은_건물();
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(Main.solution(board, skill));
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;
        int[][] preSum = new int[n + 1][m + 1];

        // 차분 배열을 사용
        for (int[] turn : skill) {
            int type = turn[0];
            int r1 = turn[1];
            int c1 = turn[2];
            int r2 = turn[3];
            int c2 = turn[4];
            int degree = turn[5];

            if (type == 1) {
                preSum[r1][c1] -= degree;
                preSum[r1][c2 + 1] += degree;
                preSum[r2 + 1][c1] += degree;
                preSum[r2 + 1][c2 + 1] -= degree;
            } else {
                preSum[r1][c1] += degree;
                preSum[r1][c2 + 1] -= degree;
                preSum[r2 + 1][c1] -= degree;
                preSum[r2 + 1][c2 + 1] += degree;
            }
        }

        // 누적합 계산
        // 왼 -> 오
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preSum[i][j] += preSum[i][j - 1];
            }
        }

        // 위 -> 아래
        for (int j = 0; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                preSum[i][j] += preSum[i - 1][j];
            }
        }

        // 원래 배열에 더하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += preSum[i][j];
            }
        }

        // 파괴되지 않은 건물 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
