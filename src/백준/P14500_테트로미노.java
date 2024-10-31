package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14500_테트로미노 {
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int N, M, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visit[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int y, int x, int sum, int count) {
        if (count == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx])
                continue;

            if (count == 2) {
                visit[ny][nx] = true;
                dfs(y, x, sum + map[ny][nx], count + 1); // 같은 위치 한번더 탐색
                visit[ny][nx] = false;
            }

            visit[ny][nx] = true;
            dfs(ny, nx, sum + map[ny][nx], count + 1);
            visit[ny][nx] = false;
        }
    }
}
