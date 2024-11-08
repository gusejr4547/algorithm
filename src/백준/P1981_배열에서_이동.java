package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (0,0) -> (n-1,n-1)로 이동
public class P1981_배열에서_이동 {
    static int N;
    static int[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int answer = 201;
        int s = 0;
        int e = max - min;
        while (s <= e) {
            int mid = (s + e) / 2;

            boolean valid = false;
            for (int minV = min; minV <= max - mid; minV++) {
                if (bfs(minV, minV + mid)) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                answer = Math.min(answer, mid);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs(int min, int max) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        if (map[0][0] < min || map[0][0] > max)
            return false;
        queue.offer(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visit[cur.y][cur.x])
                continue;
            visit[cur.y][cur.x] = true;

            if (cur.y == N - 1 && cur.x == N - 1) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                    continue;

                if (map[ny][nx] < min || map[ny][nx] > max)
                    continue;

                queue.offer(new Node(ny, nx));
            }
        }

        return false;
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
