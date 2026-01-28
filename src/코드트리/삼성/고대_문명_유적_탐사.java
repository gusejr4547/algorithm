package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 고대_문명_유적_탐사 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 탐사 반복 횟수
        int M = Integer.parseInt(st.nextToken()); // 벽면에 적힌 유물 조각 개수

        // 유물에 적혀 있는 숫자 5x5
        int[][] map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        ArrayDeque<Integer> leftRelic = new ArrayDeque<>(); // 유물 조각 빈곳에 채워지는 순서
        for (int i = 0; i < M; i++) {
            leftRelic.offer(Integer.parseInt(st.nextToken()));
        }


        StringBuilder sb = new StringBuilder();
        // K번 반복 탐사
        for (int k = 0; k < K; k++) {
            // 탐사 진행 => 격자 회전 시킴. 아무변화도 없는 경우는 고려하지 않음.
            // 목표는 1 유물 가치 획득 최대, 2 회전한 각도가 가장 작은거, 3 회전 중심 좌표의 열이 가장 작은거, 4 행이 작은거
            int bestRotateCnt = 3;
            int maxRelicPoint = -1;
            int[][] bestMap = null;

            for (int j = 1; j <= 3; j++) {
                for (int i = 1; i <= 3; i++) {
                    int[][] rotateMap = map;
                    for (int rotateCnt = 0; rotateCnt < 3; rotateCnt++) {
                        rotateMap = rotate90(i, j, rotateMap);
                        // 현재 상태일 때 유물 가치 계산
                        int cost = calRelicCost(rotateMap);
                        // 갱신
                        if (cost > maxRelicPoint) {
                            bestMap = rotateMap;
                            maxRelicPoint = cost;
                            bestRotateCnt = rotateCnt;
                        } else if (cost == maxRelicPoint && rotateCnt < bestRotateCnt) {
                            bestMap = rotateMap;
                            bestRotateCnt = rotateCnt;
                        }
                    }
                }
            }

            // 획득할 수 있는 유물이 없다.
            if(maxRelicPoint == 0){
                break;
            }

            // 유물 획득 >> 같은 번호의 조각이 3개 이상 연결되면 획득 가능.
            map = bestMap;
            // 유물 지우기
            deleteRelics(map);

            // 빈곳에 채우기
            while (fillEmptySpace(leftRelic, map)) {
                // 채웠는데 또 획득할 수 있는 유물이 있다면 없을때 까지 계속 반복
                int cost = calRelicCost(map);
                if (cost > 0) {
                    maxRelicPoint += cost;
                    deleteRelics(map);
                }
            }

            sb.append(maxRelicPoint).append(" ");
        }

        System.out.println(sb);
    }

    // leftRelic이 비는 경우는 없다고 가정
    private static boolean fillEmptySpace(ArrayDeque<Integer> leftRelic, int[][] map) {
        // 열이 작은 순서로
        // 행이 큰 순서로
        int count = 0;
        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                if (map[i][j] == 0) {
                    map[i][j] = leftRelic.poll();
                    count++;
                }
            }
        }

        return count > 0;
    }

    private static void deleteRelics(int[][] map) {
        boolean[][] visit = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visit[i][j]) continue;
                deleteRelicBlock(i, j, map, visit);
            }
        }
    }

    private static void deleteRelicBlock(int startY, int startX, int[][] map, boolean[][] visit) {
        List<Pair> deleteList = new ArrayList<>();
        int target = map[startY][startX];
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(startY, startX));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;
            deleteList.add(new Pair(cur.y, cur.x));

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5 || visit[ny][nx] || map[ny][nx] != target)
                    continue;

                queue.offer(new Pair(ny, nx));
            }
        }

        // 3이상인 경우만 삭제
        if (deleteList.size() >= 3) {
            for (Pair point : deleteList) {
                map[point.y][point.x] = 0;
            }
        }
    }

    private static int calRelicCost(int[][] map) {
        int totalCost = 0;
        boolean[][] visit = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visit[i][j]) continue;
                int cost = bfsSearch(i, j, visit, map);
                if (cost >= 3) {
                    totalCost += cost;
                }
            }
        }
        return totalCost;
    }

    private static int bfsSearch(int startY, int startX, boolean[][] visit, int[][] map) {
        int count = 0;
        int target = map[startY][startX];
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(startY, startX));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;
            count++;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5 || visit[ny][nx] || map[ny][nx] != target)
                    continue;

                queue.offer(new Pair(ny, nx));
            }
        }

        return count;
    }

    // (y,x)를 중심으로 3x3 90도 회전
    private static int[][] rotate90(int y, int x, int[][] original) {
        int[][] rotateMap = new int[5][5];
        for (int i = 0; i < 5; i++) {
            rotateMap[i] = original[i].clone();
        }

        int N = 3;
        // 왼쪽 위 좌표
        y = y - 1;
        x = x - 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotateMap[i + y][j + x] = original[N - 1 - j + y][i + x];
            }
        }

        return rotateMap;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
