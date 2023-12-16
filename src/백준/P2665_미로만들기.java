package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P2665_미로만들기 {
    static int N;
    static char[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = bfs();

        System.out.println(answer);
    }

    public static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        pq.offer(new Node(0, 0, 0));

        boolean[][] visit = new boolean[N][N];
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.y == N - 1 && curr.x == N - 1)
                return curr.cost;

            for (int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] == '0') {
                    pq.offer(new Node(ny, nx, curr.cost + 1));
                } else {
                    pq.offer(new Node(ny, nx, curr.cost));
                }
                visit[ny][nx] = true;
            }

        }

        return 0;
    }

    static class Node {
        int y;
        int x;
        int cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}
