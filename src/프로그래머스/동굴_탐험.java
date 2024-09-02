package 프로그래머스;

import java.util.*;

public class 동굴_탐험 {
    public static void main(String[] args) {
        동굴_탐험 Main = new 동굴_탐험();
        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{8, 5}, {6, 7}, {4, 1}};
        System.out.println(Main.solution(n, path, order));
    }

    // 출발 0번방
    // 방문 순서 정해진거 있음
    // 먼저 방문해야 할 방과 나중에 방문할 방을 반드시 연속해서 방문해야 할 필요는 없어
    // 규칙에 맞게 모든 방을 탐험할 수 있을 지 return

    List<Integer>[] adj;

    public boolean solution(int n, int[][] path, int[][] order) {
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : path) {
            int s = p[0];
            int e = p[1];
            adj[s].add(e);
            adj[e].add(s);
        }

        boolean answer = canVisitAllRoom(n, order);
        return answer;
    }

    private boolean canVisitAllRoom(int n, int[][] order) {
        boolean[] canGo = new boolean[n];
        Arrays.fill(canGo, true);
        Map<Integer, Integer> orderMap = new HashMap<>();
        for (int[] o : order) {
            orderMap.put(o[0], o[1]);
            canGo[o[1]] = false;
        }

        boolean[] visit = new boolean[n];
        Set<Integer> ready = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        if (canGo[0]) {
            queue.offer(0);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visit[cur]) {
                continue;
            }
            visit[cur] = true;

            if (orderMap.containsKey(cur)) {
                canGo[orderMap.get(cur)] = true;
                if (ready.contains(orderMap.get(cur))) {
                    queue.offer(orderMap.get(cur));
                    ready.remove(orderMap.get(cur));
                }
            }

            for (int next : adj[cur]) {
                if (visit[next]) continue;
                if (canGo[next]) {
                    queue.offer(next);
                } else {
                    ready.add(next);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                return false;
            }
        }

        return true;
    }
}
