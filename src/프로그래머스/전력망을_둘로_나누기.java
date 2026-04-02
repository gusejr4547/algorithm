package 프로그래머스;

import java.util.*;

public class 전력망을_둘로_나누기 {
    public static void main(String[] args) {

    }

    // n <= 100
    int N, minDiff;
    List<Integer>[] adj;
    boolean[] visit;

    public int solution(int n, int[][] wires) {
        this.N = n;
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }

        minDiff = Integer.MAX_VALUE;
        visit = new boolean[N + 1];
        // 1번노드를 root로 하는 트리
        dfs(1);

        return minDiff;
    }

    private int dfs(int cur) {
        visit[cur] = true;

        int subTreeSize = 1;
        // 다음 노드
        for (int next : adj[cur]) {
            if (!visit[next]) {
                subTreeSize += dfs(next);
            }
        }

        // 차이값 계산
        int diff = Math.abs(N - subTreeSize * 2);
        minDiff = Math.min(minDiff, diff);

        return subTreeSize;
    }
}
