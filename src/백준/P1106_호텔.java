package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1106_호텔 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] info = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            info[i][0] = cost;
            info[i][1] = customer;
        }

        int[] dp = new int[C + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                int cost = info[i][0];
                int customer = info[i][1];
                int next = Math.min(j + customer, C);
                if (dp[j] == Integer.MAX_VALUE) {
                    dp[next] = Math.min(Integer.MAX_VALUE, dp[next]);
                } else {
                    dp[next] = Math.min(dp[j] + cost, dp[next]);
                }
            }
        }

        System.out.println(dp[C]);
    }
}
