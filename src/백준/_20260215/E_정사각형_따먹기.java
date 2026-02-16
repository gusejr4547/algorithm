package 백준._20260215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class E_정사각형_따먹기 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            boolean[][] grid = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                Arrays.fill(grid[i], true);
            }

            int count = 0;
            StringBuilder delete = new StringBuilder();
            for (int y = 0; y < H; y++) {
                for (int x = 0; x < W; x++) {
                    int adj = 0;

                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny >= 0 && nx >= 0 && ny < H && nx < W && grid[ny][nx]) {
                            adj++;
                        }
                    }

                    // 인접한 곳이 2개 있으면 지우기
                    if (adj == 2) {
                        grid[y][x] = false;
                        count++;
                        delete.append(x + 1).append(' ').append(y + 1).append('\n');
                    }
                }
            }

            sb.append(count).append('\n');
            sb.append(delete);
        }
        System.out.println(sb);
    }
}
