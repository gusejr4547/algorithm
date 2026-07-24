package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 배달 {
    public static void main(String[] args) {

    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<int[]>[] adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] info : road) {
            adj[info[0]].add(new int[]{info[1], info[2]});
            adj[info[1]].add(new int[]{info[0], info[2]});
        }

        // 양의 가중치
        // 다익스트라
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (dist[cur[0]] < cur[1]) {
                continue;
            }

            for (int[] next : adj[cur[0]]) {
                if (dist[next[0]] > cur[1] + next[1]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], cur[1] + next[1]});
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}
