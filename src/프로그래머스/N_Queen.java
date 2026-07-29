package 프로그래머스;

public class N_Queen {
    public static void main(String[] args) {

    }

    int answer;

    public int solution(int n) {
        answer = 0;
        boolean[][] board = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            board[0][i] = true;
            dfs(1, n, board);
            board[0][i] = false;
        }

        return answer;
    }

    private void dfs(int count, int n, boolean[][] board) {
        if (count == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // 위치에 놓기 가능?
            if (check(count, i, n, board)) {
                board[count][i] = true;
                dfs(count + 1, n, board);
                board[count][i] = false;
            }
        }
    }

    private boolean check(int r, int c, int n, boolean[][] board) {
        int y = r;
        int x = c;
        // 대각 1
        while (--y >= 0 && --x >= 0) {
            if (board[y][x]) {
                return false;
            }
        }

        // 대각 2
        y = r;
        x = c;
        while (--y >= 0 && ++x < n) {
            if (board[y][x]) {
                return false;
            }
        }

        // 세로
        y = r;
        x = c;
        while (--y >= 0) {
            if (board[y][x]) {
                return false;
            }
        }

        return true;
    }
}
