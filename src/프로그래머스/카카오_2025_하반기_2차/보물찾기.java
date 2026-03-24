package 프로그래머스.카카오_2025_하반기_2차;

import java.util.function.Function;

public class 보물찾기 {
    // 각 열의 최대 깊이를 담은 1차원 정수 배열 depth와 사용 가능한 총비용을 나타내는 정수 money가 매개변수로 주어집니다.
    // 또한 굴착 로봇이 특정 열을 파도록 하는 excavate 함수가 주어집니다.
    // 비용은 col의 depth만큼 필요
    // excavate는 보물을 찾은 경우 0, 보물이 왼쪽 방향에 있다면 -1, 오른쪽 방향에 있다면 1을 return

    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int w = depth.length;

        // Min-Max 전략
        // 최악의 경우에 최소비용
        // dp[L][R] L, R 사이에 보물이 있을 때 최소-최대비용
        // L ~ R 구간 중 하나를 선택했을때 이후 dp값이 가장 적은 것을 선택해야함.

        // k번째를 선택했는데 보물이 두 구간 중 더 비용이 많이 드는 곳에 있는 경우가 최악
        // dp[L][R] = depth[k] + Math.max(dp[L][k-1], dp[k+1][R]);
        // L ~ R 사이 k를 전부 다 계산해서 가장 작은 값을 가지는 k를 선택해야함
        int[][] dp = new int[w + 1][w + 1];
        int[][] dig = new int[w + 1][w + 1]; // dig[L][R] L,R 구간에서 최적 내가 파야하는 곳

        for (int len = 1; len <= w; len++) {
            for (int i = 1; i <= w - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j; k++) {
                    int leftCost = (k == i) ? 0 : dp[i][k - 1];
                    int rightCost = (k == j) ? 0 : dp[k + 1][j];

                    // 둘중에 더 최악 cost
                    int worst = depth[k - 1] + Math.max(leftCost, rightCost);

                    // 원래 값보다 작으면 갱신
                    if (worst < dp[i][j]) {
                        dp[i][j] = worst;
                        dig[i][j] = k;
                    }
                }
            }
        }

        int l = 1;
        int r = w;

        while (l <= r) {
            int k = dig[l][r];

            int res = excavate.apply(k);
            if (res == 0) {
                return k;
            } else if (res == -1) {
                r = k - 1;
            } else if (res == 1) {
                l = k + 1;
            }
        }

        return 0;
    }
}
