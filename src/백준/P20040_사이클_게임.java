package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P20040_사이클_게임 {
    static int n, m;
    static List<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int count = 1;
        for (Edge edge : edgeList) {
            if (find(edge.node1) != find(edge.node2)) {
                union(edge.node1, edge.node2);
            } else {
                break;
            }
            count++;
        }

        if (count > edgeList.size())
            System.out.println(0);
        else System.out.println(count);
    }

    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) {
                parent[a] = b;
            } else {
                parent[b] = a;
            }
        }
    }

    static class Edge {
        int node1;
        int node2;

        public Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }
}
