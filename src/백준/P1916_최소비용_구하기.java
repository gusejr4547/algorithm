package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1916_최소비용_구하기 {

    static List<Edge>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Edge(e, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = bfs(start, end);

        System.out.println(answer);
    }

    private static int bfs(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(start, 0));
        boolean[] visit = new boolean[adj.length];

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.end == end) {
                return cur.cost;
            }

            if (visit[cur.end]) {
                continue;
            }
            visit[cur.end] = true;

            for (Edge next : adj[cur.end]) {
                if (visit[next.end]) continue;

                pq.offer(new Edge(next.end, cur.cost + next.cost));
            }
        }

        return -1;
    }

    private static class Edge {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
