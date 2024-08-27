package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 트리_트리오_중간값 {
    public static void main(String[] args) {
        트리_트리오_중간값 Main = new 트리_트리오_중간값();
        int n = 4;
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(Main.solution(n, edges));
    }

    // 트리 형태이나까 선택한 2개 노드 공통 조상을 찾는게 정답일듯?
    List<Integer>[] adj;
//    int[] parent;
//    int[] level;

    int maxLen, maxNode;
    boolean[] visit;


    public int solution(int n, int[][] edges) {
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

//        parent = new int[n + 1];
//        level = new int[n + 1];
//        makeTree(1, 0);

        // 1번 노드에서 가장 먼 노드를 찾는다.
        maxLen = 0;
        visit = new boolean[n + 1];
        dfs(1, 0);

        System.out.println(maxNode + " : " + maxLen);
        // maxNode에서 가장 먼 노드를 찾는다. => 가장 긴 거리
        int temp = maxNode;
        maxLen = 0;
        visit = new boolean[n + 1];
        dfs(maxNode, 0);

        int start = temp;
        int end = maxNode;
        // start 제외하고 end에서 시작하는 가장 먼 노드를 찾는다.
        maxLen = 0;
        visit = new boolean[n + 1];
        visit[start] = true;
        dfs(end, 0);

        // end 제외하고 start에서 시작하는 가장 먼 노드를 찾는다.
        visit = new boolean[n + 1];
        visit[end] = true;
        dfs(start, 0);

        int answer = maxLen;
        return answer;
    }

    private void dfs(int node, int len) {
        visit[node] = true;

        if (maxLen < len) {
            maxLen = len;
            maxNode = node;
        }

        for (int next : adj[node]) {
            if (!visit[next]) {
                dfs(next, len + 1);
            }
        }
    }

//    private void makeTree(int node, int pNode) {
//        parent[node] = pNode;
//        level[node] = level[pNode] + 1;
//
//        for (int child : adj[node]) {
//            if (child == pNode) {
//                continue;
//            }
//            makeTree(child, node);
//        }
//    }
}
