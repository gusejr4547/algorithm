package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15824_너_봄에는_캡사이신이_맛있단다 {
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] menu = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            menu[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(menu);
        long answer = 0;

        // 최댓값으로 쓰이는 경우 전부 +
        // 최솟값으로 쓰이는 경우 전부 -
        for (int i = 0; i < N; i++) {
            answer += (menu[i] * powerMod(2, i, MOD));
            answer -= (menu[i] * powerMod(2, N - 1 - i, MOD));

            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static long powerMod(int base, int exp, int mod) {
        if (exp == 0) {
            return 1;
        }
        long half = powerMod(base, exp / 2, mod);
        if (exp % 2 == 1) {
            return half * half * base % mod;
        } else {
            return half * half % mod;
        }
    }
}
