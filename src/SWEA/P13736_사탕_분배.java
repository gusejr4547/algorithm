package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class P13736_사탕_분배 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 나연
            int B = Integer.parseInt(st.nextToken()); // 다현
            int K = Integer.parseInt(st.nextToken()); // 반복횟수

            // (2^K * A) % sum
            int sum = A + B;
            int bound = sum / 2;

            long result = (get2Pow(K, sum) * A) % sum;

            result = result > bound ? sum - result : result;
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static long get2Pow(long k, long mod) {
        if (k == 0) {
            return 1;
        }

        long num = get2Pow(k / 2, mod);
        num = (num * num) % mod;
        if (k % 2 == 1) {
            num = (num * 2) % mod;
        }
        return num;
    }
}
