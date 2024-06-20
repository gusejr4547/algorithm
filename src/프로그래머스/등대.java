package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 등대 {
    public static void main(String[] args) {
        등대 Main = new 등대();
        int n = 8;
        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        System.out.println(Main.solution(n, lighthouse));
    }

    List<Integer>[] adj;
    int[][] dp;

    public int solution(int n, int[][] lighthouse) {
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] arr : lighthouse) {
            adj[arr[0]].add(arr[1]);
            adj[arr[1]].add(arr[0]);
        }

        dp = new int[n + 1][2]; // 0은 on, 1은 off
        // 1번 등대에서 출발
        dfs(1, 0);

        return Math.min(dp[1][0], dp[1][1]);
    }

    public void dfs(int node, int parent) {
        for (int i = 0; i < adj[node].size(); i++) {
            int nextNode = adj[node].get(i);
            if (nextNode == parent) {
                continue;
            }
            dfs(nextNode, node);
            dp[node][0] += Math.min(dp[nextNode][0], dp[nextNode][1]);
            dp[node][1] += dp[nextNode][0];
        }
        dp[node][0]++;
    }

}
