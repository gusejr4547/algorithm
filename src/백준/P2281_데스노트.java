package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2281_데스노트 {
    static int n, m;
    static int[] name;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        name = new int[n];
        for (int i = 0; i < n; i++) {
            name[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = write(1, name[0]);

        System.out.println(answer);
    }

    private static int write(int idx, int length) {
        if (idx == n) {
            return 0;
        }

        if (dp[idx][length] != -1) {
            return dp[idx][length];
        }

        int result = Integer.MAX_VALUE;

        int empty = m - length;
        // 다음 줄
        result = Math.min(result, write(idx + 1, name[idx]) + empty * empty);

        // 이어서 작성
        if (name[idx] + length + 1 <= m) {
            result = Math.min(result, write(idx + 1, length + 1 + name[idx]));
        }

        return dp[idx][length] = result;
    }
}
