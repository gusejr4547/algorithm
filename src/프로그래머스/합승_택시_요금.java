package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 합승_택시_요금 {
    public static void main(String[] args) {
        합승_택시_요금 Main = new 합승_택시_요금();
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(Main.solution(n, s, a, b, fares));
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Node>[] adj = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            adj[fare[0]].add(new Node(fare[1], fare[2]));
            adj[fare[1]].add(new Node(fare[0], fare[2]));
        }

        // s, a, b 의 다익스트라 구해서 합이 최소가 되는 것?
        // 플로이드 워셜 - 전체 노드 최단거리 배열 구하기...
        int[] sDistance = dijkstra(s, adj, n);
        int[] aDistance = dijkstra(a, adj, n);
        int[] bDistance = dijkstra(b, adj, n);

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, sDistance[i] + aDistance[i] + bDistance[i]);
        }

        return answer;
    }

    public int[] dijkstra(int start, List<Node>[] adj, int maxNodeNum) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[maxNodeNum + 1];
        boolean[] visit = new boolean[maxNodeNum + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNum = curr.num;
            int currCost = curr.cost;

            if (visit[currNum]) continue;
            visit[currNum] = true;

            for (Node next : adj[currNum]) {
                if (!visit[next.num] && distance[next.num] > currCost + next.cost) {
                    distance[next.num] = currCost + next.cost;
                    pq.offer(new Node(next.num, distance[next.num]));
                }
            }
        }

        return distance;
    }

    public class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
