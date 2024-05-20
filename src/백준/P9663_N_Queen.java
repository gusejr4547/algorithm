package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P9663_N_Queen {
    static int result = 0;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] queenCol = new int[N];

        NQueen(0, queenCol);

        System.out.println(result);
    }

    private static void NQueen(int y, int[] queenCol) {
        if (y == N) {
//            System.out.println("queenCol = " + Arrays.toString(queenCol));
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            // 놓을 수 있는지 확인
            if (isValid(y, i, queenCol)) {
                queenCol[y] = i;
                NQueen(y + 1, queenCol);
            }
        }

    }

    private static boolean isValid(int y, int x, int[] queenCol) {
        for (int row = 0; row < y; row++) {
            int col = queenCol[row];
            if (col == x || row + col == y + x || row - col == y - x) {
                return false;
            }
        }
        return true;
    }
}
