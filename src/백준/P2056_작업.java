package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2056_작업 {
    static List<Integer>[] adj;
    static int[] taskCostArray;
    static int[] visit;
    static ArrayDeque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        taskCostArray = new int[N + 1];
        visit = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            taskCostArray[i] = cost;
            int numberOfTask = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int task = Integer.parseInt(st.nextToken());
                adj[task].add(i);
            }
        }

        System.out.println(topologySort(1));
    }

    static int topologySort(int node) {
        List<Integer> nextNodeList = adj[node];

        int answer = 0;
        for (Integer nextNode : nextNodeList) {
            if (visit[nextNode] == 0) {
                answer = Math.max(answer, topologySort(nextNode));
            }else{
                answer = Math.max(answer, visit[nextNode]);
            }
        }
//        System.out.println(node + ", " + (taskCostArray[node] + answer));

        return taskCostArray[node] + answer;
    }
}
