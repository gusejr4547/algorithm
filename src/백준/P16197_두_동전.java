package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P16197_두_동전 {
    static int N, M, answer;
    static char[][] map;
    static List<Pos> coin;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static boolean[][][][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        coin = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'o') {
                    coin.add(new Pos(i, j));
                }
            }
        }

        visit = new boolean[N][M][N][M];
        answer = -1;
        bfs(coin.get(0), coin.get(1));

        System.out.println(answer);
    }

    static private void bfs(Pos coin1, Pos coin2) {
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(coin1, coin2, 0));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (cur.count == 10)
                break;

            Pos c1 = cur.coin1;
            Pos c2 = cur.coin2;

            if (visit[c1.y][c1.x][c2.y][c2.x])
                continue;
            visit[c1.y][c1.x][c2.y][c2.x] = true;

            for (int d = 0; d < 4; d++) {
                Pos nc1 = new Pos(c1.y + dy[d], c1.x + dx[d]);
                Pos nc2 = new Pos(c2.y + dy[d], c2.x + dx[d]);

                // 둘다 나감
                if (!isValid(nc1.y, nc1.x) && !isValid(nc2.y, nc2.x))
                    continue;

                // 1개만 나감
                if ((!isValid(nc1.y, nc1.x) && isValid(nc2.y, nc2.x)) ||
                        (isValid(nc1.y, nc1.x) && !isValid(nc2.y, nc2.x))) {
                    answer = cur.count + 1;
                    return;
                }

                // 다음 코인 위치가 벽에 막히나?
                if (map[nc1.y][nc1.x] == '#') {
                    nc1 = c1;
                }
                if (map[nc2.y][nc2.x] == '#') {
                    nc2 = c2;
                }

                queue.offer(new Pair(nc1, nc2, cur.count + 1));
            }
        }
    }


    static private boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static class Pair {
        Pos coin1, coin2;
        int count;

        public Pair(Pos coin1, Pos coin2, int count) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.count = count;
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
