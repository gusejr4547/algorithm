package 코드트리.기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
7 3 1
4 0 0 0 0 0 0
0 1 1 1 1 1 0
0 1 3 3 3 1 0
0 0 3 3 3 1 0
0 1 3 3 3 1 0
0 1 1 1 1 1 0
0 0 0 0 0 0 0
1 0 0
1 1 1
1 1 1
1 1 1
0 0 1
1 0 1
0 0 0
1 1 1
1 1 1
0 1 1
0 0 0
1 1 1
1 1 1
2 1 0
0 1 0
1 0 3 3

36
*/

// 나는 상하좌우 한칸씩 이동가능
// 시간 이상 현상 => r,c 에서 v턴마다 d방향으로 한칸씩 확산
// F <= 10

// 장애물과 시간 이상 현상을 피해 => 탈출구로
// 최소 시간 출력
// 실패하면 -1
public class 미지의_공간_탈출 {
    static int N, M, F, timeMapSY, timeMapSX;
    static int[][] map;
    static int[][][] timeMap;
    static Abnormal[] timeAbnormalList;

    // 동 서 남 북
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        // 미지의 공간 평면도
        map = new int[N][N];
        boolean find = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 공간에서의 시간평면 위치
                if (map[i][j] == 3 && !find) {
                    timeMapSY = i;
                    timeMapSX = j;
                    find = true;
                }
            }
        }
        // 시간의 벽 단면도 . 순서 > 동 서 남 북 윗면
        timeMap = new int[5][M][M];
        for (int loc = 0; loc < 5; loc++) {
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    timeMap[loc][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        // 타임머신 위치
        int loc = 4, sy = 0, sx = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (timeMap[loc][i][j] == 2) {
                    sy = i;
                    sx = j;
                    timeMap[loc][i][j] = 0;
                }
            }
        }

        // 시간 이상 현상
        timeAbnormalList = new Abnormal[F];
        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            timeAbnormalList[i] = new Abnormal(r, c, d, v);

            // 현재 map에 이상현상 적용
            map[r][c] = -1;
        }

        int answer = findWay(sy, sx);

        System.out.println(answer);
    }

    private static int findWay(int sy, int sx) {
        boolean[][] visitMap = new boolean[N][N];
        boolean[][][] visitTimeMap = new boolean[5][M][M];

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(sy, sx, 4, 0));

        // time이 늘어나면 이상현상 확산 필요함.
        PriorityQueue<Abnormal> abnormalPriorityQueue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        for (Abnormal abnormal : timeAbnormalList) {
            // 다음 이상현상 확산 시간 저장
            abnormalPriorityQueue.offer(new Abnormal(abnormal.y, abnormal.x, abnormal.dir, abnormal.v, abnormal.v));
        }
        int prevTime = 0;

        // 시간 평면 벗어나기
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // time이 변하면 이상현상 확산
            if (prevTime == cur.time) {
                moveAbnormal(abnormalPriorityQueue, cur.time + 1);
                prevTime = cur.time + 1;
            }

            // 시간 평면 탈출
            if (cur.loc == -1) {
                queue.clear();
                queue.offer(new State(cur.y, cur.x, cur.time));
                break;
            }

            // 방문 확인
            if (visitTimeMap[cur.loc][cur.y][cur.x]) continue;
            visitTimeMap[cur.loc][cur.y][cur.x] = true;

            // 이동
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                // 범위 안에서 이동하면 그대로
                if (ny >= 0 && nx >= 0 && ny < M && nx < M) {
                    if (visitTimeMap[cur.loc][ny][nx] || timeMap[cur.loc][ny][nx] == 1)
                        continue;

                    queue.offer(new State(ny, nx, cur.loc, cur.time + 1));
                }
                // 범위 밖으로 나가면 다른 면으로 이동한것 > 이동한 면을 구해서 새로 위치를 설정
                else {
                    State nextPos = nextPos(cur, d);
                    // 공간 평면으로
                    if (nextPos.loc == -1) {
                        if (nextPos.y < 0 || nextPos.x < 0 || nextPos.y >= N || nextPos.x >= N || map[nextPos.y][nextPos.x] == 1)
                            continue;
                        queue.offer(new State(nextPos.y, nextPos.x, nextPos.loc, cur.time + 1));
                    }
                    // 아직 시간 평면
                    else {
                        if (visitTimeMap[nextPos.loc][nextPos.y][nextPos.x] || timeMap[nextPos.loc][nextPos.y][nextPos.x] == 1)
                            continue;

                        queue.offer(new State(nextPos.y, nextPos.x, nextPos.loc, cur.time + 1));
                    }
                }
            }
        }

        // 공간 평면 벗어나기
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // time이 변하면 이상현상 확산
            if (prevTime == cur.time) {
                moveAbnormal(abnormalPriorityQueue, cur.time + 1);
                prevTime = cur.time + 1;
            }

            // 출구?
            if (map[cur.y][cur.x] == 4) {
                return cur.time;
            }

            // 방문
            if (visitMap[cur.y][cur.x]) continue;
            visitMap[cur.y][cur.x] = true;

            // 이동
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visitMap[ny][nx] || map[ny][nx] == 1 || map[ny][nx] == -1 || map[ny][nx] == 3)
                    continue;

                queue.offer(new State(ny, nx, cur.time + 1));
            }
        }

        return -1;
    }

    // 다른 면으로 이동한 위치 구하기
    private static State nextPos(State cur, int d) {
        State next = new State();
        // 오
        if (d == 0) {
            if (cur.loc == 4) {
                next.loc = d;
                next.y = 0;
                next.x = M - 1 - cur.y;
            } else if (cur.loc == 0) {
                next.loc = 3;
                next.y = cur.y;
                next.x = 0;
            } else if (cur.loc == 1) {
                next.loc = 2;
                next.y = cur.y;
                next.x = 0;
            } else if (cur.loc == 2) {
                next.loc = 0;
                next.y = cur.y;
                next.x = 0;
            } else if (cur.loc == 3) {
                next.loc = 1;
                next.y = cur.y;
                next.x = 0;
            }
        }
        // 왼
        else if (d == 1) {
            if (cur.loc == 4) {
                next.loc = d;
                next.y = 0;
                next.x = cur.y;
            } else if (cur.loc == 0) {
                next.loc = 2;
                next.y = cur.y;
                next.x = M - 1;
            } else if (cur.loc == 1) {
                next.loc = 3;
                next.y = cur.y;
                next.x = M - 1;
            } else if (cur.loc == 2) {
                next.loc = 1;
                next.y = cur.y;
                next.x = M - 1;
            } else if (cur.loc == 3) {
                next.loc = 0;
                next.y = cur.y;
                next.x = M - 1;
            }
        }
        // 아래
        else if (d == 2) {
            if (cur.loc == 4) {
                next.loc = d;
                next.y = 0;
                next.x = cur.x;
            } else if (cur.loc == 0) {
                next.loc = -1;
                next.y = timeMapSY + (M - 1 - cur.x);
                next.x = timeMapSX + M;
            } else if (cur.loc == 1) {
                next.loc = -1;
                next.y = timeMapSY + cur.x;
                next.x = timeMapSX - 1;
            } else if (cur.loc == 2) {
                next.loc = -1;
                next.y = timeMapSY + M;
                next.x = timeMapSX + cur.x;
            } else if (cur.loc == 3) {
                next.loc = -1;
                next.y = timeMapSY - 1;
                next.x = timeMapSX + (M - 1 - cur.x);
            }
        }
        // 위
        else if (d == 3) {
            if (cur.loc == 4) {
                next.loc = d;
                next.y = 0;
                next.x = M - 1 - cur.x;
            } else if (cur.loc == 0) {
                next.loc = 4;
                next.y = M - 1 - cur.x;
                next.x = M - 1;
            } else if (cur.loc == 1) {
                next.loc = 4;
                next.y = cur.x;
                next.x = 0;
            } else if (cur.loc == 2) {
                next.loc = 4;
                next.y = M - 1;
                next.x = cur.x;
            } else if (cur.loc == 3) {
                next.loc = 4;
                next.y = 0;
                next.x = M - 1 - cur.x;
            }
        }

        return next;
    }

    private static void moveAbnormal(PriorityQueue<Abnormal> pq, int time) {
        while (!pq.isEmpty() && pq.peek().time <= time) {
            Abnormal abnormal = pq.poll();
            // 다음 확산 지점
            int ny = abnormal.y + dy[abnormal.dir];
            int nx = abnormal.x + dx[abnormal.dir];
            // 확산 가능한 경우
            if (ny >= 0 && nx >= 0 && ny < N && nx < N && (map[ny][nx] == 0 || map[ny][nx] == -1)) {
                map[ny][nx] = -1;
                pq.offer(new Abnormal(ny, nx, abnormal.dir, abnormal.v, abnormal.time + abnormal.v));
            }
        }
    }

    private static class State {
        int y, x, loc, time;

        public State() {
        }

        public State(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        public State(int y, int x, int loc, int time) {
            this.y = y;
            this.x = x;
            this.loc = loc;
            this.time = time;
        }
    }

    private static class Abnormal {
        int y, x, dir, v, time;

        public Abnormal(int y, int x, int dir, int v) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.v = v;
        }

        public Abnormal(int y, int x, int dir, int v, int time) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.v = v;
            this.time = time;
        }
    }
}
