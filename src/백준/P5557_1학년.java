package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5557_1학년 {
    static int[] numArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N - 1][21];
        dp[0][numArr[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] == 0) continue;

                if (j + numArr[i] <= 20) {
                    dp[i][j + numArr[i]] += dp[i - 1][j];
                }
                if (j - numArr[i] >= 0) {
                    dp[i][j - numArr[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N - 2][numArr[N - 1]]);
    }
}
