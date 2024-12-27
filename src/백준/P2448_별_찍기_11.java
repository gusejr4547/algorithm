package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2448_별_찍기_11 {
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }

        draw(0, (2 * N - 1) / 2, N);

        for (char[] line : map) {
            System.out.println(String.valueOf(line));
        }
    }

    private static void draw(int y, int x, int height) {
        // 높이가 3이면 (y,x)기준으로 삼각형을 그림
        if (height == 3) {
            for (int h = 0; h < 3; h++) {
                for (int w = h * -1; w <= h; w++) {
                    if (h == 1 && w == 0) continue;
                    map[y + h][x + w] = '*';
                }
            }
            return;
        }

        height /= 2;

        // 구역 나누기 - 3개 삼각형 위쪽 꼭지점
        draw(y, x, height);
        draw(y + height, x - height, height);
        draw(y + height, x + height, height);
    }
}
