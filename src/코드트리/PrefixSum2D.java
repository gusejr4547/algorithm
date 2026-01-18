package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixSum2D {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] count1 = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count1[i][j] = count1[i - 1][j] + count1[i][j - 1] - count1[i - 1][j - 1];
                if (grid[i][j] == 1) {
                    count1[i][j] += 1;
                }
            }
        }

        int maxDiff = 0;
        int area = K * K;

        for (int i = 1; i <= N - K + 1; i++) {
            for (int j = 1; j <= N - K + 1; j++) {
                // (i,j)좌상 기준
                int r2 = i + K - 1;
                int c2 = j + K - 1;

                // 구간 내의 1
                int a = count1[r2][c2] - count1[r2][j - 1] - count1[i - 1][c2] + count1[i - 1][j - 1];
                int b = area - a;
                int diff = Math.abs(a - b);

                maxDiff = Math.max(maxDiff, diff);
            }
        }

        System.out.println(maxDiff);
    }
}
