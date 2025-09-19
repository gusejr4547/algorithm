package 코드트리.HSAT;

import java.util.Scanner;

public class 로봇이_지나간_경로 {
    static int H, W;
    static char[][] resultGrid;
    static boolean[][] visit;
    // ^>v<
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        resultGrid = new char[H][W];
        for (int i = 0; i < H; i++) {
            String line = sc.next();
            for (int j = 0; j < W; j++) {
                resultGrid[i][j] = line.charAt(j);
            }
        }

        // 바라보는 방향 = 4가지
        // 명령어 L : 왼쪽90회전, R : 오른쪽90회전, A : 2칸 전진(격자 밖으로 나가는 경우 제외)
        // 같은 칸 두번이상 방문 금지
        // 명령어 최소화

        // 명령어의 개수를 최소화하면서 목표를 달성할 수 있는 방법이 여러 가지라면,
        // 그 중 로봇을 둬야하는 위치의 행의 번호가 가장 큰 것의 방법을 출력합니다.
        // 만약 행의 번호가 가장 큰 것도 여러 가지라면, 열의 번호가 가장 큰 것을 출력합니다.

        visit = new boolean[H][W];
        int sy = 0, sx = 0, sd = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (resultGrid[i][j] == '#') {
                    // 4방향 중에 1개만 갈곳이 있는 경우 시작점
                    int count = 0, dir = -1;
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
                            continue;
                        }
                        if (resultGrid[ny][nx] == '#') {
                            count++;
                            dir = d;
                        }
                    }

                    if (count == 1) {
                        sy = i;
                        sx = j;
                        sd = dir;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sy + 1).append(" ").append(sx + 1).append("\n");
        sb.append("^>v<".charAt(sd)).append("\n");
        // sy, sx, sd 로 출발
        dfs(sy, sx, sd, sb);

        System.out.println(sb.toString());
    }

    private static void dfs(int y, int x, int dir, StringBuilder sb) {
        // 방문 체크
        visit[y][x] = true;

        // 다음 명령어
        int count = 0;
        int ndir = dir;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= H || nx >= W || visit[ny][nx]) {
                continue;
            }
            if (resultGrid[ny][nx] == '#') {
                count++;
                ndir = d;
            }
        }

        if (count == 0) {
            return;
        }

        // A : dir 방향으로 2칸 전진
        if (ndir == dir) {
            sb.append("A");
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            visit[ny][nx] = true;
            ny += dy[dir];
            nx += dx[dir];
            dfs(ny, nx, dir, sb);
        }

        // L : 왼쪽90회전 후 전진
        if (ndir == ((dir + 3) % 4)) {
            sb.append("LA");
            int ny = y + dy[ndir];
            int nx = x + dx[ndir];
            visit[ny][nx] = true;
            ny += dy[ndir];
            nx += dx[ndir];
            dfs(ny, nx, ndir, sb);
        }

        // R : 오른쪽90회전 후 전진
        if (ndir == ((dir + 1) % 4)) {
            sb.append("RA");
            int ny = y + dy[ndir];
            int nx = x + dx[ndir];
            visit[ny][nx] = true;
            ny += dy[ndir];
            nx += dx[ndir];
            dfs(ny, nx, ndir, sb);
        }
    }
}
