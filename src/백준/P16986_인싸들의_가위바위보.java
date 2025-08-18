package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16986_인싸들의_가위바위보 {
    static int N, K, answer;
    static int[][] playResult;
    static int[] kyunghee, minho;
    static boolean isEnd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        playResult = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                playResult[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        kyunghee = new int[20];
        minho = new int[20];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            kyunghee[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            minho[i] = Integer.parseInt(st.nextToken());
        }

        // 지우, 경희, 민호 순서
        // 지우가 모든 손동작을 다르게 내어 우승
        // 먼저 K 승에 도달해야함.
        answer = 0;
        play(0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(answer);
    }

    private static void play(int prevWin, int notPrevPlayer, int kyungheeTurn, int minhoTurn, int jiwooHand, int jiwooWin, int kyungheeWin, int minhoWin) {
        // 우승 확인
        if (answer == 1) {
            return;
        }

        // 우승 조건
        if (jiwooWin >= K) {
            answer = 1;
            isEnd = true;
            return;
        }

        // 다른 사람이 먼저 K 승 달성
        if (kyungheeWin >= K || minhoWin >= K) {
            return;
        }

        // 모든 손동작을 다 사용
        if (jiwooHand == (1 << (N + 1)) - 2) {
            return;
        }

        // 지우 vs 경희
        if (prevWin == 0 || (prevWin == 1 && notPrevPlayer == 2) || (prevWin == 2 && notPrevPlayer == 1)) {
            for (int i = 1; i <= N; i++) {
                if ((jiwooHand & (1 << i)) != 0) {
                    continue;
                }
                int result = playResult[i][kyunghee[kyungheeTurn]];
                if (result == 2) {
                    play(1, 3, kyungheeTurn + 1, minhoTurn, jiwooHand | (1 << i), jiwooWin + 1, kyungheeWin, minhoWin);
                } else {
                    play(2, 3, kyungheeTurn + 1, minhoTurn, jiwooHand | (1 << i), jiwooWin, kyungheeWin + 1, minhoWin);
                }
            }
        }

        // 지우 vs 민호
        if ((prevWin == 1 && notPrevPlayer == 3) || (prevWin == 3 && notPrevPlayer == 1)) {
            for (int i = 1; i <= N; i++) {
                if ((jiwooHand & (1 << i)) != 0) {
                    continue;
                }
                int result = playResult[i][minho[minhoTurn]];
                if (result == 2) {
                    play(1, 2, kyungheeTurn, minhoTurn + 1, jiwooHand | (1 << i), jiwooWin + 1, kyungheeWin, minhoWin);
                } else {
                    play(3, 2, kyungheeTurn, minhoTurn + 1, jiwooHand | (1 << i), jiwooWin, kyungheeWin, minhoWin + 1);
                }
            }
        }

        // 경희 vs 민호
        if ((prevWin == 2 && notPrevPlayer == 3) || (prevWin == 3 && notPrevPlayer == 2)) {
            int result = playResult[kyunghee[kyungheeTurn]][minho[minhoTurn]];
            if (result == 2) {
                play(2, 1, kyungheeTurn + 1, minhoTurn + 1, jiwooHand, jiwooWin, kyungheeWin + 1, minhoWin);
            } else {
                play(3, 1, kyungheeTurn + 1, minhoTurn + 1, jiwooHand, jiwooWin, kyungheeWin, minhoWin + 1);
            }
        }
    }
}
