package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P18809_Gaaaaaaaaaarden {
    static int N, M, G, R;
    static int[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Pos> possible;
    static boolean[] useIdx;
    static int[] green, red;
    static int answer = 0;

    static final int GREEN = 3;
    static final int RED = 4;
    static final int FLOWER = 5;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        possible = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    possible.add(new Pos(i, j));
                }
            }
        }

        useIdx = new boolean[possible.size()];
        green = new int[G];
        red = new int[R];
        combinationGreen(0, 0);

        System.out.println(answer);
    }

    public static void combinationGreen(int n, int idx) {
        if (n == G) {
            combinationRed(0, 0);
            return;
        }

        if (idx == possible.size())
            return;

        if (useIdx[idx]) {
            combinationGreen(n, idx + 1);
        } else {
            combinationGreen(n, idx + 1);
            useIdx[idx] = true;
            green[n] = idx;
            combinationGreen(n + 1, idx + 1);
            useIdx[idx] = false;
        }

    }

    public static void combinationRed(int n, int idx) {
        if (n == R) {
            System.out.println(Arrays.toString(green));
            System.out.println(Arrays.toString(red));
            System.out.println("----");
            bfs();
            return;
        }
        if (idx == possible.size())
            return;

        if (useIdx[idx]) {
            combinationRed(n, idx + 1);
        } else {
            combinationRed(n, idx + 1);
            useIdx[idx] = true;
            red[n] = idx;
            combinationRed(n + 1, idx + 1);
            useIdx[idx] = false;
        }
    }

    public static void bfs() {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        State[][] states = new State[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                states[i][j] = new State();
            }
        }

        for (int i = 0; i < G; i++) {
            Pos p = possible.get(green[i]);
            states[p.y][p.x] = new State(0, RED);
            queue.offer(new Pos(p.y, p.x));
        }

        for (int i = 0; i < R; i++) {
            Pos p = possible.get(red[i]);
            states[p.y][p.x] = new State(0, GREEN);
            queue.offer(new Pos(p.y, p.x));
        }

        int result = 0;

        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            int y = curr.y;
            int x = curr.x;
            int currTime = states[y][x].time;
            int currType = states[y][x].type;

            if (currTime == FLOWER) continue;

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0) continue;
                if (states[ny][nx].type == 0) {
                    states[ny][nx].type = currType;
                    states[ny][nx].time = currTime + 1;
                    queue.offer(new Pos(ny, nx));
                } else if (states[ny][nx].type == RED) {
                    if (currType == GREEN && states[ny][nx].time == currTime + 1) {
                        result += 1;
                        states[ny][nx].type = FLOWER;
                    }
                } else if (states[ny][nx].type == GREEN) {
                    if (currType == RED && states[ny][nx].time == currTime + 1) {
                        result += 1;
                        states[ny][nx].type = FLOWER;
                    }
                }

            }
        }
        System.out.println(result);
        answer = Math.max(answer, result);
    }

    public static class State {
        int time;
        int type;

        public State() {
        }

        public State(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    public static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
