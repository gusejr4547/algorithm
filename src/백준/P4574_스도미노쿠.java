package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4574_스도미노쿠 {
    static boolean complete;
    static int[][] grid;
    static boolean[][] rowUse, colUse, boxUse;
    static boolean[][] dominoUse;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            grid = new int[9][9];
            rowUse = new boolean[9][10]; // 행 별로 사용된 수
            colUse = new boolean[9][10]; // 열 별로 사용된 수
            boxUse = new boolean[9][10]; // 3x3 안에 사용된 수
            dominoUse = new boolean[10][10];
            complete = false;

            // 초기 상태
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                String LU = st.nextToken();
                int r = LU.charAt(0) - 'A';
                int c = LU.charAt(1) - '1';
                grid[r][c] = U;
                set(r, c, U, true);

                int V = Integer.parseInt(st.nextToken());
                String LV = st.nextToken();
                r = LV.charAt(0) - 'A';
                c = LV.charAt(1) - '1';
                grid[r][c] = V;
                set(r, c, V, true);

                dominoUse[U][V] = dominoUse[V][U] = true;
            }
            st = new StringTokenizer(br.readLine());
            for (int num = 1; num <= 9; num++) {
                String pos = st.nextToken();
                int r = pos.charAt(0) - 'A';
                int c = pos.charAt(1) - '1';
                grid[r][c] = num;
                set(r, c, num, true);
            }

            // 출력
            sb.append("Puzzle ").append(count).append('\n');

            // 인접한 2개 묶어야하는데... 스도쿠는 인접한거 2개 묶으면 무조건 다른 숫자 아님?
            // 1,2 조합 썼으면 다시 못쓰는 조건이다..
            solve(0, 0);

            count++;
        }

        System.out.println(sb);
    }

    private static void solve(int r, int c) {
        // 빠른종료
        if (complete) {
            return;
        }

        // 줄바꿈
        if (c == 9) {
            solve(r + 1, 0);
            return;
        }

        // 마지막
        if (r == 9) {
            complete = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(grid[i][j]);
                }
                sb.append('\n');
            }
            return;
        }

        // 이미 정해진곳
        if (grid[r][c] != 0) {
            solve(r, c + 1);
            return;
        }

        // (r,c)에 들어갈 숫자 찾기
        for (int a = 1; a <= 9; a++) {
            if (rowUse[r][a] || colUse[c][a] || boxUse[r / 3 * 3 + c / 3][a]) {
                continue;
            }

            // 가로
            if (c + 1 < 9 && grid[r][c + 1] == 0) {
                for (int b = 1; b <= 9; b++) {
                    if (a == b) {
                        continue;
                    }
                    // 사용한 도미노?
                    if (dominoUse[a][b]) {
                        continue;
                    }
                    if (rowUse[r][b] || colUse[c + 1][b] || boxUse[r / 3 * 3 + (c + 1) / 3][b]) {
                        continue;
                    }

                    grid[r][c] = a;
                    grid[r][c + 1] = b;
                    set(r, c, a, true);
                    set(r, c + 1, b, true);
                    dominoUse[a][b] = dominoUse[b][a] = true;

                    solve(r, c + 1);

                    grid[r][c] = 0;
                    grid[r][c + 1] = 0;
                    set(r, c, a, false);
                    set(r, c + 1, b, false);
                    dominoUse[a][b] = dominoUse[b][a] = false;

                    if (complete) {
                        return;
                    }
                }
            }

            // 세로
            if (r + 1 < 9 && grid[r + 1][c] == 0) {
                for (int b = 1; b <= 9; b++) {
                    if (a == b) {
                        continue;
                    }
                    // 사용한 도미노?
                    if (dominoUse[a][b]) {
                        continue;
                    }
                    if (rowUse[r + 1][b] || colUse[c][b] || boxUse[(r + 1) / 3 * 3 + c / 3][b]) {
                        continue;
                    }

                    grid[r][c] = a;
                    grid[r + 1][c] = b;
                    set(r, c, a, true);
                    set(r + 1, c, b, true);
                    dominoUse[a][b] = dominoUse[b][a] = true;

                    solve(r, c + 1);

                    grid[r][c] = 0;
                    grid[r + 1][c] = 0;
                    set(r, c, a, false);
                    set(r + 1, c, b, false);
                    dominoUse[a][b] = dominoUse[b][a] = false;

                    if (complete) {
                        return;
                    }
                }
            }
        }
    }

    private static void set(int r, int c, int num, boolean state) {
        rowUse[r][num] = state;
        colUse[c][num] = state;
        boxUse[r / 3 * 3 + c / 3][num] = state;
    }
}
