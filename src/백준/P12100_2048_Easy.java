package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12100_2048_Easy {
    static int N, result;
    static int[][] board;

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

        solution(board, 0);

        System.out.println(result);
    }

    public static void solution(int[][] originBoard, int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result = Math.max(result, originBoard[i][j]);
                }
            }
            return;
        }

        int[][] board = copyBoard(originBoard);
        // 4방향 기울이기

        // 좌
        for (int y = 0; y < N; y++) {
            int idx = 0;
            int block = 0;
            for (int x = 0; x < N; x++) {
                if (board[y][x] != 0) {
                    if (block == board[y][x]) {
                        board[y][idx - 1] = block * 2;
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
        solution(board, depth + 1);

        // 우
        board = copyBoard(originBoard);
        for (int y = 0; y < N; y++) {
            int idx = N - 1;
            int block = 0;
            for (int x = N - 1; x >= 0; x--) {
                if (board[y][x] != 0) {
                    if (block == board[y][x]) {
                        board[y][idx + 1] = block * 2;
                        block = 0;
                        board[y][x] = 0;
                    } else {
                        block = board[y][x];
                        board[y][x] = 0;
                        board[y][idx] = block;
                        idx--;
                    }
                }
            }
        }
        solution(board, depth + 1);

        // 상
        board = copyBoard(originBoard);
        for (int x = 0; x < N; x++) {
            int idx = 0;
            int block = 0;
            for (int y = 0; y < N; y++) {
                if (board[y][x] != 0) {
                    if (block == board[y][x]) {
                        board[idx - 1][x] = block * 2;
                        block = 0;
                        board[y][x] = 0;
                    } else {
                        block = board[y][x];
                        board[y][x] = 0;
                        board[idx][x] = block;
                        idx++;
                    }
                }
            }
        }
        solution(board, depth + 1);

        // 하
        board = copyBoard(originBoard);
        for (int x = 0; x < N; x++) {
            int idx = N - 1;
            int block = 0;
            for (int y = N - 1; y >= 0; y--) {
                if (board[y][x] != 0) {
                    if (block == board[y][x]) {
                        board[idx + 1][x] = block * 2;
                        block = 0;
                        board[y][x] = 0;
                    } else {
                        block = board[y][x];
                        board[y][x] = 0;
                        board[idx][x] = block;
                        idx--;
                    }
                }
            }
        }
        solution(board, depth + 1);
    }

    public static int[][] copyBoard(int[][] origin) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }
}
