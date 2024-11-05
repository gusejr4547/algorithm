package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P6087_레이저_통신 {
    static int W, H;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        List<Pos> laser = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    laser.add(new Pos(i, j));
                }
            }
        }

        int answer = bfs(laser.get(0), laser.get(1));
        System.out.println(answer);
    }

    private static int bfs(Pos start, Pos end) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.mirrorCnt, o2.mirrorCnt));
        queue.offer(new Node(start.y, start.x, -1, 9999));

        int[][][] visit = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }

        int result = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == end.y && cur.x == end.x) {
                result = Math.min(result, cur.mirrorCnt);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == '*')
                    continue;

                if (Math.abs(d - cur.dir) == 2)
                    continue;

                int nextMirrorCnt = d == cur.dir ? cur.mirrorCnt : cur.mirrorCnt + 1;

                if (visit[ny][nx][d] > nextMirrorCnt) {
                    visit[ny][nx][d] = nextMirrorCnt;
                    queue.offer(new Node(ny, nx, nextMirrorCnt, d));
                }
            }
        }

        return result;
    }

    static class Node {
        int y, x, mirrorCnt, dir;

        public Node(int y, int x, int mirrorCnt, int dir) {
            this.y = y;
            this.x = x;
            this.mirrorCnt = mirrorCnt;
            this.dir = dir;
        }
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
