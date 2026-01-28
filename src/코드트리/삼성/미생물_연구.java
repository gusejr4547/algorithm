package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 미생물_연구 {
    static int N, Q;
    static int[][] grid;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        Q = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= Q; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            // 1. 미생물 투입
            put(y1, x1, y2, x2, t);

            // 2. 배양 용기 이동
            grid = moveAll();

            // 3. 실험 결과 기록
            int result = getResult();
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

    private static int getResult() {
        // 각 그룹의 크기
        Map<Integer, Integer> groupSize = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                groupSize.put(grid[i][j], groupSize.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        // 그룹끼리 인접한 쌍
        boolean[][] visit = new boolean[N][N];
        List<Pair> adj = new ArrayList<>(); // (y, x) 그룹 번호 인접
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] || grid[i][j] == 0) {
                    continue;
                }
                getAdj(i, j, grid[i][j], visit, adj);
            }
        }

        int result = 0;
        for (Pair p : adj) {
            result += groupSize.get(p.y) * groupSize.get(p.x);
        }

        return result;
    }

    private static void getAdj(int i, int j, int groupNum, boolean[][] visit, List<Pair> adj) {
        Set<Integer> adjGroup = new HashSet<>();
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        visit[i][j] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) {
                    continue;
                }
                if (grid[ny][nx] == groupNum) {
                    visit[ny][nx] = true;
                    queue.offer(new Pair(ny, nx));
                } else if (grid[ny][nx] != 0) {
                    adjGroup.add(grid[ny][nx]);
                }
            }
        }

        for (int n : adjGroup) {
            adj.add(new Pair(groupNum, n));
        }
    }

    private static int[][] moveAll() {
        int[][] newGrid = new int[N][N];
        // 그룹별로 좌표 나누기
        Map<Integer, List<Pair>> groupMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (groupMap.containsKey(grid[i][j])) {
                    groupMap.get(grid[i][j]).add(new Pair(i, j));
                } else {
                    List<Pair> list = new ArrayList<>();
                    list.add(new Pair(i, j));
                    groupMap.put(grid[i][j], list);
                }
            }
        }
        // 순서
        // 무리 큰거 먼저, 가장 먼저 투입된 미생물 먼저
        PriorityQueue<List<Pair>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.size() == o2.size()) {
                return Integer.compare(grid[o1.get(0).y][o1.get(0).x], grid[o2.get(0).y][o2.get(0).x]);
            }
            return Integer.compare(o2.size(), o1.size());
        });
        for (List<Pair> l : groupMap.values()) {
            pq.offer(l);
        }

        // newGrid에 이동
        // x좌표 작은위치, y좌표 작은 위치 이동
        // 어떤 방법으로도 못놓으면 삭제
        while (!pq.isEmpty()) {
            List<Pair> group = pq.poll();
            // newGrid에서 빈곳 찾기
            boolean end = false;
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    if (newGrid[i][j] == 0) {
                        // (i,j) 기준으로 미생물 놓아보기
                        if (canMove(i, j, group, newGrid)) {
                            move(i, j, group, newGrid);
                            end = true;
                            break;
                        }
                    }
                }
                if (end) {
                    break;
                }
            }
        }

        return newGrid;
    }

    private static void move(int i, int j, List<Pair> group, int[][] newGrid) {
        // i, j 가장 x,y 작은값으로 두고 전부 옮겨지는가?
        int offsetY = i - group.get(0).y;
        int offsetX = j - group.get(0).x;
        int groupNum = grid[group.get(0).y][group.get(0).x];
        for (Pair p : group) {
            int ny = p.y + offsetY;
            int nx = p.x + offsetX;
            newGrid[ny][nx] = groupNum;
        }
    }

    private static boolean canMove(int i, int j, List<Pair> group, int[][] newGrid) {
        // i, j 가장 x,y 작은값으로 두고 전부 옮겨지는가?
        int offsetY = i - group.get(0).y;
        int offsetX = j - group.get(0).x;

        for (Pair p : group) {
            int ny = p.y + offsetY;
            int nx = p.x + offsetX;
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || newGrid[ny][nx] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void put(int r1, int c1, int r2, int c2, int num) {
        // (r1,c1) (r2,c2) 사각형 num으로 채우기
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                grid[i][j] = num;
            }
        }

        // 미생물의 영역이 2개 이상으로 나누어 진거 확인, 삭제
        Set<Integer> delete = new HashSet<>();
        boolean[][] visit = new boolean[N][N];
        boolean[] group = new boolean[Q + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] || grid[i][j] == 0) {
                    continue;
                }
                int groupNum = grid[i][j];
                // 이미 탐색한 미생물 번호 또 나오면 => 2개 이상의 그룹으로 나누어짐
                if (group[groupNum]) {
                    delete.add(groupNum);
                }

                bfs(i, j, groupNum, visit);
                group[groupNum] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (delete.contains(grid[i][j])) {
                    grid[i][j] = 0;
                }
            }
        }
    }

    private static void bfs(int i, int j, int groupNum, boolean[][] visit) {
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        visit[i][j] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) {
                    continue;
                }
                if (grid[ny][nx] == groupNum) {
                    visit[ny][nx] = true;
                    queue.offer(new Pair(ny, nx));
                }
            }
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
