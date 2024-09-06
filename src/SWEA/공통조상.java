package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 공통조상 {
    static StringBuilder sb;
    static Node[] nodes;
    static int[] depth;
    static int[][] parents;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // A와 B의 최소 공통 조상(LCA) 찾기
            // 트리의 root는 1번
            nodes = new Node[V + 1];
            for (int i = 0; i <= V; i++) {
                nodes[i] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[p].child.add(c);
                nodes[c].parent = p;
            }
            depth = new int[V + 1];
            setDepth(1, 0);
            k = (int) (Math.log(V) / Math.log(2) + 1);
            parents = new int[V + 1][k];
            setSparseTable(V);

            int commonANC = LCA(A, B);

            int cnt = dfs(commonANC);

            sb.append("#").append(tc).append(" ").append(commonANC).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int dfs(int node) {
        int sum = 1;
        for (int next : nodes[node].child) {
            sum += dfs(next);
        }
        return sum;
    }

    private static int LCA(int a, int b) {
        // a가 상위 노드
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // depth 맞춰주기
        for (int pow = k - 1; pow >= 0; pow--) {
            if (depth[b] - depth[a] >= Math.pow(2, pow)) {
                b = parents[b][pow];
            }
        }

        if (a == b) {
            return a;
        }

        for (int pow = k - 1; pow >= 0; pow--) {
            if (parents[a][pow] != parents[b][pow]) {
                a = parents[a][pow];
                b = parents[b][pow];
            }
        }

        return parents[a][0];
    }

    private static void setSparseTable(int V) {
        for (int node = 1; node <= V; node++) {
            parents[node][0] = nodes[node].parent;
        }
        parents[1][0] = 1;

        for (int pow = 1; pow < k; pow++) {
            for (int node = 1; node <= V; node++) {
                parents[node][pow] = parents[parents[node][pow - 1]][pow - 1];
            }
        }
    }

    private static void setDepth(int nodeNum, int d) {
        depth[nodeNum] = d;

        for (int next : nodes[nodeNum].child) {
            setDepth(next, d + 1);
        }
    }

    private static class Node {
        List<Integer> child;
        int parent;

        public Node() {
            this.child = new ArrayList<>();
            this.parent = 0;
        }
    }
}
