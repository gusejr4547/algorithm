package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17085_십자가_2개_놓기 {
    static int N, M, answer;
    static char[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        answer = 0;
        // # 칸에만 놓을 수 있다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int size = 0; size <= 7; size++) {
                    if (!canPlace(i, j, size)) {
                        break;
                    }

                    // 놓기
                    place(i, j, size, '*');

                    // 두번째 놓기
                    findSecond(1 + size * 4);

                    // 복구
                    place(i, j, size, '#');
                }
            }
        }

        System.out.println(answer);
    }

    private static void findSecond(int firstArea) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#') {
                    for (int size = 0; size <= 7; size++) {
                        if (!canPlace(i, j, size)) {
                            break;
                        }

                        int area = 1 + size * 4;
                        answer = Math.max(answer, firstArea * area);
                    }
                }
            }
        }
    }

    private static void place(int y, int x, int size, char c) {
        map[y][x] = c;
        for (int k = 1; k <= size; k++) {
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d] * k;
                int nx = x + dx[d] * k;
                map[ny][nx] = c;
            }
        }
    }

    private static boolean canPlace(int y, int x, int size) {
        if (size == 0) {
            return map[y][x] == '#';
        }
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d] * size;
            int nx = x + dx[d] * size;
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] != '#') {
                return false;
            }
        }
        return true;
    }
}
