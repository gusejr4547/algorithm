package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16929_Two_Dots {
    // 각각의 칸은 색이 칠해진 공이 하나씩 있다.
    // 이 게임의 핵심은 같은 색으로 이루어진 사이클을 찾는 것이다.
    // 점 k개 d1, d2, ..., dk로 이루어진 사이클의 정의는 아래와 같다.
    // 모든 k개의 점은 서로 다르다.
    // k는 4보다 크거나 같다.
    // 모든 점의 색은 같다.
    // 모든 1 ≤ i ≤ k-1에 대해서, di와 di+1은 인접하다. 또, dk와 d1도 인접해야 한다.
    // 두 점이 인접하다는 것은 각각의 점이 들어있는 칸이 변을 공유한다는 의미이다.
    static int N, M;
    static char[][] grid;
    static boolean[][] visit;
    static boolean result;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        visit = new boolean[N][M];
        // 출발해서 자기자신으로 돌아올 수 있으면 cycle
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j]) {
                    continue;
                }
                if (dfs(i, j, -1, -1, grid[i][j])) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    private static boolean dfs(int r, int c, int prevR, int prevC, char color) {
        visit[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dy[d];
            int nc = c + dx[d];

            // 범위
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }

            // 색
            if (grid[nr][nc] != color) {
                continue;
            }

            if (!visit[nr][nc]) {
                if (dfs(nr, nc, r, c, color)) {
                    return true;
                }
            } else {
                if (prevR != nr || prevC != nc) {
                    return true;
                }
            }
        }
        return false;
    }
}
