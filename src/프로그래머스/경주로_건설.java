package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 경주로_건설 {
    public static void main(String[] args) {
        경주로_건설 Main = new 경주로_건설();
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] board2 = {
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0}
        };
        System.out.println(Main.solution(board2));
    }

    // 직선 도로 하나를 만들 때는 100원이 소요되며, 코너를 하나 만들 때는 500원이 추가로 듭니다.
    // 최소 비용을 계산
    public int solution(int[][] board) {
        return bfs(board);
    }

    private int bfs(int[][] board) {
        int N = board.length;
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.offer(new Node(0, 0, 0, -1));
        int[][][] wayCost = new int[N][N][4];
        for (int[][] cost : wayCost) {
            for (int[] c : cost) {
                Arrays.fill(c, Integer.MAX_VALUE);
            }
        }
        Arrays.fill(wayCost[0][0], 0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.y == N - 1 && cur.x == N - 1) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || board[ny][nx] == 1) {
                    continue;
                }
                int nextWayCost = cur.cost;
                if (cur.wayDir < 0) {
                    nextWayCost += 100;
                } else if (cur.wayDir < 2 && d >= 2 || cur.wayDir >= 2 && d < 2) {
                    nextWayCost += 600;
                } else {
                    nextWayCost += 100;
                }
                if (wayCost[ny][nx][d] > nextWayCost) {
                    wayCost[ny][nx][d] = nextWayCost;
                    pq.offer(new Node(ny, nx, nextWayCost, d));
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            minCost = Math.min(minCost, wayCost[N - 1][N - 1][d]);
        }
        return minCost;
    }

    private class Node {
        int y, x, cost;
        int wayDir;

        public Node(int y, int x, int cost, int wayDir) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.wayDir = wayDir;
        }
    }

}
