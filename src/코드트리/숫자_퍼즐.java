package 코드트리;

import java.util.*;

public class 숫자_퍼즐 {
    static int n, m, k;
    static long[][][] dp;
    static List<Integer> result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        // Please write your code here.
        dp = new long[n + 1][m + 1][m + 1]; // 수열 개수 저장 , dp[i][j][k]  i 마법석 수, j 숫자 합, k 현재 마법석 숫자

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= m; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        count(0, 0, 1);

        result = new ArrayList<>();
        make(0, 0, 1, k);


        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    static void make(int depth, int sum, int last, int kth) {
        if (depth == n) {
            return;
        }

        for (int i = last; sum + i <= m; i++) {
            long cnt = count(depth + 1, sum + i, i);
            if (cnt < k) {
                k -= cnt;
            } else {
                result.add(i);
                make(depth + 1, sum + i, i, k);
                return;
            }
        }
    }

    static long count(int depth, int sum, int last) {
        if (depth == n) {
            return sum == m ? 1 : 0;
        }

        if (dp[depth][sum][last] != -1) {
            return dp[depth][sum][last];
        }

        long cnt = 0;
        for (int i = last; sum + i <= m; i++) {
            cnt += count(depth + 1, sum + i, i);
        }

        return dp[depth][sum][last] = cnt;
    }
}
