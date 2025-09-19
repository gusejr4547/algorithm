package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 잃어버린_섬 {
    static int N, M;
    static int[] danger, inDegree;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        danger = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            danger[i] = Integer.parseInt(st.nextToken());
        }
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
        }

        inDegree = new int[N + 1];
        // 처음 방호복을 어떤거 입을껀지 하고 각각 비용 구한 뒤 최소값선택
        int cost = Math.min(getCost(0), getCost(1));
        System.out.println(cost);
    }

    private static int getCost(int safe) {
        int cost = 0;
        for (int i = 1; i <= N; i++) {
            for (int j : adj[i]) {
                inDegree[j]++;
            }
        }

        ArrayDeque<Integer>[] queue = new ArrayDeque[2]; // safe 0인 큐, 1인 큐
        for (int i = 0; i < 2; i++) {
            queue[i] = new ArrayDeque<>();
        }
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue[danger[i]].offer(i);
            }
        }

        while (true) {
            // 현재 safe한거 queue 수행 => 비용이 안듬
            while (!queue[safe].isEmpty()) {
                int cur = queue[safe].poll();
                for (int next : adj[cur]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue[danger[next]].offer(next);
                    }
                }
            }

            if (queue[0].isEmpty() && queue[1].isEmpty()) {
                break;
            }
            // 갈아입기
            safe ^= 1;
            cost++;
        }

        return cost;
    }
}
