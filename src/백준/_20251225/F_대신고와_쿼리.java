package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F_대신고와_쿼리 {
    // 4xN 의 좌표
    // 공사 => 장소 and 상,하,좌,우 영향

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] grid = new int[5][N + 1];

        // N, Q <= 1000
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (1 == type) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                grid[r][c]++;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dy[d];
                    int nc = c + dx[d];
                    if (nr >= 1 && nc >= 1 && nr <= 4 && nc <= N) {
                        grid[nr][nc]++;
                    }
                }
            } else {
                int r = Integer.parseInt(st.nextToken());
                int maxC = 0;
                int maxV = -1;
                // gird[r] 에서 가장 높은 값을 가진 c
                for (int c = 1; c <= N; c++) {
                    if (maxV < grid[r][c]) {
                        maxV = grid[r][c];
                        maxC = c;
                    }
                }
                sb.append(maxC).append("\n");
            }
        }

        // 가장 높은 값을 가진 r, c
        // 값이 같으면 r이 더 작은것, c가 더 작은것
        int aR = 0;
        int aC = 0;
        int maxV = -1;
        for (int r = 1; r <= 4; r++) {
            for (int c = 1; c <= N; c++) {
                if (maxV < grid[r][c]) {
                    aR = r;
                    aC = c;
                    maxV = grid[r][c];
                }
            }
        }

        sb.append(aR).append(" ").append(aC);

        System.out.println(sb);
    }
}
