package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P12850_본대_산책2 {
    static final int Node = 8;
    static final int MOD = 1_000_000_007;

    // 주어진 지도에 맞게 그래프 구성
    // 간선의 cost는 1
    static long[][] adj = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());

        long[][] result = cal(D, adj);

//        for(long[] d : result){
//            System.out.println(Arrays.toString(d));
//        }

        System.out.println(result[0][0]);

    }

    private static long[][] cal(int d, long[][] matrix) {
        if (d == 1) {
            return matrix;
        } else {
            long[][] divide = cal(d / 2, matrix);

            if (d % 2 == 0) {
                return matrixMulti(divide, divide);
            } else {
                return matrixMulti(matrixMulti(divide, divide), matrix);
            }
        }
    }

    private static long[][] matrixMulti(long[][] A, long[][] B) {
        long[][] matrix = new long[Node][Node];
        for (int i = 0; i < Node; i++) {
            for (int j = 0; j < Node; j++) {
                long sum = 0;
                for (int k = 0; k < Node; k++) {
                    sum = (sum + A[i][k] * B[k][j]) % MOD;
                }
                matrix[i][j] = sum;
            }
        }

        return matrix;
    }

}
