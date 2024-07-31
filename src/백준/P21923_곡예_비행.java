package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21923_곡예_비행 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] score = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] scoreSum = new int[N][M][2]; // 상승, 하강
        // 상승
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                int max = Integer.MIN_VALUE;
                if (j - 1 >= 0) {
                    max = Math.max(max, scoreSum[i][j - 1][0]);
                }
                if (i + 1 < N) {
                    max = Math.max(max, scoreSum[i + 1][j][0]);
                }
                if (i == N - 1 && j == 0) {
                    scoreSum[i][j][0] = score[i][j];
                } else {
                    scoreSum[i][j][0] = max + score[i][j];
                }
            }
        }

        // 하강
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                int max = Integer.MIN_VALUE;
                if (j + 1 < M) {
                    max = Math.max(max, scoreSum[i][j + 1][1]);
                }
                if (i + 1 < N) {
                    max = Math.max(max, scoreSum[i + 1][j][1]);
                }
                if (i == N - 1 && j == M - 1) {
                    scoreSum[i][j][1] = score[i][j];
                } else {
                    scoreSum[i][j][1] = max + score[i][j];
                }
            }
        }
//        System.out.println("######### 상승 ##########");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(scoreSum[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("######### 하강 ##########");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(scoreSum[i][j][1] + " ");
//            }
//            System.out.println();
//        }

        // 상승 + 하강 가장 큰거
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(result, scoreSum[i][j][0] + scoreSum[i][j][1]);
            }
        }
        System.out.println(result);
    }
}
