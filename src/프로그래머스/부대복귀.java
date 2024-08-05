package 프로그래머스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 부대복귀 {
    public static void main(String[] args) {
        부대복귀 Main = new 부대복귀();
        int n = 3;
        int[][] roads = {{1, 2}, {2, 3}};
        int[] sources = {2, 3};
        int destination = 1;
        System.out.println(Arrays.toString(Main.solution(n, roads, sources, destination)));
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(destination, 0));
        boolean[] visit = new boolean[n + 1];
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visit[curr.area]) {
                continue;
            }
            visit[curr.area] = true;

            for (int next : adj[curr.area]) {
                if (dist[next] > curr.cost + 1) {
                    dist[next] = curr.cost + 1;
                    pq.offer(new Node(next, curr.cost + 1));
                }
            }
        }

//        System.out.println(Arrays.toString(dist));

        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]] == Integer.MAX_VALUE ? -1 : dist[sources[i]];
        }

        return answer;
    }

    private class Node {
        int area;
        int cost;

        public Node(int area, int cost) {
            this.area = area;
            this.cost = cost;
        }
    }
}
