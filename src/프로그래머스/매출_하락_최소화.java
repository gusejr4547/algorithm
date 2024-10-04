package 프로그래머스;

import java.util.*;

public class 매출_하락_최소화 {
    public static void main(String[] args) {
        매출_하락_최소화 Main = new 매출_하락_최소화();
        int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
        int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
        System.out.println(Main.solution(sales, links));
    }

    // 1번은 회사의 CEO로 고정

    // 모든 팀은 최소 1명 이상의 직원을 워크숍에 참석
    // 워크숍에 참석하는 직원들의 하루평균 매출액의 합이 최소
    // 최소화된 매출액의 합을 구해서 return
    List<Integer>[] adj;
    int[][] dp;
    int[] SALES;

    public int solution(int[] sales, int[][] links) {
        SALES = sales;
        dp = new int[sales.length][2]; // dp[사원번호][참여여부]

        // 트리 만들기
        adj = new List[sales.length];
        for (int i = 0; i < sales.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] link : links) {
            adj[link[0] - 1].add(link[1] - 1);
        }

        dfs(0);

        int answer = Math.min(dp[0][0], dp[0][1]);
        return answer;
    }

    private void dfs(int empNo) {
        // 리프노드
        if (adj[empNo].isEmpty()) {
            dp[empNo][1] = SALES[empNo];
            dp[empNo][0] = 0;
            return;
        }

        int sum = 0; // 하위 노드의 최소 합
        boolean isChildGoWorkShop = false;
        int minDiff = Integer.MAX_VALUE;

        for (int child : adj[empNo]) {
            dfs(child);

            if (dp[child][1] <= dp[child][0]) {
                isChildGoWorkShop = true;
                sum += dp[child][1];
            } else {
                sum += dp[child][0];
            }

            minDiff = Math.min(minDiff, dp[child][1] - dp[child][0]);
        }

        // empNo 워크숍 보냄?
        dp[empNo][1] = SALES[empNo] + sum;

        // 안보냄
        // 나말고 팀원중 워크숍간사람 있음
        if (isChildGoWorkShop) {
            dp[empNo][0] = sum;
        }
        // 나말고 팀원도 안감
        else {
            dp[empNo][0] = sum + minDiff;
        }
    }
}
