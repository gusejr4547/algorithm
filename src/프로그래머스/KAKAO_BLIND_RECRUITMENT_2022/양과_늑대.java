package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2022;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class 양과_늑대 {
    public static void main(String[] args) {
        양과_늑대 Main = new 양과_늑대();
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edge = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(Main.solution(info, edge));
    }

    public int solution(int[] info, int[][] edges) {
        int N = info.length;
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            adj[parent].add(child);
        }

        // 늑대가 더 많아 방문하지 못했다고 하더라도, 그 노드를 살려두어 나중에라도 방문할 수 있게 끔
        List<Integer> nextNodes = new ArrayList<>(adj[0]);

        return dfs(0, 0, 0, nextNodes, info, adj);
    }

    public int dfs(int sheep, int wolf, int curr, List<Integer> nextNodes, int[] info, List<Integer>[] adj) {
        if (info[curr] == 0)
            sheep++;
        else
            wolf++;

        int answer = sheep;
        if(sheep <= wolf)
            return answer;

        for (int i = 0; i < nextNodes.size(); i++) {
            int nextNode = nextNodes.get(i);
            List<Integer> temp = new ArrayList<>(nextNodes);
            temp.remove(Integer.valueOf(nextNode));
            temp.addAll(adj[nextNode]);
            answer = Math.max(answer, dfs(sheep, wolf, nextNode, temp, info, adj));
        }

        return answer;
    }

}
