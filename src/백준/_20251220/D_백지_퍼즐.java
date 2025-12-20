package 백준._20251220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D_백지_퍼즐 {
    // 조각 모양은 3^4 = 81가지
    // N*M 크기의 퍼즐 만들 수 있는 경우의 수
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 오목 - 볼록
        // 볼록 - 오목
        // 평평 - 평평
        // 맞닫는 면 3가지 경우

        // 총 맞닫는 면 개수?
        // 가로 => N(M-1)
        // 세로 => M(N-1)
        // 2NM - N - M

        // 3^(2NM - N - M)
        int exp = 2 * N * M - N - M;
        long answer = pow(3, exp);

        System.out.println(answer);
    }

    private static long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 != 0) {
                result = result * base % MOD;
            }
            base = (base * base) % MOD;
            exp = exp / 2;
        }
        return result;
    }
}
