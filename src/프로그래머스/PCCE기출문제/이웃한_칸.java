package 프로그래머스.PCCE기출문제;

public class 이웃한_칸 {
    public static void main(String[] args) {

    }

    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        int N = board.length;

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        for (int d = 0; d < 4; d++) {
            int ny = h + dy[d];
            int nx = w + dx[d];

            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }

            if (board[h][w].equals(board[ny][nx])) {
                answer++;
            }
        }

        return answer;
    }
}
