package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2611_자동차_경주 {
    static int N, M;
    static List<Node>[] adj;
    static int[] inDegree, dp, parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        dp = new int[N + 1];
        parent = new int[N + 1];
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            adj[p].add(new Node(q, r));

            if (q != 1) {
                inDegree[q]++;
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node next : adj[cur]) {
                if (dp[next.to] < dp[cur] + next.value) {
                    dp[next.to] = dp[cur] + next.value;
                    parent[next.to] = cur; // 경로 기록
                }

                if (next.to != 1) {
                    inDegree[next.to]--;
                    if (inDegree[next.to] == 0) {
                        queue.offer(next.to);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append('\n');

        // 역순으로 경로 stack에 저장
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int cur = 1;
        do {
            cur = parent[cur];
            stack.push(cur);
        } while (cur != 1);

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }

    private static class Node {
        int to, value;

        public Node(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
