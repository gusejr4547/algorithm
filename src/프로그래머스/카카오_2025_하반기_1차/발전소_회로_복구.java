package 프로그래머스.카카오_2025_하반기_1차;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 발전소_회로_복구 {
    public static void main(String[] args) {

    }

    // 각 층은 같은 구조의 n×m 크기의 격자
    // 기술자는 항상 1번 패널이 설치된 위치에서 출발해 모든 회로 패널을 활성화해야 합니다.
    // 상하좌우로 인접한 칸으로 1초에 1칸씩 이동
    // 엘리베이터 칸에서만 다른 층으로 이동
    // 한 층을 이동할 때마다 1초
    // 각 패널은 안전 순서를 따라 활성화 => 위상정렬

    // h <= 10, n,m <= 40, k <= 15

    int N, M, K;
    char[][] map;
    int[] elv, req;
    int[][] dp;

    int INF = 987654321;
    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        N = grid.length;
        M = grid[0].length();
        K = panels.length;
        map = new char[N][M];
        elv = new int[2];
        for (int i = 0; i < N; i++) {
            map[i] = grid[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '@') {
                    elv[0] = i;
                    elv[1] = j;
                }
            }
        }

        // 패널 열기위해 필요한 조건
        req = new int[K];
        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            req[b] |= (1 << a);
        }

        // 거리 계산
        int[][] dist = new int[K + 1][K + 1];
        int[][] pos = new int[K + 1][2];
        for (int i = 0; i < K; i++) {
            pos[i][0] = panels[i][1] - 1;
            pos[i][1] = panels[i][2] - 1;
        }
        pos[K][0] = elv[0];
        pos[K][1] = elv[1];

        for (int i = 0; i <= K; i++) {
            // pos[i] 로 부터 출발해서 도달하는 거리 d에 전부 기록
            int[][] m = new int[N][M];
            for (int[] r : m) {
                Arrays.fill(r, -1);
            }
            ArrayDeque<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{pos[i][0], pos[i][1]});
            m[pos[i][0]][pos[i][1]] = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int y = cur[0];
                int x = cur[1];

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || m[ny][nx] != -1 || map[ny][nx] == '#') {
                        continue;
                    }

                    m[ny][nx] = m[y][x] + 1;
                    queue.offer(new int[]{ny, nx});
                }
            }

            // dist 기록
            for (int j = 0; j <= K; j++) {
                dist[i][j] = m[pos[j][0]][pos[j][1]];
            }
        }

        // 출발지점은 1번이다.
        // 조작할 수 있는 패널을 선택하는 것은 전부 확인이 필요함.
        // bit로 visit 만들어서 dp이용
        dp = new int[1 << K][K];
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        dp[0][0] = 0;

        for (int mask = 0; mask < (1 << K); mask++) {
            // 현재 a패널에 있고 b패널 키러가기
            for (int a = 0; a < K; a++) {
                // 도달할 수 없음
                if (dp[mask][a] == INF) {
                    continue;
                }

                for (int b = 0; b < K; b++) {
                    // b가 아직 안켜진 상태여야함
                    // b가 현재 mask로 킬 수 있어야함
                    if ((mask & (1 << b)) == 0 && (req[b] & mask) == req[b]) {
                        // 층
                        int aFloor = panels[a][0];
                        int bFloor = panels[b][0];

                        int cost = 0;
                        // 층이 같다
                        if (aFloor == bFloor) {
                            cost = dist[a][b];
                        }
                        // 층이 다르다
                        else {
                            cost = dist[a][K] + Math.abs(aFloor - bFloor) + dist[K][b];
                        }

                        // dp 갱신
                        int nextMask = mask | (1 << b);
                        dp[nextMask][b] = Math.min(dp[nextMask][b], dp[mask][a] + cost);
                    }
                }
            }
        }

        int answer = INF;
        int allMask = (1 << K) - 1;
        for (int i = 0; i < K; i++) {
            answer = Math.min(answer, dp[allMask][i]);
        }
        return answer;
    }
}
