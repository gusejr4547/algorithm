package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
    }

    public static void solution() {
        while (true) {
            visit = new int[N][N];
            List<Group> groupList = findGroup();
            if (groupList.isEmpty())
                return;

            int idx = 0;
            int max = 0;
            for (int i = 0; i < groupList.size(); i++) {
                if (max < groupList.get(i).size) {
                    max = groupList.get(i).size;
                    idx = i;
                }
            }

            result += max * max;
            Group blockGroup = groupList.get(idx);
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

    public static List<Group> findGroup() {
        List<Group> groupList = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && visit[i][j] == 0) {
                    int size = groupSize(i, j, map[i][j], count);
                    if (size >= 2) {
                        groupList.add(new Group(i, j, size));
                        count++;
                    }
                }
            }
        }

        return groupList;
    }

    public static int groupSize(int y, int x, int color, int count) {
        int size = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int currY = point[0];
            int currX = point[1];

            if (visit[currY][currX] == color) continue;
            visit[currY][currX] = color;
            size++;

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

        return size;
    }

    public static void gravity() {

    }

    public static void rotate() {

    }

    public static class Group {
        int y;
        int x;
        int size;

        public Group(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }
}
