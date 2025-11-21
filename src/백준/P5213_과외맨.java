package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P5213_과외맨 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] tileNumGrid = new int[N + 1][N * 2 + 1];
        int[][] grid = new int[N + 1][N * 2 + 1];

        int tileNum = 1;
        for (int r = 1; r <= N; r++) {
            // 홀수 줄 N개
            if (r % 2 == 1) {
                for (int c = 1; c <= N * 2; c += 2) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    grid[r][c + 1] = Integer.parseInt(st.nextToken());

                    tileNumGrid[r][c] = tileNum;
                    tileNumGrid[r][c + 1] = tileNum;

                    tileNum++;
                }
            }
            // 짝수 N-1개
            else {
                for (int c = 2; c <= (N - 1) * 2; c += 2) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    grid[r][c + 1] = Integer.parseInt(st.nextToken());

                    tileNumGrid[r][c] = tileNum;
                    tileNumGrid[r][c + 1] = tileNum;

                    tileNum++;
                }
            }
        }

        // 인접한 타일로 이동 할때는 인접한 타일의 수가 같아야 이동가능.
        // 첫줄 첫타일 -> 마지막줄 마지막타일
        // 마지막 줄의 마지막 타일로 이동할 수 없는 경우가 존재 => 번호가 가장 큰 타일 이동

        int maxTileNum = 1;
        int lastTileNum = N * N - N / 2;
        boolean[][] visit = new boolean[N + 1][N * 2 + 1];
        int[] parents = new int[lastTileNum + 1]; // 이전 방문 타일 기록

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 1));
        queue.offer(new Node(1, 2));
        visit[1][1] = true;
        visit[1][2] = true;
        parents[1] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 최대 타일 번호 갱신
            if (maxTileNum < tileNumGrid[cur.y][cur.x]) {
                maxTileNum = tileNumGrid[cur.y][cur.x];
            }

            // 제일 마지막 타일에 도달한 경우..
            if (tileNumGrid[cur.y][cur.x] == lastTileNum) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 1 || nx < 1 || ny > N || nx > N * 2 || grid[ny][nx] == 0 || visit[ny][nx]) {
                    continue;
                }

                // 같은 값이어야 타일간 이동
                if (grid[cur.y][cur.x] != grid[ny][nx]) {
                    continue;
                }

                // (ny, nx) 큐에 넣기
                queue.offer(new Node(ny, nx));
                visit[ny][nx] = true;

                // (ny,nx)를 포함하는 타일의 다른 부분도 추가
                if (nx + 1 <= N * 2 && tileNumGrid[ny][nx] == tileNumGrid[ny][nx + 1]) {
                    queue.offer(new Node(ny, nx + 1));
                    visit[ny][nx + 1] = true;
                }
                if (nx - 1 >= 1 && tileNumGrid[ny][nx] == tileNumGrid[ny][nx - 1]) {
                    queue.offer(new Node(ny, nx - 1));
                    visit[ny][nx - 1] = true;
                }

                // 길
                parents[tileNumGrid[ny][nx]] = tileNumGrid[cur.y][cur.x];
            }
        }

        // 길 찾기
        tileNum = maxTileNum;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(tileNum);
        while (tileNum != 1) {
            tileNum = parents[tileNum];
            stack.push(tileNum);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(stack.size()).append("\n");

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}