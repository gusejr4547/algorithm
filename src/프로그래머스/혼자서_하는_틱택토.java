package 프로그래머스;

public class 혼자서_하는_틱택토 {
    public static void main(String[] args) {
        혼자서_하는_틱택토 Main = new 혼자서_하는_틱택토();
        String[] board = {};
        System.out.println(Main.solution(board));
    }

    public int solution(String[] board) {
        int n = 3;
        char[][] b = new char[n][];
        for (int i = 0; i < n; i++) {
            b[i] = board[i].toCharArray();
        }

        int countO = 0;
        int countX = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] == 'O') {
                    countO++;
                } else if (b[i][j] == 'X') {
                    countX++;
                }
            }
        }

        boolean[] win = whoWin(b);


        // O개수 >= X개수
        if (0 > countO - countX || countO - countX > 1) {
            return 0;
        }

        // O 승리 X 승리 둘다 있을 수 없음
        if (win[0] && win[1]) {
            return 0;
        }

        // O가 이기면 O개수 == X개수 + 1
        if (win[0] && countO != countX + 1) {
            return 0;
        }

        // X가 이기면 X개수 == O개수
        if (win[1] && countO != countX) {
            return 0;
        }

        return 1;
    }

    private boolean[] whoWin(char[][] board) {
        int n = 3;
        boolean[] win = new boolean[2];
        // 가로
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    count++;
                } else if (board[i][j] == 'X') {
                    count--;
                }
            }
            if (count == 3) {
                win[0] = true;
            } else if (count == -3) {
                win[1] = true;
            }
        }
        // 세로
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (board[i][j] == 'O') {
                    count++;
                } else if (board[i][j] == 'X') {
                    count--;
                }
            }
            if (count == 3) {
                win[0] = true;
            } else if (count == -3) {
                win[1] = true;
            }
        }
        // 대각선
        int countD = 0;
        int countR = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][i] == 'O') {
                countD++;
            } else if (board[i][i] == 'X') {
                countD--;
            }

            if (board[i][n - i - 1] == 'O') {
                countR++;
            } else if (board[i][n - i - 1] == 'X') {
                countR--;
            }
        }
        if (countD == 3) {
            win[0] = true;
        } else if (countD == -3) {
            win[1] = true;
        }

        if (countR == 3) {
            win[0] = true;
        } else if (countR == -3) {
            win[1] = true;
        }

        return win;
    }
}
