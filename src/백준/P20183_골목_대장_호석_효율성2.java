package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P20183_골목_대장_호석_효율성2 {
    static int N, M;
    static long C;
    static List<Node>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        System.out.println(dijkstra(start, end));
    }

    public static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        pq.offer(new Node(start, 0, 0));
        distance[start] = 0;
        boolean[] visit = new boolean[N + 1];
        visit[start] = true;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNum = curr.nodeNum;
            long currCost = curr.cost;
            long currMax = curr.maxCost;

            for (Node next : adj[currNum]) {
                if (currCost + next.cost > C || visit[next.nodeNum]) continue;
                if (distance[next.nodeNum] > Math.max(currMax, next.cost)) {
                    distance[next.nodeNum] = Math.max(currMax, next.cost);
                    visit[next.nodeNum] = true;
                    pq.offer(new Node(next.nodeNum, currCost + next.cost, distance[next.nodeNum]));
                }
            }
        }

        return distance[end] != Long.MAX_VALUE ? distance[end] : -1;
    }


    public static class Node implements Comparable<Node> {
        int nodeNum;
        long cost;
        long maxCost;

        public Node(int nodeNum, long cost) {
            this.nodeNum = nodeNum;
            this.cost = cost;
        }

        public Node(int nodeNum, long cost, long maxCost) {
            this.nodeNum = nodeNum;
            this.cost = cost;
            this.maxCost = maxCost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.maxCost, o.maxCost);
        }
    }
}
