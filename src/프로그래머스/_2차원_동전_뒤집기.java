package 프로그래머스;

public class _2차원_동전_뒤집기 {
    public static void main(String[] args) {
        _2차원_동전_뒤집기 Main = new _2차원_동전_뒤집기();
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        System.out.println(Main.solution(beginning, target));
    }

    int r, c;
    int[][] board;
    int[][] tg;
    int result;

    public int solution(int[][] beginning, int[][] target) {
        // 뒤집는 횟수 홀수번 => 변함, 짝수번 => 그대로
        // 결국 1줄씩 (뒤집는다, 안뒤집는다) 선택 하는 방법
        // 최대 20번 선택 => 2^20
        r = beginning.length;
        c = beginning[0].length;
        board = beginning;
        tg = target;

        result = Integer.MAX_VALUE;

        dfs(0, 0);

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return result;
    }

    public void dfs(int row, int count) {
        if (row == r) {
            // col 확인
            // target과 완전 같거나 완전 반대 => ok
            // 아니면 => row선택 다시
            boolean isEqual = true;

            for (int col = 0; col < c; col++) {
                // col 비교
                int n = 0;
                for (int i = 0; i < r; i++) {
                    if (board[i][col] == tg[i][col]) {
                        n++;
                    }
                }
                if (n == 0)
                    count += 1;
                else if (n == r)
                    count += 0;
                else
                    return;
            }

            result = Math.min(result, count);
            return;
        }


        // 안뒤집기
        dfs(row + 1, count);
        // 뒤집기
        flip(row);
        dfs(row + 1, count + 1);
        flip(row);

    }

    public void flip(int row) {
        for (int col = 0; col < c; col++) {
            board[row][col] = board[row][col] == 1 ? 0 : 1;
        }
    }
}
