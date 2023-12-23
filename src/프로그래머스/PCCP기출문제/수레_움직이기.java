package 프로그래머스.PCCP기출문제;

import java.util.PriorityQueue;

public class 수레_움직이기 {
    public static void main(String[] args) {
        수레_움직이기 Main = new 수레_움직이기();
        int[][] maze = {{4, 3, 0, 0}, {5, 5, 5, 0}, {1, 0, 0, 0}, {2, 0, 0, 0}};
        System.out.println(Main.solution(maze));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};
    boolean[][][] visit;
    final int RED = 0;
    final int BLUE = 1;

    public int solution(int[][] maze) {
        int N = maze.length;
        int M = maze[0].length;

        Node start = new Node();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    start.redY = i;
                    start.redX = j;
                } else if (maze[i][j] == 2) {
                    start.blueY = i;
                    start.blueX = j;
                }
            }
        }
        visit = new boolean[2][N][M];
        visit[RED][start.redY][start.redX] = true;
        visit[BLUE][start.blueY][start.blueX] = true;
        int answer = dfs(start, maze, N, M);
        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }

    public int dfs(Node node, int[][] maze, int N, int M) {
        System.out.println(node);
        if (maze[node.redY][node.redX] == 3 && maze[node.blueY][node.blueX] == 4)
            return node.turn;

        int result = Integer.MAX_VALUE;
        for (int d1 = 0; d1 < 4; d1++) {
            int nRedY = node.redY + dy[d1];
            int nRedX = node.redX + dx[d1];

            if (maze[node.redY][node.redX] == 3) {
                nRedY = node.redY;
                nRedX = node.redX;
            }

            if (nRedY < 0 || nRedX < 0 || nRedY >= N || nRedX >= M || maze[nRedY][nRedX] == 5 || (maze[node.redY][node.redX] != 3 && visit[RED][nRedY][nRedX]))
                continue;

            for (int d2 = 0; d2 < 4; d2++) {
                int nBlueY = node.blueY + dy[d2];
                int nBlueX = node.blueX + dx[d2];

                if (maze[node.blueY][node.blueX] == 4) {
                    nBlueY = node.blueY;
                    nBlueX = node.blueX;
                }

                if (nBlueY < 0 || nBlueX < 0 || nBlueY >= N || nBlueX >= M || maze[nBlueY][nBlueX] == 5 || (maze[node.blueY][node.blueX] != 4 && visit[BLUE][nBlueY][nBlueX]))
                    continue;

                if (((nRedY == node.blueY && nRedX == node.blueX) && (nBlueY == node.redY && nBlueX == node.redX)) ||
                        (nRedY == nBlueY && nRedX == nBlueX)) {
                    continue;
                }


                visit[RED][nRedY][nRedX] = true;
                visit[BLUE][nBlueY][nBlueX] = true;
                result = Math.min(result, dfs(new Node(nRedY, nRedX, nBlueY, nBlueX, node.turn + 1), maze, N, M));
                visit[RED][nRedY][nRedX] = false;
                visit[BLUE][nBlueY][nBlueX] = false;
            }
        }

        return result;
    }

    public class Node {
        int redY;
        int blueY;
        int redX;
        int blueX;
        int turn;

        Node() {
        }

        Node(int redY, int redX, int blueY, int blueX, int turn) {
            this.redY = redY;
            this.redX = redX;
            this.blueY = blueY;
            this.blueX = blueX;
            this.turn = turn;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "redY=" + redY +
                    ", redX=" + redX +
                    ", blueY=" + blueY +
                    ", blueX=" + blueX +
                    ", turn=" + turn +
                    '}';
        }
    }
//    public int bfs(Node start, int[][] maze, int N, int M) {
//
//
//        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.turn - e2.turn);
//        boolean[][][] visit = new boolean[2][N][M];
//
//        final int RED = 0;
//        final int BLUE = 1;
//
//        pq.offer(start);
//        visit[RED][start.redY][start.redX] = true;
//        visit[BLUE][start.blueY][start.blueX] = true;
//
//        while (!pq.isEmpty()) {
//            Node curr = pq.poll();
//
//            System.out.println(curr);
//
//            if (maze[curr.redY][curr.redX] == 3 && maze[curr.blueY][curr.blueX] == 4)
//                return curr.turn;
//
//            if (maze[curr.redY][curr.redX] == 3) {
//                for (int d = 0; d < 4; d++) {
//                    int nBlueY = curr.blueY + dy[d];
//                    int nBlueX = curr.blueX + dx[d];
//
//                    if (nBlueY < 0 || nBlueX < 0 || nBlueY >= N || nBlueX >= M || maze[nBlueY][nBlueX] == 5 || visit[BLUE][nBlueY][nBlueX])
//                        continue;
//                    if (nBlueY == curr.redY && nBlueX == curr.redX)
//                        continue;
//
//                    pq.offer(new Node(curr.redY, curr.redX, nBlueY, nBlueX, curr.turn + 1));
//                    visit[BLUE][nBlueY][nBlueX] = true;
//                }
//            } else if (maze[curr.blueY][curr.blueX] == 4) {
//                for (int d = 0; d < 4; d++) {
//                    int nRedY = curr.redY + dy[d];
//                    int nRedX = curr.redX + dx[d];
//
//                    if (nRedY < 0 || nRedX < 0 || nRedY >= N || nRedX >= M || maze[nRedY][nRedX] == 5 || visit[RED][nRedY][nRedX])
//                        continue;
//                    if (nRedY == curr.blueY && nRedX == curr.blueX)
//                        continue;
//
//                    pq.offer(new Node(nRedY, nRedX, curr.blueY, curr.blueX, curr.turn + 1));
//                    visit[RED][nRedY][nRedX] = true;
//                }
//            } else {
//                for (int d1 = 0; d1 < 4; d1++) {
//                    int nRedY = curr.redY + dy[d1];
//                    int nRedX = curr.redX + dx[d1];
//
//                    if (nRedY < 0 || nRedX < 0 || nRedY >= N || nRedX >= M || maze[nRedY][nRedX] == 5 || visit[RED][nRedY][nRedX]) {
//                        continue;
//                    }
//
//                    for (int d2 = 0; d2 < 4; d2++) {
//                        int nBlueY = curr.blueY + dy[d2];
//                        int nBlueX = curr.blueX + dx[d2];
//                        System.out.println(nRedY + " " + nRedX + " " + nBlueY + " " + nBlueX);
//                        System.out.println(nBlueY < 0 || nBlueX < 0 || nBlueY >= N || nBlueX >= M || maze[nBlueY][nBlueX] == 5 || visit[BLUE][nBlueY][nBlueX]);
//                        if (nBlueY < 0 || nBlueX < 0 || nBlueY >= N || nBlueX >= M || maze[nBlueY][nBlueX] == 5 || visit[BLUE][nBlueY][nBlueX])
//                            continue;
//
//
//                        if ((nRedY == curr.blueY && nRedX == curr.blueX) ||
//                                (nBlueY == curr.redY && nBlueX == curr.redX) ||
//                                (nRedY == nBlueY && nRedX == nBlueX)) {
//                            continue;
//                        }
//
//
//                        pq.offer(new Node(nRedY, nRedX, nBlueY, nBlueX, curr.turn + 1));
//                        visit[RED][nRedY][nRedX] = true;
//                        visit[BLUE][nBlueY][nBlueX] = true;
//                    }
//                }
//            }
//
//
//        }
//
//        return 0;
//    }
}
