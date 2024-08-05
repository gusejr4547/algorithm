package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 숫자_타자_대회 {
    public static void main(String[] args) {
        숫자_타자_대회 Main = new 숫자_타자_대회();
        String number = "1756";
        System.out.println(Main.solution(number));
    }

    // 시작 왼4, 오6
    // 제자리 가중치 1
    // 인접 가중치 2
    // 대각선 인접 가중치 3
    // 이외 가중치 최소경로 합
    int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};

    public int solution(String numbers) {
        int[][] dist = new int[10][10]; // dist[i][j] i에서 j까지 최소 cost
        for (int i = 0; i < 10; i++) {
            dijkstra(dist[i], i);
        }

//        for (int[] d : dist) {
//            System.out.println(Arrays.toString(d));
//        }

        int answer = bfs(dist, numbers);
        return answer;
    }


    private int bfs(int[][] dist, String numbers) {
        int left = 4;
        int right = 6;
        PriorityQueue<Hand> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Hand(left, right, 0, 0));
        int[][][] visitCost = new int[numbers.length()][10][10]; // idx에 오른손위치, 왼손위치 최소 cost
        for (int[][] cost : visitCost) {
            for (int[] c : cost) {
                Arrays.fill(c, Integer.MAX_VALUE);
            }
        }
        int minCost = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Hand curr = pq.poll();

            if (curr.idx == numbers.length()) {
                minCost = Math.min(minCost, curr.cost);
                continue;
            }

            int nextNum = numbers.charAt(curr.idx) - '0';
            int leftHand = curr.cost + dist[curr.left][nextNum];
            int rightHand = curr.cost + dist[curr.right][nextNum];
            // 왼손
            if (nextNum != curr.right && leftHand < visitCost[curr.idx][nextNum][curr.right]) {
                visitCost[curr.idx][nextNum][curr.right] = leftHand;
                pq.offer(new Hand(nextNum, curr.right, curr.idx + 1, leftHand));
            }
            // 오른손
            if (nextNum != curr.left && rightHand < visitCost[curr.idx][curr.left][nextNum]) {
                visitCost[curr.idx][curr.left][nextNum] = rightHand;
                pq.offer(new Hand(curr.left, nextNum, curr.idx + 1, rightHand));
            }
        }

//        for (int[] d : visitCost) {
//            System.out.println(Arrays.toString(d));
//        }

        return minCost;
    }

    private void dijkstra(int[] dist, int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] startPos = numberToPos(start);
        pq.offer(new Node(startPos[0], startPos[1], start, dist[start]));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 인접
            for (int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 3) {
                    continue;
                }
                int nextNum = posToNumber(ny, nx);
                if (nextNum >= 10) continue;
                if (dist[nextNum] > dist[curr.number] + 2) {
                    dist[nextNum] = dist[curr.number] + 2;
                    pq.offer(new Node(ny, nx, nextNum, dist[nextNum]));
                }
            }
            // 대각
            for (int d = 4; d < 8; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 3) {
                    continue;
                }
                int nextNum = posToNumber(ny, nx);
                if (nextNum >= 10) continue;
                if (dist[nextNum] > dist[curr.number] + 3) {
                    dist[nextNum] = dist[curr.number] + 3;
                    pq.offer(new Node(ny, nx, nextNum, dist[nextNum]));
                }
            }
        }
        dist[start] = 1;
    }

    private int posToNumber(int y, int x) {
        if (y == 3 && x == 1) {
            return 0;
        }
        return y * 3 + x + 1;
    }

    private int[] numberToPos(int number) {
        if (number == 0) {
            return new int[]{3, 1};
        }
        return new int[]{(number - 1) / 3, (number - 1) % 3};
    }

    private class Hand {
        int left, right, idx;
        int cost;

        public Hand(int left, int right, int idx, int cost) {
            this.left = left;
            this.right = right;
            this.idx = idx;
            this.cost = cost;
        }
    }

    private class Node {
        int y, x;
        int number;
        int cost;

        public Node(int y, int x, int number, int cost) {
            this.y = y;
            this.x = x;
            this.number = number;
            this.cost = cost;
        }
    }
}
