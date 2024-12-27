package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2447_별_찍기_10 {
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        draw(0, 0, N, false);

        for (int i = 0; i < N; i++) {
            System.out.println(String.valueOf(map[i]));
        }
    }

    private static void draw(int y, int x, int size, boolean isEmpty) {
        // size == 1이면 그리기
        if (size == 1) {
            map[y][x] = isEmpty ? ' ' : '*';
            return;
        }

        // 전체 size 3등분씩 해서 9구역으로 나눔.
        size = size / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    draw(y + size * i, x + size * j, size, true);
                } else {
                    draw(y + size * i, x + size * j, size, isEmpty);
                }
            }
        }
    }
}
