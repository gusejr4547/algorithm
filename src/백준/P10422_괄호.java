package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10422_괄호 {
    static final int MOD = 1_000_000_007;
    static long[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        a = new long[5001];
        a[0] = 1;
        a[2] = 1;

        for (int len = 4; len <= 5000; len += 2) {
            for (int k = 0; k <= len - 2; k += 2) {
                a[len] = (a[len] + (a[k] * a[len - k - 2])) % MOD;
            }
        }

        while (T-- > 0) {
            int length = Integer.parseInt(br.readLine());
            if (length % 2 != 0) {
                System.out.println(0);
            } else {
                System.out.println(a[length]);
            }
        }
    }
}
