package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2096_내려가기 {
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];
        for (int i = 0; i < N; i++) {
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }
        for (int j = 0; j < 3; j++) {
            maxDp[0][j] = map[0][j];
            minDp[0][j] = map[0][j];
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 3; j++) {
                for (int d = 0; d < 3; d++) {
                    int ny = i + 1;
                    int nx = j + dx[d];
                    if (0 <= nx && nx < 3) {
                        maxDp[ny][nx] = Math.max(maxDp[ny][nx], maxDp[i][j] + map[ny][nx]);
                        minDp[ny][nx] = Math.min(minDp[ny][nx], minDp[i][j] + map[ny][nx]);
                    }
                }
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            max = Math.max(max, maxDp[N - 1][j]);
            min = Math.min(min, minDp[N - 1][j]);
        }
        System.out.println(max + " " + min);
    }
}
