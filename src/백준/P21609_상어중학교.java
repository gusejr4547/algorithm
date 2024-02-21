package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P21609_상어중학교 {
    static final int EMPTY = -2;
    static int N, M, result;
    static int[][] map;
    static int[][] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(result);
    }

    public static void solution() {
        while (true) {
            visit = new int[N][N];
            PriorityQueue<Group> groupList = findGroup();
            if (groupList.isEmpty())
                return;

            Group blockGroup = groupList.poll();
            result += blockGroup.size * blockGroup.size;
            deleteGroup(blockGroup.y, blockGroup.x, map[blockGroup.y][blockGroup.x]);
            gravity();
            rotate();
            gravity();
        }
    }

    public static void deleteGroup(int y, int x, int color) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        queue.offer(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int currY = point[0];
            int currX = point[1];

            if (visit[currY][currX]) continue;
            visit[currY][currX] = true;
            map[currY][currX] = EMPTY;

            for (int d = 0; d < 4; d++) {
                int ny = currY + dy[d];
                int nx = currX + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx])
                    continue;
                if (map[ny][nx] == color || map[ny][nx] == 0) {
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
    }

    public static PriorityQueue<Group> findGroup() {
        PriorityQueue<Group> groupList = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && visit[i][j] == 0) {
                    Group group = makeGroup(i, j, map[i][j]);
                    if (group.size >= 2) {
                        groupList.offer(group);
                    }
                }
            }
        }

        return groupList;
    }

    public static Group makeGroup(int y, int x, int color) {
        int size = 0;
        int rainbowCnt = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int currY = point[0];
            int currX = point[1];

            if (visit[currY][currX] == color) continue;
            visit[currY][currX] = color;
            size++;
            if (map[currY][currX] == 0)
                rainbowCnt++;

            for (int d = 0; d < 4; d++) {
                int ny = currY + dy[d];
                int nx = currX + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                    continue;
                if (map[ny][nx] == color && visit[ny][nx] == 0) {
                    queue.offer(new int[]{ny, nx});
                } else if (map[ny][nx] == 0 && visit[ny][nx] != color) {
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

        return new Group(y, x, size, rainbowCnt);
    }

    public static void gravity() {
        for (int x = 0; x < N; x++) {
            int cnt = 0;
            for (int y = N - 1; y >= 0; y--) {
                if (map[y][x] == EMPTY) {
                    cnt++;
                } else {
                    if (map[y][x] != -1 && cnt > 0) {
                        map[y + cnt][x] = map[y][x];
                        map[y][x] = EMPTY;
                        y = y + cnt;
                        cnt = 0;
                    } else {
                        cnt = 0;
                    }
                }
            }
        }
    }

    public static void rotate() {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[N - j - 1][i] = map[i][j];
            }
        }
        map = newMap;
    }

    public static class Group implements Comparable<Group> {
        int y;
        int x;
        int size;
        int rainbowCnt;

        public Group(int y, int x, int size, int rainbowCnt) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.rainbowCnt = rainbowCnt;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "y=" + y +
                    ", x=" + x +
                    ", size=" + size +
                    ", rainbowCnt=" + rainbowCnt +
                    '}';
        }

        @Override
        public int compareTo(Group o) {
            if (this.size == o.size) {
                if (this.rainbowCnt == o.rainbowCnt) {
                    if (this.y == o.y) {
                        return o.x - this.x;
                    }
                    return o.y - this.y;
                }
                return o.rainbowCnt - this.rainbowCnt;
            }
            return o.size - this.size;
        }
    }
}
