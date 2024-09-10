package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 영준이의_진짜_BFS {
    static StringBuilder sb;
    static int k, N;
    static List<Integer>[] child;
    static int[][] parents;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            child = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                child[i] = new ArrayList<>();
            }

            k = (int) (Math.log(N + 1) / Math.log(2) + 1);
            parents = new int[N + 1][k];
            parents[1][0] = 1;

            depth = new int[N + 1];
            depth[1] = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= N; i++) {
                int p = Integer.parseInt(st.nextToken());
                parents[i][0] = p;
                child[p].add(i);
                depth[i] = depth[p] + 1;
            }

            setSparseTable(N);

            long result = bfs();

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long bfs() {
        long cnt = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        int prev = 1;
        boolean[] visit = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visit[cur]) continue;
            visit[cur] = true;

            for (int next : child[cur]) {
                queue.offer(next);

                int commonParent = LCA(prev, next);
                cnt += (depth[prev] - depth[commonParent]) + (depth[next] - depth[commonParent]);
                prev = next;
            }
        }

        return cnt;
    }

    private static int LCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int pow = k - 1; pow >= 0; pow--) {
            if (depth[b] - depth[a] >= (1 << pow)) {
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

    private static void setSparseTable(int n) {
        for (int pow = 1; pow < k; pow++) {
            for (int node = 1; node <= n; node++) {
                parents[node][pow] = parents[parents[node][pow - 1]][pow - 1];
            }
        }
    }
}
