package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P18808_스티커_붙이기 {
    static int N, M, K;
    static int[][] notebook;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 최대 4회전
            for (int k = 0; k < 4; k++) {
                int r = sticker.length;
                int c = sticker[0].length;

                if (stick(sticker, r, c)) {
                    break;
                }

                sticker = rotate90(sticker);
            }
        }

//        for (int[] arr : notebook) {
//            System.out.println(Arrays.toString(arr));
//        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1)
                    result += 1;
            }
        }
        System.out.println(result);
    }

    public static boolean stick(int[][] sticker, int R, int C) {
        if (N - R < 0 || M - C < 0) return false;

        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                if (valid(sticker, R, C, i, j)) {
                    fill(sticker, R, C, i, j);
                    return true;
                }
            }
        }

        return false;
    }

    public static void fill(int[][] sticker, int R, int C, int y, int x) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1) {
                    notebook[y + i][x + j] = sticker[i][j];
                }
            }
        }
    }

    public static boolean valid(int[][] sticker, int R, int C, int y, int x) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (notebook[y + i][x + j] == 1 && sticker[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    public static int[][] rotate90(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] temp = new int[C][R];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[j][R - i - 1] = sticker[i][j];
            }
        }
        return temp;
    }
}
