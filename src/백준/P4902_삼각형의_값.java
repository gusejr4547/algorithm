package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4902_삼각형의_값 {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            // 0입력되면 종료
            if (N == 0) {
                break;
            }

            // 삼각형 가장 큰 값
            int result = Integer.MIN_VALUE;
            // 단위 삼각형 개수 == N*N;
            int[][] values = new int[N + 1][2 * N];
            // 누적합 >> 행별로
            int[][] preSum = new int[N + 1][2 * N];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= 2 * i - 1; j++) {
                    // 입력되는거는 단위 1짜리 삼각형
                    int v = Integer.parseInt(st.nextToken());
                    values[i][j] = v;
                    preSum[i][j] = preSum[i][j - 1] + v;
                }
            }

            // 그냥 삼각형
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= 2 * i - 1; j += 2) {
                    // (i,j)를 기준으로 삼각형 >> j가 홀수여야함.
                    int sum = 0;
                    for (int k = 0; k <= N - i; k++) {
                        sum += preSum[i + k][j + 2 * k] - preSum[i + k][j - 1];
                        result = Math.max(result, sum);
                    }
                }
            }

            // 역삼각형
            for (int i = 1; i <= N; i++) {
                for (int j = 2; j <= 2 * i - 1; j += 2) {
                    // (i,j)기준으로 역삼각형 >> j가 짝수여야함.
                    int sum = 0;
                    for (int k = 0; k < Math.min(j / 2, i - j / 2); k++) {
                        sum += preSum[i - k][j] - preSum[i - k][j - 2 * k - 1];
                        result = Math.max(result, sum);
                    }
                }
            }

            sb.append(tc).append(". ").append(result).append("\n");
            tc++;
        }
        System.out.println(sb);
    }
}
