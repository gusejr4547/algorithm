package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1695_팰린드롬_만들기 {
    static int[] numArr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            numArr[i] = num;
        }
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        int result = sol(0, N - 1);
        System.out.println(result);
    }

    private static int sol(int left, int right) {
        if (left >= right) return 0;
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        if (numArr[left] == numArr[right]) {
            return dp[left][right] = sol(left + 1, right - 1);
        } else {
            int min = Math.min(sol(left + 1, right) + 1, sol(left, right - 1) + 1);
            return dp[left][right] = min;
        }
    }
}
