package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 도넛과_막대_그래프 {
    public static void main(String[] args) {
        도넛과_막대_그래프 Main = new 도넛과_막대_그래프();
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}, {4, 16}};
        System.out.println(Arrays.toString(Main.solution(edges)));
    }

    List<Integer>[] adj;
    int[] inEdge;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // 임의의 정점 => 나가는 간선이 2이상이고 들어오는 간선이 없음
        // 막대 => 나가는 간선이 없는 노드 1개 존재
        // 도넛 => 모든 노드 간선 나가는거 들어오는거 1개존재
        // 8자 => 1개 노드 들어오는거 2개 나가는거 2개, 나머지 노드 들어오는거 1개 나가는거 1개

        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        inEdge = new int[maxNode + 1];
        adj = new List[maxNode + 1];
        for (int i = 0; i <= maxNode; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            inEdge[edge[1]]++;
        }

        int startNode = findStartNode();
        answer[0] = startNode;
        int totalGraph = adj[startNode].size();

//        removeEdge(startNode); // 지우면 원래 연결 안된 노드랑 구분이 안됨.

//        System.out.println(Arrays.toString(inEdge));


        for (int i = 1; i <= maxNode; i++) {
            if (i == startNode) continue;
            // 막대
            if (adj[i].isEmpty() && inEdge[i] >= 1) {
                answer[2]++;
//                System.out.println(i);
            }
            // 8자
            else if (adj[i].size() >= 2 && inEdge[i] >= 2) {
                answer[3]++;
//                System.out.println(i);
            }
        }

        // 도넛
        answer[1] = totalGraph - answer[2] - answer[3];

        return answer;
    }

    private void removeEdge(int startNode) {
        for (int node : adj[startNode]) {
            inEdge[node]--;
        }
    }

    private int findStartNode() {
        for (int i = 1; i < adj.length; i++) {
            if (adj[i].size() >= 2 && inEdge[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
