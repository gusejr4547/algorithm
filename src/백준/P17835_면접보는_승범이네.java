package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17835_면접보는_승범이네 {
    static int N, M, K;
    static List<Edge>[] revAdj;
    static int[] interviewP;
    static long[] interviewDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        adj = new List[N + 1];
        revAdj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
//            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
//            adj[U].add(new Edge(V, C));
            revAdj[V].add(new Edge(U, C));
        }
        st = new StringTokenizer(br.readLine());
        interviewP = new int[K];
        for (int i = 0; i < K; i++) {
            interviewP[i] = Integer.parseInt(st.nextToken());
        }

        interviewDist = new long[N + 1];
        Arrays.fill(interviewDist, Long.MAX_VALUE);
        // 면접장에서 출발해서 각도시로 도착하는 거리 계산
        // 출발점이 여러개 => 0을 새로운 출발점으로 다른 출발점 가중치 0으로 이어지는
        for (int i = 0; i < K; i++) {
            revAdj[0].add(new Edge(interviewP[i], 0));
        }
        dijkstra(0);

        // 가장 먼도시 계산
        int idx = 0;
        long distance = 0;
        for (int i = 1; i <= N; i++) {
            if (distance < interviewDist[i]) {
                idx = i;
                distance = interviewDist[i];
            }
        }

        System.out.println(idx);
        System.out.println(distance);
    }

    private static void dijkstra(int start) {
        interviewDist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (interviewDist[cur.to] < cur.weight) {
                continue;
            }

            for (Edge next : revAdj[cur.to]) {
                if (interviewDist[next.to] > interviewDist[cur.to] + next.weight) {
                    interviewDist[next.to] = interviewDist[cur.to] + next.weight;
                    pq.offer(new Edge(next.to, interviewDist[next.to]));
                }
            }
        }
    }

    private static class Edge {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}
