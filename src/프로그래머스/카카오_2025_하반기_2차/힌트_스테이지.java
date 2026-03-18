package 프로그래머스.카카오_2025_하반기_2차;

public class 힌트_스테이지 {
    public static void main(String[] args) {
        힌트_스테이지 Main = new 힌트_스테이지();
        int[][] cost = {{160, 140, 120, 110, 60}, {290, 270, 260, 120, 10}, {160, 130, 120, 60, 20}, {160, 120, 80, 70, 20}, {110, 70, 60, 30, 20}};
        int[][] hint = {{40, 2, 3}, {40, 5, 3}, {20, 5, 4}, {50, 5, 5}};
        System.out.println(Main.solution(cost, hint));
    }

    // 1 -> N번 순서대로 해결
    // i번 힌트권은 i스테이지에서 사용

    // 모든 스테이지를 해결하는데 필요한 최소 비용을 return
    // n <= 16
    int N, K, answer;

    public int solution(int[][] cost, int[][] hint) {
        N = cost.length;
        K = hint[0].length - 1;

        // 각 스테이지마다 사용할 수 있는 힌트권은 다 사용하는걸로
        // 각 스테이지마다 힌트권 사거나 안사거나
        // dfs로 전부 탐색
        answer = Integer.MAX_VALUE;

        dfs(0, 0, new int[N], cost, hint);

        return answer;
    }

    private void dfs(int round, int totalCost, int[] hintCnt, int[][] cost, int[][] hint) {
        // 이번 round에 가지고 있는 힌트 사용한 cost
        // 사용할 힌트권
        int use = Math.min(N - 1, hintCnt[round]);
        int curCost = totalCost + cost[round][use];
        if (round == N - 1) {
            answer = Math.min(answer, curCost);
            return;
        }

        // 힌트 번들 구매x
        dfs(round + 1, curCost, hintCnt, cost, hint);

        // 힌트 번들 구매
        curCost += hint[round][0];
        for (int i = 1; i <= K; i++) {
            hintCnt[hint[round][i] - 1]++;
        }
        dfs(round + 1, curCost, hintCnt, cost, hint);
        for (int i = 1; i <= K; i++) {
            hintCnt[hint[round][i] - 1]--;
        }
    }
}
