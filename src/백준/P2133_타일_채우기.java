package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2133_타일_채우기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        // 3xN 크기의 벽을 2x1, 1x2 타일로 채우는 경우의 수?
        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= N; i+=2) {
            dp[i] = dp[i - 2] * 3; // 3x2 크기 짜리 3가지 방법 붙이기

            // 특수한 패턴 추가
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
