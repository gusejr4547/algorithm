package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P2624_동전_바꿔주기 {
    static int T, k;
    static int[][] coin;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        coin = new int[k + 1][2];
        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coin, Comparator.comparingInt(e -> e[0]));

        dp = new int[k + 1][T + 1];
        for (int i = 0; i <= k; i++) {
            dp[i][0] = 1;
        }

        for (int idx = 1; idx <= k; idx++) {
            int coinValue = coin[idx][0];
            int coinCnt = coin[idx][1];
            for (int money = 1; money <= T; money++) {
                for (int i = 0; i <= coinCnt; i++) {
                    if (money - (coinValue * i) < 0) break;
                    dp[idx][money] += dp[idx - 1][money - (coinValue * i)];
                }
            }
        }
        System.out.println(dp[k][T]);
    }
}
