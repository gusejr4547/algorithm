package 코드트리.ACPC2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 독립_전력망 {
    // 일부 도로를 제거해서 다음 2조건을 만족하게 만들기
    // 1. 사이클 없게, 어떤 마을에서 출발해서 같은 도로 사용하지 않고 돌아오지 못하는것
    // 2. 각 연결 요소에는 발전소가 정확히 1개 있어야함. 연결요소는 도로로 이동 가능한 마을의 묶음.
    // 도로 제거 최소비용? 불가능 -1

    // 발전소 개수만큼 그룹을 나누어야한다.
    // MST를 구하는것.
    // union find에서 이미 연결된거 확인하면서 발전소의 유무도 확인해야한다.
    static int N, M, K;
    static int[] parents;
    static boolean[] bal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bal = new boolean[N + 1]; // 발전소 있는 마을 표시
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            bal[n] = true;
        }
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(u, v, c));
        }

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }


        // 비싼 cost를 먼저 연결해야함
        // c 오름차순으로 정렬
        edgeList.sort((o1, o2) -> Long.compare(o2.c, o1.c));

        // 큰거부터 union
        long totalCost = 0;
        for (Edge edge : edgeList) {
            boolean res = union(edge.u, edge.v);

            // 사용안한거 cost 계산
            if (!res) {
                totalCost += edge.c;
            }
        }
//        System.out.println(Arrays.toString(bal));
        // 전부 발전소 연결됐는지 확인 필요
        if (!allConnect()) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost);
        }
    }

    private static boolean allConnect() {
        for (int i = 1; i <= N; i++) {
            if (!bal[find(i)]) {
                return false;
            }
        }
        return true;
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }

        // 발전소가 둘다 있으면
        if (bal[x] && bal[y]) {
            return false;
        }

        parents[y] = x;
        bal[x] = bal[x] || bal[y];
        return true;
    }

    private static class Edge {
        int u, v;
        long c;

        public Edge(int u, int v, long c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
}
