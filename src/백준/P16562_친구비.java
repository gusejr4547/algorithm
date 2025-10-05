package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P16562_친구비 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] friendMoney = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            friendMoney[i] = Integer.parseInt(st.nextToken());
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        // 친구의 친구는 친구다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // v, w 친구
            union(v, w);
        }

        int answer = 0;
        // 친구비 작은거 부터 확인
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.value, o2.value));
        for (int i = 1; i <= N; i++) {
            pq.offer(new Node(i, friendMoney[i]));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (find(cur.idx) != 0) {
                union(0, cur.idx);
                answer += cur.value;
            }
        }

        System.out.println(answer <= k ? answer : "Oh no");
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parents[b] = a;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static class Node {
        int idx, value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

}
