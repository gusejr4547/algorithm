package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11066_파일_합치기 {
    static int dpUse = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] files = new int[K];
            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
            }
            int[] prefixSum = new int[K + 1];
            for (int i = 1; i <= K; i++) {
                prefixSum[i] = prefixSum[i - 1] + files[i - 1];
            }
            int[][] dp = new int[K][K]; // dp[i][j] i~j까지 파일 합했을때 가장 작은 비용
            dpUse = 0;
            System.out.println(dfs(0, K - 1, dp, prefixSum));
            System.out.println(dpUse);
        }
    }

    private static int dfs(int left, int right, int[][] dp, int[] filesPrefixSum) {
        if (dp[left][right] != 0) {
            dpUse++;
            return dp[left][right];
        }
        if (right - left == 0) {
            return dp[left][right] = 0;
        }
        int minCost = Integer.MAX_VALUE;
        int rangeCost = filesPrefixSum[right + 1] - filesPrefixSum[left];
        for (int split = left; split < right; split++) {
            minCost = Math.min(minCost,
                    dfs(left, split, dp, filesPrefixSum) + dfs(split + 1, right, dp, filesPrefixSum) + rangeCost);
        }

        return dp[left][right] = minCost;
    }
}
