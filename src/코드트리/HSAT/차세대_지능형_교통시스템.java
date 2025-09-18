package 코드트리.HSAT;

import java.util.ArrayDeque;
import java.util.Scanner;

public class 차세대_지능형_교통시스템 {
    static int N, T, answer;
    static int[][][] signals;
    static boolean[][][][] visit;
    static boolean[][] check;
    // 우, 좌, 하, 상
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    // 신호 종류
    static Type[] type = {
            new Type(0, new int[]{0, 2, 3}),
            new Type(3, new int[]{0, 1, 3}),
            new Type(1, new int[]{1, 2, 3}),
            new Type(2, new int[]{0, 1, 2}),

            new Type(0, new int[]{0, 3}),
            new Type(3, new int[]{1, 3}),
            new Type(1, new int[]{1, 2}),
            new Type(2, new int[]{0, 2}),

            new Type(0, new int[]{0, 2}),
            new Type(3, new int[]{0, 3}),
            new Type(1, new int[]{2, 3}),
            new Type(2, new int[]{1, 2})
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        signals = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    signals[i][j][k] = sc.nextInt() - 1;
                }
            }
        }

        visit = new boolean[N][N][4][4];
        check = new boolean[N][N];
        // 처음에는 제일 왼쪽 위의 교차로로 아래쪽 방향에서 진입.
        // 자동차가 멈추지 않고 T 시간 이내에 갈 수 있는 교차로의 수를 계산
        // 진입 방향도 중요하다.

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(0, 0, 3));

        int time = 0;
        while (!queue.isEmpty() && time <= T) {
            int size = queue.size();
            while (size-- > 0) {
                State cur = queue.poll();

                // 체크
                if(!check[cur.y][cur.x]){
                    answer++;
                    check[cur.y][cur.x] = true;
                }

                if (visit[cur.y][cur.x][cur.d][time % 4]) {
                    continue;
                }
                visit[cur.y][cur.x][cur.d][time % 4] = true;

                // 진입 가능?
                int signal = signals[cur.y][cur.x][time % 4];
                if (cur.d != type[signal].inDir) {
                    continue;
                }

                for (int d : type[signal].next) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx][d][(time + 1) % 4]) {
                        continue;
                    }

                    queue.offer(new State(ny, nx, d));
                }
            }

            time++;
        }
    }

    private static class State {
        int y, x, d;

        public State(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    private static class Type {
        int inDir;
        int[] next;

        public Type() {
        }

        public Type(int inDir, int[] next) {
            this.inDir = inDir;
            this.next = next;
        }
    }
}
