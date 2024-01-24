package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;

public class 섬_연결하기 {
    public static void main(String[] args) {
        섬_연결하기 Main = new 섬_연결하기();
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        System.out.println(Main.solution(n, costs));
    }

    // 최소 신장 트리 (MST)
    // 크루스칼
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(e -> e[2]));
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int answer = kruskal(costs, parents);
        return answer;
    }

    public int kruskal(int[][] costs, int[] parents) {
        int result = 0;
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int cost = costs[i][2];

            if (find(a, parents) != find(b, parents)) {
                union(a, b, parents);
                result += cost;
            }
        }

        return result;
    }

    public int find(int node, int[] parents) {
        if (parents[node] == node)
            return node;
        return parents[node] = find(parents[node], parents);
    }

    public void union(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x != y) {
            if (x > y) {
                parents[x] = y;
            } else {
                parents[y] = x;
            }
        }
    }
}
