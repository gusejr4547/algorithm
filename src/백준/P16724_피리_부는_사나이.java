package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16724_피리_부는_사나이 {
    static int N, M;
    static int[] next;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        next = new int[N * M];
        for (int i = 0; i < N * M; i++) {
            next[i] = i;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char dir = map[i][j];
                int ny = i;
                int nx = j;
                if ('U' == dir) {
                    ny -= 1;
                } else if ('D' == dir) {
                    ny += 1;
                } else if ('L' == dir) {
                    nx -= 1;
                } else if ('R' == dir) {
                    nx += 1;
                }

                if (find(ny * M + nx) != find(i * M + j)) {
                    union(i * M + j, ny * M + nx);
                }
            }
        }

        int cnt = 0;
        boolean[] visit = new boolean[N * M];
        for (int i = 0; i < N * M; i++) {
            int pos = find(i);
            if (!visit[pos]) {
                cnt++;
                visit[pos] = true;
            }
        }

        System.out.println(cnt);
    }

    private static int find(int x) {
        if (x == next[x]) {
            return x;
        }
        return next[x] = find(next[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            next[x] = y;
        }
    }
}
