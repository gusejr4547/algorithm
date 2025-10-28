package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P11437_LCA {
    static int N, h;
    static List<Integer>[] adj;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        // 트리 정보
        // root 1번
        depth = new int[N + 1];
        h = getMaxLevel(N);
        parent = new int[N + 1][h]; // parent[i][j] i노드에서 2^j 부모노드 번호
        init(1, 0, 1); // 부모노드 채우기
        setSparseTable();

//        for (int[] d : parent) {
//            System.out.println(Arrays.toString(d));
//        }


        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static int lca(int a, int b) {
        // depth[a] > depth[b]
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 모든 수는 2^i의 합으로 표현이 가능하다.
        // 높이 맞추기
        for (int i = h - 1; i >= 0; i--) {
            // 2^i 이상으로 두 노드의 높이 차이가 나면 2^i만큼 위로
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        // 같은 노드일 경우
        if (a == b) {
            return a;
        }

        // LCA찾기
        for (int i = h - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // 나온 결과의 부모노드가 LCA
        return parent[a][0];
    }

    private static void init(int node, int pNode, int hi) {
        depth[node] = hi;
        for (int next : adj[node]) {
            if (next != pNode) {
                parent[next][0] = node;
                init(next, node, hi + 1);
            }
        }
    }


    private static void setSparseTable() {
        for (int j = 1; j < h; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    private static int getMaxLevel(int a) {
        return (int) (Math.ceil(Math.log(a) / Math.log(2)) + 1);
    }
}
