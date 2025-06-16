package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P16565_N포커 {
    static final int MOD = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] comb = new int[53][53];
        for (int i = 0; i <= 52; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        int answer = 0;
        for (int i = 1; N - 4 * i >= 0; i++) {
            // i개의 포카드를 포함하는 경우의 수
            // 13개의 포카드 경우 중 i개의 숫자 선택
            // 52개에서 i개 세트 빼고 나머지 카드 선택
            int p = (comb[13][i] * comb[52 - 4 * i][N - 4 * i]) % MOD;

            // 포함-제외
            if (i % 2 == 1) {
                answer = (answer + p) % MOD;
            } else {
                // 음수 방지
                answer = (answer - p + MOD) % MOD;
            }
        }

        System.out.println(answer);
    }

}
