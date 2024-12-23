package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17090_미로_탈출하기 {
    static int N, M;
    static char[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j)) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int y, int x) {
        if (y < 0 || x < 0 || y >= N || x >= M) {
            return true;
        }

        if (map[y][x] == 'T') {
            return true;
        } else if (map[y][x] == 'F') {
            return false;
        }

        if (visit[y][x]) {
            return false;
        }
        visit[y][x] = true;

        // 다음 위치 이동
        int ny = y;
        int nx = x;
        if (map[y][x] == 'U') {
            ny -= 1;
        } else if (map[y][x] == 'R') {
            nx += 1;
        } else if (map[y][x] == 'D') {
            ny += 1;
        } else if (map[y][x] == 'L') {
            nx -= 1;
        }

        boolean result = dfs(ny, nx);
        if (result) {
            map[y][x] = 'T';
            return true;
        } else {
            map[y][x] = 'F';
            return false;
        }
    }
}
