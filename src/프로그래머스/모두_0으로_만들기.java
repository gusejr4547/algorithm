package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 모두_0으로_만들기 {
    public static void main(String[] args) {
        모두_0으로_만들기 Main = new 모두_0으로_만들기();
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
        System.out.println(Main.solution(a, edges));
    }

    List<Integer>[] adj;
    boolean[] visit;
    long[] weights;
    long answer = 0;

    public long solution(int[] a, int[][] edges) {
        weights = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            weights[i] = a[i];
        }

        // 인접 리스트
        adj = new List[a.length];
        for (int i = 0; i < a.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // dfs
        visit = new boolean[a.length];
        visit[0] = true;
        long weight = dfs(0);

        if (weight != 0) {
            answer = -1;
        }

        return answer;
    }

    public long dfs(int node) {
        long weight = weights[node];
        for (int i = 0; i < adj[node].size(); i++) {
            int child = adj[node].get(i);
            if (!visit[child]) {
                visit[child] = true;
                long nextWeight = dfs(child);
                weight += nextWeight;
                answer += Math.abs(nextWeight);
            }
        }
//        System.out.println(node + " : " + weight);
        return weight;
    }

}
