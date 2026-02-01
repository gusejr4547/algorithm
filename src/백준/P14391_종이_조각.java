package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14391_종이_조각 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = 0;

        // 0은 가로, 1은 세로
        for (int state = 0; state < (1 << (N * M)); state++) {
            int sum = 0;

            // 가로로 잘린거 계산
            for (int i = 0; i < N; i++) {
                int cur = 0;
                for (int j = 0; j < M; j++) {
                    // bit 위치
                    int k = i * M + j;
                    // 가로
                    if ((state & (1 << k)) == 0) {
                        cur = cur * 10 + map[i][j];
                    }
                    // 세로
                    else {
                        sum += cur;
                        cur = 0;
                    }
                }
                // 행끝나면 남은거 계산
                sum += cur;
            }

            // 세로로 잘린거 계산
            for (int j = 0; j < M; j++) {
                int cur = 0;
                for (int i = 0; i < N; i++) {
                    int k = i * M + j;
                    if ((state & (1 << k)) != 0) {
                        cur = cur * 10 + map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
