package 프로그래머스;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPS {
    public static void main(String[] args) {
        GPS Main = new GPS();
        int n = 7;
        int m = 10;
        int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}};
        int k = 6;
        int[] gps_logs = {1, 2, 3, 3, 6, 7};
        System.out.println(Main.solution(n, m, edge_list, k, gps_logs));
    }

    // 택시의 이동 경로를 GPS를 통해 수집하게 되는데, GPS 신호 불량, 통신 오류 등 다양한 원인으로 위치의 오류가 발생한 것을 알게 되었다.
    // 다만 승차 위치와 하차 위치는 오류가 없는 것으로 확인이 되었다.
    // 오류를 최소한으로 수정하여 좀 더 정확한 이동 경로를 구하고 싶어 한다
    int N;
    List<Integer>[] adj;
    int[][] dp;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        N = n;
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edge_list) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        dp = new int[k][n + 1];
        for (int[] d : dp) {
            Arrays.fill(d, 100000);
        }

        // 시작점
        dp[0][gps_log[0]] = 0;

        for (int t = 1; t < k; t++) {
            for (int prev = 1; prev <= n; prev++) {
                int goal = gps_log[t];
                for (int next : adj[prev]) {
                    if (next == goal) {
                        dp[t][next] = Math.min(dp[t][next], dp[t - 1][prev]);
                    } else {
                        dp[t][next] = Math.min(dp[t][next], dp[t - 1][prev] + 1);
                    }
                }
            }
        }

        int answer = dp[k - 1][gps_log[k - 1]] >= 100000 ? -1 : dp[k - 1][gps_log[k - 1]];
        return answer;
    }
}
