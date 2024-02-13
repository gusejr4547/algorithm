package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12100_2048_Easy {
    static int N;
    static int[][] board;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }

    public static void solution(int[][] board, int depth) {
        if (depth == 5) return;
        int[][] origin = copyBoard(board);

        // 4방향 기울이기

        // 좌
        for (int y = 0; y < N; y++) {
            int idx = 0;
            int block = 0;
            for (int x = 0; x < N; x++) {
                if (board[y][x] != 0) {
                    if (block == board[y][x]) {
                        board[y][x - 1] = block * 2;
                        block = 0;
                        board[y][x] = 0;
                    } else {
                        block = board[y][x];
                        board[y][x] = 0;
                        board[y][idx] = block;
                        idx++;
                    }
                }
            }


        }
    }

    public static int[][] copyBoard(int[][] origin) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }
}
