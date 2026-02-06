package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16947_서울_지하철_2호선 {
    static int N;
    static List<Integer>[] adj;
    static boolean[] isCycle, visit;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        // cycle 찾기
        visit = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        dfs(1, -1);

        // 거리 계산
        dist = new int[N + 1];
        bfs();

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(dist[i]).append(' ');
        }
        System.out.println(sb);
    }

    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Arrays.fill(dist, -1);

        // 순환선에 있는거 전부 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (!isCycle[i]) continue;
            queue.offer(i);
            dist[i] = 0;
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : adj[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    private static int dfs(int cur, int prev) {
        visit[cur] = true;

        for (int next : adj[cur]) {
            // 방문한적 있음 && prev가 아님 => 사이클
            if (visit[next] && next != prev) {
                // next가 cycle 시작점.
                isCycle[next] = true;
                isCycle[cur] = true;
                // next가 cycle 시작점임을 알려줌
                return next;
            }

            // 방문 안한곳
            if (!visit[next]) {
                int result = dfs(next, cur);
                // result > 0 이면 사이클 시작점 찾음
                if (result > 0) {
                    // 다음 탐색에서 cycle을 형성했기 때문에 cur도 cycle에 포함.
                    isCycle[cur] = true;

                    // cur이 cycle 시작점? => cycle 종료
                    if (cur == result) {
                        return -1;
                    } else {
                        return result; // 계속 위로 전달
                    }
                }
            }
        }
        return -1;
    }
}
