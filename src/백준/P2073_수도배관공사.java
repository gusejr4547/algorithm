package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2073_수도배관공사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] dp = new int[D + 1];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            for (int dist = D; dist > L; dist--) {
                if (dp[dist - L] != 0) {
                    dp[dist] = Math.max(dp[dist], Math.min(dp[dist - L], C));
                }
            }
            dp[L] = Math.max(dp[L], C);

//            System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[D]);
    }
}
