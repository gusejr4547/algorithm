package 코드트리.기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
NxN 크기의 마을. 도로 0, 도로x 1
집 -> 공원. 도로를 따라 최단경로
전사 M명. (r,c)에 위치하고 전사 -> 메두사. 어느칸이든 최단경로 이동.
메두사는 전사가 움직이기 전에 바라보면 움직임 멈추기 가능.

움직이는 순서 -
1. 메두사 이동 - 이동위치에 전사 있으면 전사 사라짐
2. 메두사 시선 - 가장 전사를 많이 바라보는 방향으로, 상하좌우 우선순위
3. 전사 이동 - 최대 2칸 이동. 전사끼리 칸 공유 가능.
4. 전사 공격 - 메두사와 같은 칸이면 공격하고 전사는 사라짐.

이동의 우선순위 - 상하좌우
돌이된 전사는 턴이 끝나면 풀림

턴마다 - 모든전사 이동거리 합, 돌이된 전사수, 메두사를 공격한 전사 수
 */
public class 메두사와_전사들 {
    static int N, M;
    static Point house, park;
    static Point[] warriors;
    static int[][] map, warriorsMap;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 메두사 집, 공원 위치
        st = new StringTokenizer(br.readLine());
        house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        park = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 전사 좌표
        st = new StringTokenizer(br.readLine());
        warriors = new Point[M];
        for (int i = 0; i < M; i++) {
            warriors[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 마을 도로 정보
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 집 -> 공원의 실제 최단경로를 구한다.
        List<Point> path = getShortestPath(house, park);

        // 최단경로가 없으면 -1 출력
        if (path == null) {
            System.out.println(-1);
        }
        // 최단경로를 바탕으로 1턴씩 진행
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < path.size(); i++) {
                warriorsMap = updateWarriorsMap();
                sb.append(turn(path.get(i)));
            }
            System.out.println(sb);
        }
    }

    private static String turn(Point medusaPoint) {
        int warriorMoveCnt = 0;
        int rockWarriorCnt = 0;
        int attackCnt = 0;

        // 메두사 이동 마지막?
        if (medusaPoint.y == park.y && medusaPoint.x == park.x) {
            return "0";
        }

        // 현재 위치에 전사 있으면 잡음.
        for (Point warrior : warriors) {
            if (warrior.y == medusaPoint.y && warrior.x == medusaPoint.x) {
                // 전사 위치 -1,-1은 잡힌것
                warrior.y = -1;
                warrior.x = -1;
            }
        }

        // 메두사 바라보는 방향 선택 >> 이동할 수 있는 범위 map
        SightResult sightResult = watchDir(medusaPoint);
        int[][] sightMap = sightResult.sightMap;
        rockWarriorCnt = sightResult.rockWarriorCnt;

        // 전사 이동
        warriorMoveCnt = moveWarriors(medusaPoint, sightMap);

        // 전사 공격
        attackCnt = attackWarriors(medusaPoint);

        // 결과 출력
        return String.format("%d %d %d\n", warriorMoveCnt, rockWarriorCnt, attackCnt);
    }

    private static int attackWarriors(Point medusaPoint) {
        int totalAttack = 0;
        for (Point warrior : warriors) {
            // 잡힌 전사
            if (warrior.y == -1) {
                continue;
            }

            // 메두사와 같은 위치에 도달한 전사
            if (warrior.y == medusaPoint.y && warrior.x == medusaPoint.x) {
                totalAttack++;
                warrior.y = -1;
                warrior.x = -1;
            }
        }

        return totalAttack;
    }

    private static int moveWarriors(Point medusaPoint, int[][] sightMap) {
        int totalMove = 0;
        for (Point warrior : warriors) {
            // 잡힌 전사
            if (warrior.y == -1) {
                continue;
            }

            int wY = warrior.y;
            int wX = warrior.x;
            // 돌이 된 전사
            if (sightMap[wY][wX] == 1) {
                continue;
            }

            // 1번 이동 >> 상하좌우
            int curDist = manhattanDistance(medusaPoint, new Point(wY, wX));
            boolean isMove = false;
            for (int d = 0; d < 4; d++) {
                int ny = wY + dy[d];
                int nx = wX + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || sightMap[ny][nx] == 1) {
                    continue;
                }

                int nextDist = manhattanDistance(medusaPoint, new Point(ny, nx));
                // 거리가 줄어드는 쪽으로 이동
                if (nextDist < curDist) {
                    wY = ny;
                    wX = nx;
                    curDist = nextDist;
                    totalMove++;
                    isMove = true;
                    break;
                }
            }

            // 2번 이동 >> 좌우상하
            if (isMove) {
                for (int d = 0; d < 4; d++) {
                    int dir = (d + 2) % 4;
                    int ny = wY + dy[dir];
                    int nx = wX + dx[dir];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || sightMap[ny][nx] == 1) {
                        continue;
                    }

                    int nextDist = manhattanDistance(medusaPoint, new Point(ny, nx));
                    // 거리가 줄어드는 쪽
                    if (nextDist < curDist) {
                        wY = ny;
                        wX = nx;
                        totalMove++;
                        break;
                    }
                }
            }

            // 위치 수정
            warrior.y = wY;
            warrior.x = wX;
        }

        return totalMove;
    }

    private static SightResult watchDir(Point medusaPoint) {
        int[][][] sightMap = new int[4][N][N];

        int maxDir = -1; // 최대 방향
        int maxCnt = -1; // 최대 수

        // 4방향 전부 시도
        for (int dir = 0; dir < 4; dir++) {
            int rockWarriorCnt = 0;
            if (dir == 0) {
                rockWarriorCnt = sightUp(medusaPoint.y, medusaPoint.x, sightMap[dir]);
            } else if (dir == 1) {
                rockWarriorCnt = sightDown(medusaPoint.y, medusaPoint.x, sightMap[dir]);
            } else if (dir == 2) {
                rockWarriorCnt = sightLeft(medusaPoint.y, medusaPoint.x, sightMap[dir]);
            } else if (dir == 3) {
                rockWarriorCnt = sightRight(medusaPoint.y, medusaPoint.x, sightMap[dir]);
            }

            if (maxCnt < rockWarriorCnt) {
                maxCnt = rockWarriorCnt;
                maxDir = dir;
            }
        }

        return new SightResult(sightMap[maxDir], maxCnt);
    }

    private static int sightUp(int y, int x, int[][] sightMap) {
        // 시야 범위 처리
        for (int i = y - 1; i >= 0; i--) {
            int s = Math.max(0, x - (y - i));
            int e = Math.min(N - 1, x + (y - i));
            for (int j = s; j <= e; j++) {
                sightMap[i][j] = 1; // 시야 들어옴
            }
        }

        // 직선 방향 확인
        boolean isWarrior = false;
        for (int i = y - 1; i >= 0; i--) {
            // 앞에 전사가 돌이 되면 뒤에는 안보임
            if (isWarrior) {
                sightMap[i][x] = 0;
            }

            // 해당 장소에 전사 있으면
            if (warriorsMap[i][x] > 0) {
                isWarrior = true;
            }
        }

        // 대각선 방향 확인
        for (int i = y - 1; i >= 1; i--) {
            int s = Math.max(0, x - (y - i));
            int e = Math.min(N - 1, x + (y - i));
            // 좌
            for (int j = s; j < x; j++) {
                // 전사 있는 경우 or 시야 안보이는 경우 >> 뒤칸 안보이게 처리
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i - 1][j] = 0;
                    if (j > 0) {
                        sightMap[i - 1][j - 1] = 0;
                    }
                }
            }
            // 우
            for (int j = x + 1; j <= e; j++) {
                // 전사 있는 경우 or 시야 안보이는 경우 >> 뒤칸 안보이게 처리
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i - 1][j] = 0;
                    if (j < N - 1) {
                        sightMap[i - 1][j + 1] = 0;
                    }
                }
            }
        }

        // 돌이된 전사 수 계산
        int rockWarriorCnt = 0;
        for (int i = y - 1; i >= 0; i--) {
            int s = Math.max(0, x - (y - i));
            int e = Math.min(N - 1, x + (y - i));
            for (int j = s; j <= e; j++) {
                // 시야 범위에 들어오는 곳 전사 계산
                if (sightMap[i][j] == 1) {
                    rockWarriorCnt += warriorsMap[i][j];
                }
            }
        }

        return rockWarriorCnt;
    }

    private static int sightDown(int y, int x, int[][] sightMap) {
        // 시야 범위 처리
        for (int i = y + 1; i <= N - 1; i++) {
            int s = Math.max(0, x - (i - y));
            int e = Math.min(N - 1, x + (i - y));
            for (int j = s; j <= e; j++) {
                sightMap[i][j] = 1; // 시야 들어옴
            }
        }
        // 직선 방향 확인
        boolean isWarrior = false;
        for (int i = y + 1; i <= N - 1; i++) {
            // 앞에 전사가 돌이 되면 뒤에는 안보임
            if (isWarrior) {
                sightMap[i][x] = 0;
            }

            // 해당 장소에 전사 있으면
            if (warriorsMap[i][x] > 0) {
                isWarrior = true;
            }
        }
        // 대각선 방향 확인
        for (int i = y + 1; i < N - 1; i++) {
            int s = Math.max(0, x - (i - y));
            int e = Math.min(N - 1, x + (i - y));
            // 좌
            for (int j = s; j < x; j++) {
                // 전사 있는 경우 or 시야 안보이는 경우 >> 뒤칸 안보이게 처리
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i + 1][j] = 0;
                    if (j > 0) {
                        sightMap[i + 1][j - 1] = 0;
                    }
                }
            }
            // 우
            for (int j = x + 1; j <= e; j++) {
                // 전사 있는 경우 or 시야 안보이는 경우 >> 뒤칸 안보이게 처리
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i + 1][j] = 0;
                    if (j < N - 1) {
                        sightMap[i + 1][j + 1] = 0;
                    }
                }
            }
        }

        // 돌이된 전사 수 계산
        int rockWarriorCnt = 0;
        for (int i = y + 1; i <= N - 1; i++) {
            int s = Math.max(0, x - (i - y));
            int e = Math.min(N - 1, x + (i - y));
            for (int j = s; j <= e; j++) {
                // 시야 범위에 들어오는 곳 전사 계산
                if (sightMap[i][j] == 1) {
                    rockWarriorCnt += warriorsMap[i][j];
                }
            }
        }

        return rockWarriorCnt;
    }

    private static int sightLeft(int y, int x, int[][] sightMap) {
        for (int j = x - 1; j >= 0; j--) {
            int s = Math.max(0, y - (x - j));
            int e = Math.min(N - 1, y + (x - j));
            for (int i = s; i <= e; i++) {
                sightMap[i][j] = 1;
            }
        }

        boolean isWarrior = false;
        for (int j = x - 1; j >= 0; j--) {
            if (isWarrior) {
                sightMap[y][j] = 0;
            }
            if (warriorsMap[y][j] > 0) {
                isWarrior = true;
            }
        }

        for (int j = x - 1; j >= 1; j--) {
            int s = Math.max(0, y - (x - j));
            int e = Math.min(N - 1, y + (x - j));
            for (int i = s; i <= y - 1; i++) {
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i][j - 1] = 0;
                    if (i > 0) {
                        sightMap[i - 1][j - 1] = 0;
                    }
                }
            }

            for (int i = y + 1; i <= e; i++) {
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i][j - 1] = 0;
                    if (i < N - 1) {
                        sightMap[i + 1][j - 1] = 0;
                    }
                }
            }
        }

        int rockWarriorCnt = 0;
        for (int j = x - 1; j >= 0; j--) {
            int s = Math.max(0, y - (x - j));
            int e = Math.min(N - 1, y + (x - j));
            for (int i = s; i <= e; i++) {
                if (sightMap[i][j] == 1) {
                    rockWarriorCnt += warriorsMap[i][j];
                }
            }
        }

        return rockWarriorCnt;
    }

    private static int sightRight(int y, int x, int[][] sightMap) {
        for (int j = x + 1; j < N; j++) {
            int s = Math.max(0, y - (j - x));
            int e = Math.min(N - 1, y + (j - x));
            for (int i = s; i <= e; i++) {
                sightMap[i][j] = 1;
            }
        }

        boolean isWarrior = false;
        for (int j = x + 1; j <= N - 1; j++) {
            if (isWarrior) {
                sightMap[y][j] = 0;
            }
            if (warriorsMap[y][j] > 0) {
                isWarrior = true;
            }
        }

        for (int j = x + 1; j < N - 1; j++) {
            int s = Math.max(0, y - (j - x));
            int e = Math.min(N - 1, y + (j - x));
            for (int i = s; i <= y - 1; i++) {
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i][j + 1] = 0;
                    if (i > 0) {
                        sightMap[i - 1][j + 1] = 0;
                    }
                }
            }

            for (int i = y + 1; i <= e; i++) {
                if (warriorsMap[i][j] > 0 || sightMap[i][j] == 0) {
                    sightMap[i][j + 1] = 0;
                    if (i < N - 1) {
                        sightMap[i + 1][j + 1] = 0;
                    }
                }
            }
        }

        int rockWarriorCnt = 0;
        for (int j = x + 1; j < N; j++) {
            int s = Math.max(0, y - (j - x));
            int e = Math.min(N - 1, y + (j - x));
            for (int i = s; i <= e; i++) {
                if (sightMap[i][j] == 1) {
                    rockWarriorCnt += warriorsMap[i][j];
                }
            }
        }

        return rockWarriorCnt;
    }

    private static int[][] updateWarriorsMap() {
        int[][] warriorsMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(warriorsMap[i], 0);
        }

        for (Point warrior : warriors) {
            // 죽음
            if (warrior.y == -1) continue;

            warriorsMap[warrior.y][warrior.x]++;
        }

        return warriorsMap;
    }

    private static List<Point> getShortestPath(Point start, Point end) {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        Point[][] visit = new Point[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit[i][j] = null;
            }
        }
        queue.offer(new Point(start.y, start.x));
        visit[start.y][start.x] = start;

        boolean canGo = false;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            // end
            if (cur.y == end.y && cur.x == end.x) {
                canGo = true;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1 || visit[ny][nx] != null)
                    continue;

                queue.offer(new Point(ny, nx));
                // ny,nx에 cur를 기록
                visit[ny][nx] = cur;
            }
        }

        // 길이 없으면
        if (!canGo)
            return null;

        // 경로를 end로부터 start까지 찾아서 stack에 넣기
        ArrayDeque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(end.y, end.x));
        while (true) {
            Point cur = stack.peek();
            if (cur.y == start.y && cur.x == start.x) {
                break;
            }

            stack.push(visit[cur.y][cur.x]);
        }

        List<Point> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
    }


    private static class Point {
        int y, x;

        public Point() {
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class SightResult {
        int[][] sightMap;
        int rockWarriorCnt;

        public SightResult(int[][] sightMap, int rockWarriorCnt) {
            this.sightMap = sightMap;
            this.rockWarriorCnt = rockWarriorCnt;
        }
    }
}
