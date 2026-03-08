package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P5214_환승 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 하이퍼튜브를 새로운 노드로 설정
        int size = N + M;
        List<Integer>[] adj = new ArrayList[size + 1];
        for (int i = 0; i <= size; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            // 하이퍼 튜브
            int node = i + N;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                // 역
                int a = Integer.parseInt(st.nextToken());

                adj[a].add(node);
                adj[node].add(a);
            }
        }

        int[] dist = new int[size + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        dist[1] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == N) {
                break;
            }

            for (int next : adj[cur]) {
                // 방문 안함
                if (dist[next] == 0) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        if (dist[N] == 0) {
            System.out.println(-1);
        } else {
            // 하이퍼튜브 노드 방문 길이 빼야함.
            System.out.println((dist[N] + 1) / 2);
        }
    }
}
