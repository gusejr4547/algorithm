package 프로그래머스;

public class 사라지는_발판 {
    public static void main(String[] args) {
        사라지는_발판 Main = new 사라지는_발판();
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
        System.out.println(Main.solution(board, aloc, bloc));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};
    int row, col;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;

        Result result = turnA(board, aloc, bloc);

        return result.turn;
    }

    public Result turnA(int[][] board, int[] aloc, int[] bloc) {
        int ay = aloc[0];
        int ax = aloc[1];
        if (board[ay][ax] == 0)
            return new Result(false, 0);

        boolean isWin = false;
        int win = Integer.MAX_VALUE;
        int lose = 0;
        boolean canMove = false;

        for (int d = 0; d < 4; d++) {
            int ny = ay + dy[d];
            int nx = ax + dx[d];

            if (ny < 0 || nx < 0 || ny >= row || nx >= col || board[ny][nx] == 0) continue;
            canMove = true;
            board[ay][ax] = 0;

            Result B = turnB(board, new int[]{ny, nx}, bloc);

            if (B.win) {
                lose = Math.max(lose, B.turn);
            } else {
                isWin = true;
                win = Math.min(win, B.turn);
            }

            board[ay][ax] = 1;
        }

        if(!canMove){
            return new Result(false, 0);
        }
        return new Result(isWin, (isWin ? win : lose) + 1);
    }

    public Result turnB(int[][] board, int[] aloc, int[] bloc) {
        int by = bloc[0];
        int bx = bloc[1];
        if (board[by][bx] == 0)
            return new Result(false, 0);

        boolean isWin = false;
        int win = Integer.MAX_VALUE;
        int lose = 0;
        boolean canMove = false;

        for (int d = 0; d < 4; d++) {
            int ny = by + dy[d];
            int nx = bx + dx[d];

            if (ny < 0 || nx < 0 || ny >= row || nx >= col || board[ny][nx] == 0) continue;
            canMove = true;
            board[by][bx] = 0;

            Result A = turnA(board, aloc, new int[]{ny, nx});

            if (A.win) {
                lose = Math.max(lose, A.turn);
            } else {
                isWin = true;
                win = Math.min(win, A.turn);
            }

            board[by][bx] = 1;
        }

        if(!canMove){
            return new Result(false, 0);
        }
        return new Result(isWin, (isWin ? win : lose) + 1);
    }

    static class Result {
        boolean win;
        int turn;

        public Result(boolean win, int turn) {
            this.win = win;
            this.turn = turn;
        }
    }

}