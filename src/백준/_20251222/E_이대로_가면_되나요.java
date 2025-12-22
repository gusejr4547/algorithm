package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class E_이대로_가면_되나요 {
    static int N;
    static int[] minDist;
    static List<Integer>[] revAdj;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        revAdj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            revAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            int next = Integer.parseInt(st.nextToken());
            revAdj[next].add(i);
        }

        // revAdj => 역방향 인접

        // 도착장소에서 역순으로 따라가면 그게 최소 거리 아님?

        minDist = new int[N + 1];
        Arrays.fill(minDist, -1);
        minDist[N] = 0; // 도착장소 초기화

        visit = new boolean[N + 1];

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(N, 0));
        visit[N] = true;
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            for (int next : revAdj[cur.node]) {
                if (visit[next]) {
                    continue;
                }
                visit[next] = true;
                minDist[next] = cur.dist + 1;
                queue.offer(new State(next, cur.dist + 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(minDist[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static class State {
        int node, dist;

        public State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
