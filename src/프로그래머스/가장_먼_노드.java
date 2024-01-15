package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 가장_먼_노드 {
    public static void main(String[] args) {
        가장_먼_노드 Main = new 가장_먼_노드();
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(Main.solution(n, edge));
    }

    public int solution(int n, int[][] edge) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        int[] distance = dijkstra(n, adj);

        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                maxDistance = Math.max(distance[i], maxDistance);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == maxDistance) {
                answer++;
            }
        }

        return answer;
    }

    public int[] dijkstra(int n, List<Integer>[] adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        boolean[] visit = new boolean[n + 1];
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visit[curr.num]) continue;
            visit[curr.num] = true;

            for (int next : adj[curr.num]) {
                if (!visit[next] && distance[next] > curr.cost + 1) {
                    distance[next] = curr.cost + 1;
                    pq.offer(new Node(next, distance[next]));
                }
            }
        }
        return distance;
    }

    public class Node {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
