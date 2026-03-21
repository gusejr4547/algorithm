package 프로그래머스.카카오_2025_하반기_2차;

import java.util.HashMap;
import java.util.Map;

public class 기차_선로 {
    public static void main(String[] args) {

    }

    // 선로 7가지
    // (0,0)에서 (n,m)으로 이동해야함
    // grid에 주어지는 선로는 무조건 지나가야함.
    // # 모양의 3번 선로는 상하좌우 모든 방향으로 연결
    // 선로를 놓는 방법 수 return

    // n, m <= 8, n × m ≤ 20

    int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    int N, M;
    int[][] grid;
    int[][] visit;

    public int solution(int[][] grid) {
        N = grid.length;
        M = grid[0].length;
        this.grid = grid;
        visit = new int[N][M]; // 0 방문안함, 1 가로, 2 세로, 3 완전 끝

        // dfs로 풀기

        int answer = dfs(0, 0, RIGHT);
        return answer;
    }

    private int dfs(int y, int x, int dir) {
        // 밖이나 장애물
        if (y < 0 || x < 0 || y >= N || x >= M || grid[y][x] == -1) {
            return 0;
        }

        // 도착
        if (y == N - 1 && x == M - 1) {
            // 선로와 온 방향 맞는지 확인
            // 선로 1
            if (grid[y][x] == 1 && (dir == UP || dir == DOWN)) {
                return 0;
            }
            // 선로 2
            if (grid[y][x] == 2 && (dir == LEFT || dir == RIGHT)) {
                return 0;
            }

            int prev = visit[y][x];
            visit[y][x] = 3;

            // 선로 전부 지나갔는지 확인
            boolean success = checkAllTrackVisited();

            // 다시 상태 되돌리기
            visit[y][x] = prev;
            return success ? 1 : 0;
        }

        int result = 0;
        int rail = grid[y][x];
        int state = visit[y][x];

        // 현재 칸이 빈칸
        if (rail == 0) {
            // 방문 안한 칸
            if (state == 0) {
                // 그대로 직진
                visit[y][x] = (dir % 2 == 1) ? 1 : 2;
                result += dfs(y + dy[dir], x + dx[dir], dir);

                // 좌회전
                int nextDir = (dir + 3) % 4;
                visit[y][x] = 3;
                result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);

                // 우회전
                nextDir = (dir + 1) % 4;
                visit[y][x] = 3;
                result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);

                // 상태 되돌리기
                visit[y][x] = state;
            }
            // state 1에서 세로로 방문
            else if (state == 1 && dir % 2 == 0) {
                // 십자 모양
                visit[y][x] = 3;
                result += dfs(y + dy[dir], x + dx[dir], dir);
                visit[y][x] = state;
            }
            // state 2에서 가로로 방문
            else if (state == 2 && dir % 2 == 1) {
                // 십자 모양
                visit[y][x] = 3;
                result += dfs(y + dy[dir], x + dx[dir], dir);
                visit[y][x] = state;
            }
        }
        // 현재 칸이 rail 있음
        else {
            // 선로 번호에 따라 dir이 올바른지?
            if (!isValid(y, x, dir)) {
                return 0;
            }

            // state 확인
            // 방문 안한 곳
            if (state == 0) {
                // 다음 dir은 rail 마다 다르게 결정됨
                if (rail == 1 || rail == 2) {
                    // 직진
                    visit[y][x] = 3;
                    result += dfs(y + dy[dir], x + dx[dir], dir);
                    visit[y][x] = state;
                } else if (rail == 3) {
                    // 직진인데 상태 변경 dir에 따라 다르게
                    visit[y][x] = (dir % 2 == 1) ? 1 : 2;
                    result += dfs(y + dy[dir], x + dx[dir], dir);
                    visit[y][x] = state;
                } else if (rail == 4) {
                    int nextDir = (dir == DOWN) ? LEFT : UP;
                    visit[y][x] = 3;
                    result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);
                    visit[y][x] = state;
                } else if (rail == 5) {
                    int nextDir = (dir == DOWN) ? RIGHT : UP;
                    visit[y][x] = 3;
                    result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);
                    visit[y][x] = state;
                } else if (rail == 6) {
                    int nextDir = (dir == UP) ? RIGHT : DOWN;
                    visit[y][x] = 3;
                    result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);
                    visit[y][x] = state;
                } else if (rail == 7) {
                    int nextDir = (dir == UP) ? LEFT : DOWN;
                    visit[y][x] = 3;
                    result += dfs(y + dy[nextDir], x + dx[nextDir], nextDir);
                    visit[y][x] = state;
                }
            }
            // 방문한 적 있는 곳
            else {
                // 3번만 가능
                if (rail == 3) {
                    // 가로로 왔었음 or 세로로 왔었음
                    if ((state == 1 && dir % 2 == 0) || (state == 2 && dir % 2 == 1)) {
                        visit[y][x] = 3;
                        result += dfs(y + dy[dir], x + dx[dir], dir);
                        visit[y][x] = state;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }

        return result;
    }


    private boolean checkAllTrackVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] > 0) {
                    if (visit[i][j] != 3) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValid(int y, int x, int dir) {
        int rail = grid[y][x];
        if (rail == 1) {
            // LEFT, RIGHT
            return dir == LEFT || dir == RIGHT;
        } else if (rail == 2) {
            // UP, DOWN
            return dir == UP || dir == DOWN;
        } else if (rail == 3) {
            // 전방향 가능
            return true;
        } else if (rail == 4) {
            // DOWN, RIGHT
            return dir == DOWN || dir == RIGHT;
        } else if (rail == 5) {
            // DOWN, LEFT
            return dir == DOWN || dir == LEFT;
        } else if (rail == 6) {
            // UP, LEFT
            return dir == UP || dir == LEFT;
        } else {
            // RIGHT, UP
            return dir == RIGHT || dir == UP;
        }
    }
}
