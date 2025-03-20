package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11048_이동하기 {
    static int[] dy = {1, 0, 1};
    static int[] dx = {0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int[] d : map){
//            System.out.println(Arrays.toString(d));
//        }
        int[][] dp = new int[N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], -1);
        }

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(0, 0));
        dp[0][0] = map[0][0];

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            for (int d = 0; d < 3; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                    continue;

                if (dp[ny][nx] < dp[cur.y][cur.x] + map[ny][nx]) {
                    dp[ny][nx] = dp[cur.y][cur.x] + map[ny][nx];
                    queue.offer(new State(ny, nx));
                }
            }
        }
//        for(int[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }
        System.out.println(dp[N - 1][M - 1]);
    }

    private static class State {
        int y, x;

        public State(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
