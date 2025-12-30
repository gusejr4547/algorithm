package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H_오량 {
    // 최종적으로 가지고 있는 노트 개수가 주어짐
    // 노트는 전부 다름
    // 학생이 가지고 있는 노트 경우의 수?

    // T<=1000, N<=2000

    static long MOD = 1_000_000_007;
    static int MAX = 2000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

//        // 팩토리얼 계산
//        long[] factorial = new long[MAX + 1];
//        factorial[0] = 1;
//        for (int i = 1; i <= MAX; i++) {
//            factorial[i] = (factorial[i - 1] * i) % MOD;
//        }
//
//        // 역원 a^-1 = a^(P-2) mod P
//        long[] invFactorial = new long[MAX + 1];
//        invFactorial[MAX] = pow(factorial[MAX], MOD - 2);
//        for (int i = MAX - 1; i >= 0; i--) {
//            invFactorial[i] = (invFactorial[i + 1] * (i + 1)) % MOD;
//        }
//
//        for (int tc = 0; tc < T; tc++) {
//            int N = Integer.parseInt(br.readLine());
//            StringTokenizer st = new StringTokenizer(br.readLine());
//
//            // state 중에 0이 없으면 처음 상태 그대로 인것....
//            boolean hasZero = false;
//            int[] state = new int[N];
//            for (int i = 0; i < N; i++) {
//                state[i] = Integer.parseInt(st.nextToken());
//                if (state[i] == 0) {
//                    hasZero = true;
//                }
//            }
//
//            long answer = factorial[N];
//            if (hasZero) {
//                for (int A : state) {
//                    if (A != 0) {
//                        // 역원 곱하기
//                        answer = (answer * invFactorial[A]) % MOD;
//                    }
//                }
//            } else {
//                answer = 1;
//            }
//
//            sb.append(answer).append('\n');
//        }

        // 2000개중에 0 ~ 2000개 뽑는 경우의 수 미리 계산
        long[][] dp = new long[MAX + 1][MAX + 1];
        for (int n = 0; n <= MAX; n++) {
            dp[n][0] = 1;
            dp[n][n] = 1;
            for (int k = 1; k < n; k++) {
                dp[n][k] = (dp[n - 1][k - 1] + dp[n - 1][k]) % MOD;
            }
        }

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());


            boolean hasZero = false;
            int[] state = new int[N];
            for (int i = 0; i < N; i++) {
                state[i] = Integer.parseInt(st.nextToken());
                if (state[i] == 0) {
                    hasZero = true;
                }
            }

            long answer = 1;
            int total = N;
            if (hasZero) {
                for (int A : state) {
                    if (A != 0) {
                        // total개 중에 A개를 뽑는 경우의 수
                        answer = (answer * dp[total][A]) % MOD;
                    }
                    total -= A;
                }
            }
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    static long pow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return result;
    }
}
