package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16118_달빛_여우 {

    static int N, M;
    static List<Node>[] adj;
    static int[] FoxDist;
    static int[][] WolfDist;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        FoxDist = new int[N + 1];
        WolfDist = new int[2][N + 1];

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            FoxDist[i] = INF;
            WolfDist[0][i] = INF;
            WolfDist[1][i] = INF;
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, d * 2));
            adj[b].add(new Node(a, d * 2));
        }


        FoxDijkstra(1);
        WolfDijkstra(1);

//        System.out.println(Arrays.toString(FoxDist));
//        System.out.println("##############");
//        System.out.println(Arrays.deepToString(WolfDist));

        int ans = 0;
        for (int i = 2; i <= N; i++) {
            if (FoxDist[i] < Math.min(WolfDist[0][i], WolfDist[1][i]))
                ans++;
        }

        System.out.println(ans);
    }

    private static void FoxDijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        FoxDist[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int v = curr.idx;
            int c = curr.cost;

            if (FoxDist[v] < c)
                continue;

            for (Node next : adj[v]) {
                int nv = next.idx;
                int nc = next.cost;
                int cost = FoxDist[v] + nc;
                if (FoxDist[nv] > cost) {
                    FoxDist[nv] = cost;
                    pq.offer(new Node(nv, cost));
                }
            }
        }
    }

    // 지치면 2배 느림, 안지치면 2배 빠름 => 소수점 계산 필요?
    private static void WolfDijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 1));
        WolfDist[1][start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int v = curr.idx;
            int c = curr.cost;
            int t = curr.tired;

            if (WolfDist[t][v] < c)
                continue;

            for (Node next : adj[v]) {
                int nv = next.idx;
                int nc = next.cost;
                int cost = WolfDist[t][v] + (t == 1 ? nc / 2 : nc * 2);
                if (WolfDist[1 - t][nv] > cost) {
                    WolfDist[1 - t][nv] = cost;
                    pq.offer(new Node(nv, cost, 1 - t));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;
        int tired;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public Node(int idx, int cost, int tired) {
            this.idx = idx;
            this.cost = cost;
            this.tired = tired;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {//distance가 작은 순
                return -1;
            }
            return 1;
        }
    }
}


