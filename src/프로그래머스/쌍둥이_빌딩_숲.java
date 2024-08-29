package 프로그래머스;

import java.util.Arrays;

public class 쌍둥이_빌딩_숲 {
    public static void main(String[] args) {
        쌍둥이_빌딩_숲 Main = new 쌍둥이_빌딩_숲();
        int n = 100;
        int count = 10;
        System.out.println(Main.solution(n, count));
    }

    // 1부터 n까지 각각 2 채씩 총 2n채의 빌딩이 존재
    // 같은 높이를 가지는 빌딩 사이에는 그보다 높은 빌딩이 존재하지 않습니다.
    final int MOD = 1_000_000_007;

    public int solution(int n, int count) {
        // 가장 높은 건물은 무조건 보임.
        // 높은 건물부터 배치

        long[][] dp = new long[n + 1][n + 1]; // dp[i][j] i높이 일때 j count인 경우 종류 몇개?
        dp[1][1] = 1;

        for (int useCnt = 2; useCnt <= n; useCnt++) {
            // count? 몇개 보이게 만들꺼?
            for (int see = 1; see <= useCnt; see++) {
                dp[useCnt][see] = ((dp[useCnt - 1][see] * 2 * (useCnt - 1)) + dp[useCnt - 1][see - 1]) % MOD;
            }
        }

        for(long[] d : dp){
            System.out.println(Arrays.toString(d));
        }

        int answer = (int) dp[n][count];
        return answer;
    }
}
