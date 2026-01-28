package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 골렘은 중심을 기준으로 4방향 1칸씩 포함해서, 총 5칸을 차지한다.
// 출구 방향 정보 0,1,2,3 -> 북,동,남,서
public class 마법의_숲_탐색 {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] forest = new int[R + 3][C];
        // 골렘은 무조건 맨 위에서 출발한다.
        // 골렘이 출발하는 열, 골렘의 출구 방향
        List<Golem> golemList = new ArrayList<>();
        Pair[] outPointArr = new Pair[K + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            golemList.add(new Golem(col, dir));
            outPointArr[i + 1] = new Pair(1 + dy[dir], col + dx[dir]);
        }

        // 움직이는 방법
        // 1.남쪽으로 내려가기
        // 2.1번 불가능하면, 서쪽방향으로 회전하면서 내려가기, 서쪽이동 -> -90회전 -> 남쪽이동
        // 3.2번 불가능하면, 동쪽방향으로 회전하면서 내려가기, 동쪽이동 -> 90회전 -> 남쪽이동
        // 골렘이 최대한 남쪽에 도달해 이동할 수 없으면, 정령은 골렘에서 이동이 가능하다.
        // 골렘의 출구가 다른 골렘과 인접하면 다른 골렘으로 이동이 가능하다.
        // 정령은 갈수있는 최대한 남쪽으로 이동한 뒤 이동을 종료한다.
        // 만약에 골렘이 최대한 남쪽으로 이동했는데 숲을 벗어난 상태이면 숲을 초기화하고 새롭게 시작함. => 이때는 답에 포함되지 않음.
        int golemId = 1;
        int answer = 0;
        for (int i = 0; i < K; i++, golemId++) {
            int result = searchForest(forest, golemList.get(i), golemId, outPointArr);
//            System.out.println(result);
            answer += result;
        }


        // 정령 최종 위치의 행번호의 합을 구하시오.
        System.out.println(answer);
    }

    private static int searchForest(int[][] forest, Golem golem, int golemId, Pair[] outPointDir) {
        while (moveGolem(forest, golem, golemId, outPointDir)) {

        }
        // 골렘 최종위치가 숲 범위를 벗어나는 경우
        if (!isValidPosition(golem)) {
            for (int i = 0; i < forest.length; i++) {
                Arrays.fill(forest[i], 0);
            }
            return 0;
        }

        // 자리 확정 => forest에 id값으로 기록
        forest[golem.row][golem.col] = golemId;
        for (int d = 0; d < 4; d++) {
            forest[golem.row + dy[d]][golem.col + dx[d]] = golemId;
        }

//        System.out.println("==============================");
//        for (int[] d : forest) {
//            System.out.println(Arrays.toString(d));
//        }
//        System.out.println("출구 : " + "(" + outPointDir[golemId].y + "," + outPointDir[golemId].x + ")");


        // 요정 움직이기
        int lastRow = moveFairy(forest, golem, outPointDir);

        return lastRow;
    }

    private static int moveFairy(int[][] forest, Golem golem, Pair[] outPointDir) {
        int maxRow = golem.row;
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[forest.length][forest[0].length];
        queue.offer(new Pair(golem.row, golem.col));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int curId = forest[cur.y][cur.x];

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;

            // 가장 아래로 내려갔을 때의 col 갱신
            if (maxRow < cur.y) {
                maxRow = cur.y;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                // 현재 위치가 출구인 경우 => 다른 골렘으로 이동 가능
                if (outPointDir[curId].y == cur.y && outPointDir[curId].x == cur.x) {
                    if (ny < 0 || nx < 0 || ny >= forest.length || nx >= forest[0].length || forest[ny][nx] == 0 || visit[ny][nx])
                        continue;
                    queue.offer(new Pair(ny, nx));
                } else {
                    if (ny < 0 || nx < 0 || ny >= forest.length || nx >= forest[0].length || forest[ny][nx] != curId || visit[ny][nx])
                        continue;
                    queue.offer(new Pair(ny, nx));
                }
            }
        }

        return maxRow - 2;
    }

    private static boolean isValidPosition(Golem golem) {
        // row 0 ~ 2에 골램이 걸치는 경우 => false
        return golem.row > 3;
    }

    private static boolean moveGolem(int[][] forest, Golem golem, int golemId, Pair[] outPointDir) {
        if (moveDown(forest, golem, golemId, outPointDir)) return true;
        if (moveLeftDown(forest, golem, golemId, outPointDir)) return true;
        return moveRightDown(forest, golem, golemId, outPointDir);
    }

    private static boolean moveRightDown(int[][] forest, Golem golem, int golemId, Pair[] outPointDir) {
        Golem next = new Golem(golem.row, golem.col, golem.outDir);
        // 오른쪽 한칸 이동
        int nextY = golem.row;
        int nextX = golem.col + 1;

        if (nextX >= forest[0].length - 1) {
            return false;
        }
        if (forest[nextY][nextX + 1] != 0 || forest[nextY - 1][nextX] != 0 || forest[nextY + 1][nextX] != 0) {
            return false;
        }
        next.col += 1;

        // 90 회전
        next.outDir = (next.outDir + 1) % 4;

        // 아래쪽 한칸 이동
        if (moveDown(forest, next, golemId, outPointDir)) {
            golem.col = next.col;
            golem.row = next.row;
            golem.outDir = next.outDir;
            outPointDir[golemId].y = golem.row + dy[golem.outDir];
            outPointDir[golemId].x = golem.col + dx[golem.outDir];
            return true;
        } else {
            return false;
        }
    }

    private static boolean moveLeftDown(int[][] forest, Golem golem, int golemId, Pair[] outPointDir) {
        Golem next = new Golem(golem.row, golem.col, golem.outDir);
        // 왼쪽 한칸 이동
        int nextY = golem.row;
        int nextX = golem.col - 1;

        if (nextX < 1) {
            return false;
        }
        if (forest[nextY][nextX - 1] != 0 || forest[nextY - 1][nextX] != 0 || forest[nextY + 1][nextX] != 0) {
            return false;
        }
        next.col -= 1;

        // -90 회전
        next.outDir = next.outDir == 0 ? 3 : next.outDir - 1;

        // 아래쪽 한칸 이동
        if (moveDown(forest, next, golemId, outPointDir)) {
            golem.col = next.col;
            golem.row = next.row;
            golem.outDir = next.outDir;
            outPointDir[golemId].y = golem.row + dy[golem.outDir];
            outPointDir[golemId].x = golem.col + dx[golem.outDir];
            return true;
        } else {
            return false;
        }
    }

    private static boolean moveDown(int[][] forest, Golem golem, int golemId, Pair[] outPointDir) {
        // 아래쪽으로 이동
        int nextY = golem.row + 1;
        int nextX = golem.col;

        // 다음 골렘 위치가 forest를 벗어나는가
        if (nextY >= forest.length - 1) {
            return false;
        }
        // 움직인 위치에 다른 골렘이 존재하는가 => 좌, 우, 아래만 확인하면 됨.
        if (forest[nextY + 1][nextX] != 0 || forest[nextY][nextX - 1] != 0 || forest[nextY][nextX + 1] != 0) {
            return false;
        }

        // 이동 성공
        golem.row += 1;
        outPointDir[golemId].y += 1;
        return true;
    }

    private static class Golem {
        int row, col, outDir;

        public Golem(int row, int col, int outDir) {
            this.row = row;
            this.col = col;
            this.outDir = outDir;
        }

        public Golem(int col, int outDir) {
            this.row = 1;
            this.col = col;
            this.outDir = outDir;
        }
    }

    private static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
