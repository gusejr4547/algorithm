package 프로그래머스.PCCE기출문제;

import java.util.Arrays;

public class 공원 {
    public static void main(String[] args) {

    }

    public int solution(int[] mats, String[][] park) {
        int N = park.length;
        int M = park[0].length;
        int[][] sum = new int[N + 1][M + 1];

        // 누적합
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int fill = park[i][j].equals("-1") ? 0 : 1;

                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + fill;
            }
        }

        // 확인
        Arrays.sort(mats); // 큰거부터 확인하기 위해

        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];

            for (int r = 0; r + size <= N; r++) {
                for (int c = 0; c + size <= M; c++) {
                    int p = sum[r + size][c + size] - sum[r][c + size] - sum[r + size][c] + sum[r][c];

                    if (p == 0) {
                        return size;
                    }
                }
            }
        }

        return -1;
    }
}
