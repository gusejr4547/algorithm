package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P11779_최소비용_구하기_2 {
    static int N, M;
    static List<Node>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[start].add(new Node(end, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];
        int[] distance = new int[N + 1];
        int[] parent = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNum = curr.num;
            int currCost = curr.cost;

            if (visit[currNum]) {
                continue;
            }
            visit[currNum] = true;

            for (Node next : adj[currNum]) {
                if (!visit[next.num] && distance[next.num] > currCost + next.cost) {
                    distance[next.num] = currCost + next.cost;
                    parent[next.num] = currNum;
                    pq.offer(new Node(next.num, distance[next.num]));
                }
            }
        }

        int nodeNum = end;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (nodeNum != 0) {
            stack.push(nodeNum);
            nodeNum = parent[nodeNum];
        }


        System.out.println(distance[end]);
        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    static class Node implements Comparable<Node> {
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
