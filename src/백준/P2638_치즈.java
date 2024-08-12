package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2638_치즈 {
    static int N, M;
    static int[][] cheese;
    static int[][] air;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        air = new int[N][M];
        fillAir();

//        for (int[] d : air) {
//            System.out.println(Arrays.toString(d));
//        }
        int time = calTotalMeltCheeseTime();
        System.out.println(time);
    }

    private static int calTotalMeltCheeseTime() {
        int totalTime = 0;

        ArrayDeque<Pos> nextMeltCheeseQueue = new ArrayDeque<>();
        findNextMeltCheese(nextMeltCheeseQueue);

        while (!nextMeltCheeseQueue.isEmpty()) {
            // 녹이기
            meltCheese(nextMeltCheeseQueue.clone());
            totalTime++;
            while (!nextMeltCheeseQueue.isEmpty()) {
                Pos cur = nextMeltCheeseQueue.poll();
                spreadAir(cur.y, cur.x);
            }
            findNextMeltCheese(nextMeltCheeseQueue);
        }
        return totalTime;
    }

    private static void findNextMeltCheese(ArrayDeque<Pos> nextMeltCheeseQueue) {
        nextMeltCheeseQueue.clear();
        // 녹는 치즈 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int sum = 0;
                if (cheese[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        sum += air[ny][nx];
                    }
                }
                if (sum >= 2) {
                    nextMeltCheeseQueue.offer(new Pos(i, j));
                }
            }
        }
    }

    private static void meltCheese(ArrayDeque<Pos> nextMeltCheeseQueue) {
        while (!nextMeltCheeseQueue.isEmpty()) {
            Pos cur = nextMeltCheeseQueue.poll();
            cheese[cur.y][cur.x] = 0;
        }
    }

    // 모는종이 가장자리는 치즈가 놓이지 않는다
    private static void fillAir() {
        for (int i = 0; i < N; i++) {
            if (air[i][0] != 1) {
                spreadAir(i, 0);
            }
            if (air[i][M - 1] != 1) {
                spreadAir(i, M - 1);
            }
        }
        for (int j = 0; j < M; j++) {
            if (air[0][j] != 1) {
                spreadAir(0, j);
            }
            if (air[N - 1][j] != 1) {
                spreadAir(N - 1, j);
            }
        }
    }


    private static void spreadAir(int y, int x) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(y, x));
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            if (air[cur.y][cur.x] == 1) {
                continue;
            }
            air[cur.y][cur.x] = 1;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || cheese[ny][nx] == 1) {
                    continue;
                }
                queue.offer(new Pos(ny, nx));
            }
        }
    }

    private static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
