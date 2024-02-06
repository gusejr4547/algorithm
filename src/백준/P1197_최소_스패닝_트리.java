package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1197_최소_스패닝_트리 {
    static int V, E;
    static int[] parent;
    static List<Edge> edgeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }
        edgeList = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(A, B, C));
        }

        Collections.sort(edgeList, Comparator.comparingInt(e -> e.weight));

        int result = 0;
        for (Edge edge : edgeList) {
            if (find(edge.num1) != find(edge.num2)) {
                union(edge.num1, edge.num2);
                result += edge.weight;
            }
        }
        System.out.println(result);
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    static class Edge {
        int num1;
        int num2;
        int weight;

        public Edge(int num1, int num2, int weight) {
            this.num1 = num1;
            this.num2 = num2;
            this.weight = weight;
        }
    }
}
