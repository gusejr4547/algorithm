package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P18513_샘터 {
    static int N, K;
    static int[] houses;

    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        houses = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(answer);
    }

    public static void bfs() {
        int[] dx = {1, -1};

        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<Integer> visit = new HashSet<>();
        for (int house : houses) {
            queue.offer(new Node(house, 0));
            visit.add(house);
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.depth > 0) {
                count++;
                answer += curr.depth;
                if (count == K) {
                    return;
                }
            }

            for (int d = 0; d < 2; d++) {
                int next = curr.num + dx[d];

                if (!visit.contains(next)) {
                    queue.offer(new Node(next, curr.depth + 1));
                    visit.add(next);
                }
            }

        }

    }

    static class Node {
        int num;
        int depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
