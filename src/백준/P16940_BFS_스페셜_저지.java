package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16940_BFS_스페셜_저지 {
    static int N;
    static Set<Integer>[] adj;
    static int[] visitOrder;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new HashSet<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        visitOrder = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visitOrder[i] = Integer.parseInt(st.nextToken());
        }

        // 시작점은 1이다.
        boolean result = bfs(1);

        System.out.println(result ? 1 : 0);
    }

    private static boolean bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] visit = new boolean[N + 1];
        visit[1] = true;
        int orderIdx = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 방문하지 않은 인접한 곳 몇개?
            int count = 0;
            for (int next : adj[cur]) {
                if (!visit[next]) {
                    count++;
                }
            }

            for (int i = 0; i < count; i++) {
                int order = visitOrder[orderIdx];

                // 이미 방문한 곳?
                if (visit[order]) {
                    return false;
                }

                // adj에 있나?
                if (adj[cur].contains(order)) {
                    visit[order] = true;
                    queue.offer(order);
                } else {
                    return false;
                }

                orderIdx++;
            }
        }

        return true;
    }
}
