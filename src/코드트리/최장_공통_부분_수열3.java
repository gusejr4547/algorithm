package 코드트리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 최장_공통_부분_수열3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[m];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        for (int i = 0; i < m; i++) B[i] = sc.nextInt();
        // Please write your code here.
        List<Integer>[][] dp = new List[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    // 일치하면
                    dp[i][j] = new ArrayList<>(dp[i - 1][j - 1]);
                    dp[i][j].add(A[i - 1]);
                } else {
                    // 다르면
                    dp[i][j] = leastOrderList(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        List<Integer> answer = dp[n][m];
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

    private static List<Integer> leastOrderList(List<Integer> a, List<Integer> b) {
        // size
        if (a.size() != b.size()) {
            return a.size() > b.size() ? new ArrayList<>(a) : new ArrayList<>(b);
        }

        // 사전순
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i) ? new ArrayList<>(a) : new ArrayList<>(b);
            }
        }

        return new ArrayList<>(a);
    }
}
