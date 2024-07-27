package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2631_줄세우기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            numArr[i] = num;
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (numArr[j] < num) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(N - result);
    }
}
