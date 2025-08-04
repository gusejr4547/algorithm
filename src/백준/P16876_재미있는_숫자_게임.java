package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16876_재미있는_숫자_게임 {
    static String N;
    static int M;
    static boolean[][][][][] win, visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[M + 1][10][10][10][10];
        win = new boolean[M + 1][10][10][10][10];

        boolean answer = game(0, N.charAt(0) - '0', N.charAt(1) - '0', N.charAt(2) - '0', N.charAt(3) - '0');


        System.out.println(answer ? "koosaga" : "cubelover");
    }

    private static boolean game(int turn, int a, int b, int c, int d) {
        if (visit[turn][a][b][c][d]) {
            return win[turn][a][b][c][d];
        }

        if (turn == M) {
            int original = Integer.parseInt(N);
            int make = a * 1000 + b * 100 + c * 10 + d;

            boolean koosagaWin = original < make;
            boolean koosagaTurn = turn % 2 == 0;

            visit[turn][a][b][c][d] = true;
            if (koosagaTurn && koosagaWin || !koosagaTurn && !koosagaWin) {
                return win[turn][a][b][c][d] = true;
            } else {
                return win[turn][a][b][c][d] = false;
            }
        }

        boolean result = false;
        result |= !game(turn + 1, (a + 1) % 10, b, c, d);
        result |= !game(turn + 1, a, (b + 1) % 10, c, d);
        result |= !game(turn + 1, a, b, (c + 1) % 10, d);
        result |= !game(turn + 1, a, b, c, (d + 1) % 10);
        visit[turn][a][b][c][d] = true;
        return win[turn][a][b][c][d] = result;
    }
}
