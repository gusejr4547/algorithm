package 코드트리;

import java.util.Scanner;

public class 외판원_순회 {
    static final int INF = 999999999;
    static int allVisitMask, totalPlace;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        // cost가 0이면 길이 없는것
        totalPlace = n;
        allVisitMask = (1 << n) - 1; // 이걸로 방문 확인
        dp = new int[n][allVisitMask]; // dp[i][j] i위치에서 j방문 체크 된걸로 있는 경우 나머지 탐색했을때 최소값

        int answer = tsp(0, 1, cost);

        System.out.println(answer);
    }

    private static int tsp(int place, int visitMask, int[][] cost) {
        // 전부 방문함.
        if (visitMask == allVisitMask) {
            // 원래 출발지점으로 돌아가기 => 나는 0으로 설정함
            // 못돌아가는 경우
            if (cost[place][0] == 0) {
                return INF;
            }
            // 돌아가는 비용
            return cost[place][0];
        }

        // 이미 계산된 값이 존재?
        if (dp[place][visitMask] != 0) {
            return dp[place][visitMask];
        }

        dp[place][visitMask] = INF;
        for (int nextPlace = 0; nextPlace < totalPlace; nextPlace++) {
            // 길이 없음 or 이미 방문함.
            if (cost[place][nextPlace] == 0 || (visitMask & (1 << nextPlace)) != 0) {
                continue;
            }

            int totalCost = tsp(nextPlace, visitMask | (1 << nextPlace), cost) + cost[place][nextPlace];
            dp[place][visitMask] = Math.min(dp[place][visitMask], totalCost);
        }

        return dp[place][visitMask];
    }
}
