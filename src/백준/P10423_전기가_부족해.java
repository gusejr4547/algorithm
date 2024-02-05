package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P10423_전기가_부족해 {
    static int N, M, K;
    static List<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            parent[idx] = -1;
        }

        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(u, v, w));
        }


        Collections.sort(edgeList, Comparator.comparingInt(e -> e.cost));

        int result = 0;
        for (Edge edge : edgeList) {
            int parent1 = find(edge.node1);
            int parent2 = find(edge.node2);
            if (parent1 != parent2) {
                union(edge.node1, edge.node2);
                result += edge.cost;

                if (isValid()) {
                    break;
                }
            }
        }

        System.out.println(result);
    }

    public static boolean isValid() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] != -1) {
                return false;
            }
        }
        return true;
    }

    public static int find(int a) {
        if (-1 == parent[a]) {
            return -1;
        }
        if (a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a == -1) {
                parent[b] = a;
            } else if (b == -1) {
                parent[a] = b;
            } else if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    static class Edge {
        int node1;
        int node2;
        int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

}
