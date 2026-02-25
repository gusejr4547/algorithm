package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P35284_신촌_방수_계획 {
    // 지붕을 설치한 통로는 비를 맞지 않고 이동할 수 있다.
    // 우산 보관함이 있는 건물에 도달하면 우산을 빌릴 수 있다.
    // 따라서 이후 어떤 통로를 지나더라도 비를 맞지 않고 이동할 수 있다. 빌린 우산을 다시 반납할 필요는 없다.
    // 설치 후에는 어떤 건물에서 출발해도 비를 맞지 않고 모든 건물로 이동 가능해야함
    // 각 건물에 우산 보관함을 설치하는 비용과 각 통로에 지붕을 설치하는 비용이 주어질 때, 필요한 최소 비용?

    // N <= 100 000
    // M <= 300 000

    static List<Edge> edgeList;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] C = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }
        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(u, v, w));
        }

        // 최소 스패닝 트리로 하는데,
        // 1. 우산 안쓰고 MST
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        edgeList.sort((o1, o2) -> o1.w - o2.w);

        long answer1 = 0;
        for (Edge edge : edgeList) {
            // u와 v가 연결되어 있지 않으면
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                answer1 += edge.w;
            }
        }

        // 2. 우산 포함 MST
        // 가상노드 0번을 추가
        // i에 우산보관함 설치 비용을 0번과 i 사이의 edge로
        // i는 0번이랑 연결되던지, 아니면 다른 곳으로 연결되던지
        for (int i = 1; i <= N; i++) {
            edgeList.add(new Edge(0, i, C[i]));
        }
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        edgeList.sort((o1, o2) -> o1.w - o2.w);

        long answer2 = 0;
        for (Edge edge : edgeList) {
            // u와 v가 연결되어 있지 않으면
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                answer2 += edge.w;
            }
        }

        System.out.println(Math.min(answer1, answer2));
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parents[y] = x;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
