package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14501_퇴사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N];
        int[] cost = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            // 이날 일 안하는 경우
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            // 일하는 경우
            if (i + time[i] <= N) {
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + cost[i]);
            }
        }

        System.out.println(dp[N]);
    }
}
