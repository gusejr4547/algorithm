package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class G_은하_충돌 {
    // N개 은하, M개 블랙홀
    // 은하, 블랙홀 단방향 워프게이트, 사이클없음
    // 처음 T = 0
    // 블랙홀 양의 정수 크기 S
    // 블랙홀에 도착하면... S 만큼 시간 지나감.
    // S만큼 시간 흐르면, 모든 은하, 블랙홀 간 거리 S만큼 줄어듬

    // 은하간 거리 0 => 소멸..
    // 블랙홀, 은하 거리 0 => 소멸..

    // 나는 1에 있고
    // G에 내 원수 있는데 G소멸시킬 수 있는지 판단..

    static int OFFSET, FULL;
    static long MAX = 9_999_999_999L;
    // 0~199,999 은 블랙홀
    // 200,001 ~ 400,000 은 은하
    static List<Edge>[] adj;
    static int N, M, E;
    static int[] S, inDegree;
    static long[] dp, minDist;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        OFFSET = M;
        FULL = M + N;

        adj = new List[FULL + 1];
        for (int i = 0; i <= FULL; i++) {
            adj[i] = new ArrayList<>();
        }

        inDegree = new int[FULL + 1];
        minDist = new long[FULL + 1];
        Arrays.fill(minDist, MAX);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) + OFFSET;
            int v = Integer.parseInt(st.nextToken()) + OFFSET;
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, w));
            inDegree[v]++;

            // 각 위치에서 다른 곳 최소거리
            minDist[u] = Math.min(minDist[u], w);
            minDist[v] = Math.min(minDist[v], w);
        }

        S = new int[FULL + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = OFFSET - 1; i >= 0; i--) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        // 내 원수는 G에 있다.
        int G = Integer.parseInt(br.readLine()) + OFFSET;

        // G에서 다른 곳 가장 가까운 거리?
        long gMinDist = minDist[G];
        if (gMinDist == MAX) {
            System.out.println("SAD");
            return;
        }

        // 1번 출발 위상정렬
        long[] dp = new long[FULL + 1]; // dp[i] i번 위치에 왔을때 최대 시간
        // 나는 1번은하
        int start = 1 + OFFSET;

        Arrays.fill(dp, -1);
        dp[start] = 0; // 실제 출발지점만 초기화
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <= FULL; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Edge edge : adj[cur]) {
                inDegree[edge.to]--;

                // dp가 -1 이 아닌것만 다음 시간 갱신 가능
                if (dp[cur] != -1) {
                    // 블랙홀? => 시간 더 큰걸로 갱신
                    if (edge.to < OFFSET) {
                        dp[edge.to] = Math.max(dp[edge.to], dp[cur] + S[edge.to]);
                    }
                    // 아니면 이전 시간 그대로, 갱신
                    else {
                        dp[edge.to] = Math.max(dp[edge.to], dp[cur]);
                    }
                }

                if (inDegree[edge.to] == 0) {
                    queue.offer(edge.to);
                }
            }
        }

        // DP에서 가장 큰 숫자 찾기
        long maxTime = 0;
        for (int i = 0; i <= FULL; i++) {
            maxTime = Math.max(maxTime, dp[i]);
        }

        if (gMinDist <= maxTime) {
            System.out.println("HAPPY");
        } else {
            System.out.println("SAD");
        }
    }

    private static class Edge {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
