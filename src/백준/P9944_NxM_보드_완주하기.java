package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9944_NxM_보드_완주하기 {
    static final int MAX = 9999999;
    static int N, M;
    static char[][] map;
    static boolean[][] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            String input = br.readLine();
            if (input == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }
            visit = new boolean[N][M];
            int answer = MAX;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        visit[i][j] = true;
                        answer = Math.min(answer, find(i, j));
                        visit[i][j] = false;
                    }
                }
            }

            if (answer == MAX) {
                System.out.printf("Case %d: %d\n", tc, -1);
            } else {
                System.out.printf("Case %d: %d\n", tc, answer);
            }
            tc++;
        }
    }

    private static int find(int y, int x) {
//        visit[y][x] = true;

        int move = MAX;
        // 4방향으로 직선 이동
        for (int d = 0; d < 4; d++) {
            int ny = y;
            int nx = x;
            int cnt = 0;
            while (true) {
                ny = ny + dy[d];
                nx = nx + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == '*' || visit[ny][nx]) {
                    ny = ny - dy[d];
                    nx = nx - dx[d];
                    break;
                }
                visit[ny][nx] = true;
                cnt++;
            }

            if (cnt != 0) {
                move = Math.min(move, find(ny, nx) + 1);

                while (cnt-- > 0) {
                    visit[ny][nx] = false;
                    ny = ny - dy[d];
                    nx = nx - dx[d];
                }
            }
        }

        // move가 갱신 안되면 더 움직이지 못함.
        if (move == MAX) {
            // 전부 방문했는지 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.' && !visit[i][j]) {
                        return MAX;
                    }
                }
            }
            return 0;
        }

        return move;
    }
}
