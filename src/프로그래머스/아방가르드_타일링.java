package 프로그래머스;

import java.util.Arrays;

public class 아방가르드_타일링 {
    public static void main(String[] args) {
        아방가르드_타일링 Main = new 아방가르드_타일링();
        System.out.println(Main.solution(100000));
    }

    // 가로길이 n, 세로길이 3
    final int MOD = 1_000_000_007;

    public int solution(int n) {
        // 1 이전 타일 크기 더해서 현재 크기 만들기
        // 2 새로 타일 맞추기

        // i크기의 unique한 타일
        // 1, 2, 5, 2, 2, 4, 2, 2, 4 반복
        long[] dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        for (int i = 7; i <= n; i++) {
            dp[i] = (dp[i - 1] + (2 * dp[i - 2]) % MOD + (6 * dp[i - 3]) % MOD + dp[i - 4] - dp[i - 6]) % MOD;
            dp[i] = (dp[i] + MOD) % MOD;
        }

//        System.out.println(Arrays.toString(dp));

        return (int) dp[n];
    }
}
