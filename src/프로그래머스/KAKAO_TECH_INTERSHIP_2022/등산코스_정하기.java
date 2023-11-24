package 프로그래머스.KAKAO_TECH_INTERSHIP_2022;

import java.util.*;

public class 등산코스_정하기 {
    public static void main(String[] args) {
        int n = 7;
        int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
        int[] gates = {3, 7};
        int[] summits = {1, 5};
        System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int time = paths[i][2];

            adj[a].add(new Node(b, time));
            adj[b].add(new Node(a, time));
        }
        for (int i = 0; i < gates.length; i++) {
            adj[0].add(new Node(gates[i], 0));
        }

        Set<Integer> endPoint = new HashSet<>();
        for (int i = 0; i < summits.length; i++) {
            endPoint.add(summits[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] intensity = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        intensity[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visit[curr.num])
                continue;
            visit[curr.num] = true;

            if (endPoint.contains(curr.num))
                continue;

            for (Node next : adj[curr.num]) {
                int maxValue = Math.max(next.time, intensity[curr.num]);
                if (!visit[next.num] && intensity[next.num] > maxValue) {
                    intensity[next.num] = maxValue;
                    pq.offer(new Node(next.num, maxValue));
                }
            }
        }

        System.out.println(Arrays.toString(intensity));

        Arrays.sort(summits);

        int minSummit = summits[0];
        int minIntensity = intensity[minSummit];
        for (int i = 1; i < summits.length; i++) {
            if (minIntensity > intensity[summits[i]]) {
                minSummit = summits[i];
                minIntensity = intensity[minSummit];
            }
        }

        return new int[]{minSummit, minIntensity};
    }

    public static class Node implements Comparable<Node> {
        int num;
        int time;

        Node() {
        }

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", time=" + time +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
