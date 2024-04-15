package 프로그래머스;

public class 파괴되지_않은_건물 {
    public static void main(String[] args) {
        파괴되지_않은_건물 Main = new 파괴되지_않은_건물();
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(Main.solution(board, skill));
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;
        int[][] sum = new int[r + 1][c + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            if (type == 1) {
                sum[r1][c1] -= degree;
                sum[r1][c2 + 1] += degree;
                sum[r2 + 1][c1] += degree;
                sum[r2 + 1][c2 + 1] -= degree;
            } else {
                sum[r1][c1] += degree;
                sum[r1][c2 + 1] -= degree;
                sum[r2 + 1][c1] -= degree;
                sum[r2 + 1][c2 + 1] += degree;
            }
        }

        // 누적합 계산
        // 위 -> 아래
        for (int j = 0; j <= c; j++) {
            for (int i = 1; i <= r; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        // 왼 -> 오
        for (int i = 0; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + sum[i][j] > 0)
                    answer++;
            }
        }

        return answer;
    }
}
