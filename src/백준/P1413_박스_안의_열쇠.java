package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1413_박스_안의_열쇠 {
    static int N, M;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][N + 1]; // dp[i][j] i개의 수에서 j개의 사이클 만드는 경우의 수

        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                // 새로운 사이클 만드는 경우의 수 + 기존 사이클에 끼워 넣기(넣을 수 있는 자리 * i-1개 수로 j 사이클 만드는 경우의수)
                dp[i][j] = dp[i - 1][j - 1] + (i - 1) * dp[i - 1][j];
            }
        }

        long up = 0;
        long down = 0;


        for (int j = 1; j <= M; j++) {
            up += dp[N][j];
        }

        for (int j = 1; j <= N; j++) {
            down += dp[N][j];
        }


//        for (long[] d : dp) {
//            System.out.println(Arrays.toString(d));
//        }

//        System.out.println(up);
//        System.out.println(down);
        long g = gcd(up, down);
        System.out.printf("%d/%d", up/g, down/g);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
