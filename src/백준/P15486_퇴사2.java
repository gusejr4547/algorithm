package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15486_퇴사2 {
    static int N;
    static int[] T, P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        // dp[i] i일에 받을 수 있는 돈 최대값

        for (int i = 0; i < N; i++) {
            int endDay = i + T[i];
            if (endDay <= N) {
                dp[endDay] = Math.max(dp[endDay], dp[i] + P[i]);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]); // 현재 받는 돈이 다음날 받는 돈보다 크면 다음날 갱신
//            System.out.println("dp = " + Arrays.toString(dp));
        }

        System.out.println(dp[N]);
    }
}
