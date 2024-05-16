package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.*;

public class P123_떨어트리기 {
    public static void main(String[] args) {
        P123_떨어트리기 Main = new P123_떨어트리기();
        int[][] edges = {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}};
        int[] target = {0, 0, 0, 3, 0, 0, 5, 1, 2, 3};
        System.out.println(Arrays.toString(Main.solution(edges, target)));
    }

    public int[] solution(int[][] edges, int[] target) {
        int n = edges.length + 1;

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0] - 1].add(edge[1] - 1);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(adj[i]);
        }

        int[] pass = new int[n];
        int[] count = new int[n];

        List<Integer> leafNodeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj[i].isEmpty()) leafNodeList.add(i);
        }
        List<Integer> leafNodeOrder = new ArrayList<>();

        while (true) {
            // 숫자 넣기
            int node = 0;
            while (!adj[node].isEmpty()) {
                int nextNodeEdge = pass[node] % adj[node].size();
                pass[node]++;
                node = adj[node].get(nextNodeEdge);
            }

            count[node]++;
            leafNodeOrder.add(node);

            // 가능?
            if (count[node] > target[node]) {
                System.out.println(node);
                return new int[]{-1};
            }

            boolean isLast = true;
            for (int num : leafNodeList) {
                if (count[num] <= target[num] && target[num] <= 3 * count[num]) {
                    continue;
                } else {
                    isLast = false;
                    break;
                }
            }

            if (isLast) {
                break;
            }
        }

//        System.out.println(leafNodeOrder);
        int[] answer = new int[leafNodeOrder.size()];
        for (int i = 0; i < leafNodeOrder.size(); i++) {
            int node = leafNodeOrder.get(i);
            count[node]--;
            for (int value = 1; value <= 3; value++) {
                if (count[node] <= target[node] - value && target[node] - value <= 3 * count[node]) {
                    answer[i] = value;
                    target[node] -= value;
                    break;
                }
            }
        }

        return answer;
    }
}
