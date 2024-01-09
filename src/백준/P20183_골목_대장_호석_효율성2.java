package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 이분탐색 + 다익스트라

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

        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            max = Math.max(max, c);

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        int answer = -1;
        int left = 0;
        // 1개 골목의 최대값
        int right = max;
        while (left <= right) {
            int maxCost = (left + right) / 2;
            long[] distance = dijkstra(start, maxCost);
//            System.out.println(Arrays.toString(distance));

            // 경로 없음
            if (distance[end] == Long.MAX_VALUE) {
                left = maxCost + 1;
            } else {
                right = maxCost - 1;
                answer = maxCost;
            }
        }
        System.out.println(answer);
    }

    public static long[] dijkstra(int start, int maxCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[N + 1];
        boolean[] visit = new boolean[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNum = curr.nodeNum;
            long currCost = curr.cost;

            if (visit[currNum]) continue;
            visit[currNum] = true;

            for (Node next : adj[currNum]) {
                if (next.cost > maxCost) continue;

                long sum = currCost + next.cost;
                if (sum > C || visit[next.nodeNum]) continue;
                if (distance[next.nodeNum] > sum) {
                    distance[next.nodeNum] = sum;
                    pq.offer(new Node(next.nodeNum, sum));
                }
            }
        }
        return distance;
    }


    public static class Node implements Comparable<Node> {
        int nodeNum;
        long cost;

        public Node(int nodeNum, long cost) {
            this.nodeNum = nodeNum;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
