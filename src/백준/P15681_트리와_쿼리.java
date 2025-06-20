package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15681_트리와_쿼리 {
    static List<Integer>[] adj;
    static int[] cnt;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        cnt = new int[N + 1];
        visit = new boolean[N + 1];
        makeTree(R);

        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            int answer = cnt[u];
            System.out.println(answer);
        }
    }

    private static void makeTree(int node) {
        visit[node] = true;
        cnt[node]++;

        for (int i = 0; i < adj[node].size(); i++) {
            int next = adj[node].get(i);
            if (visit[next]) continue;
            makeTree(next);
            cnt[node] += cnt[next];
        }
    }
}
