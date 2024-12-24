package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P16946_벽_부수고_이동하기_4 {
    static int N, M, blockNum;
    static char[][] map, answer;
    static int[][] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static Map<Integer, Integer> blockSizeMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        answer = new char[N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(answer[i], '0');
        }

        visit = new int[N][M];
        blockNum = 1;
        blockSizeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '1') {
                    answer[i][j] = Character.forDigit(calMoveCnt(i, j), 10);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(String.valueOf(answer[i]));
        }
    }

    private static int calMoveCnt(int y, int x) {
        int result = 1;
        Set<Integer> adjBlock = new HashSet<>();

        // 인접한 4방향 더하기
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || nx < 0 | ny >= N || nx >= M || map[ny][nx] == '1')
                continue;

            // map이 0인데 bfs안돌린거
            if (visit[ny][nx] == 0) {
                bfs(ny, nx);
                blockNum++;
            }
            adjBlock.add(visit[ny][nx]);
        }

        // blocksize 더하기
        for (int BN : adjBlock) {
            result += blockSizeMap.get(BN);
        }

        return result % 10;
    }

    private static void bfs(int y, int x) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(y, x));
        int totalSize = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visit[cur.y][cur.x] == blockNum) continue;
            visit[cur.y][cur.x] = blockNum;
            totalSize++;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 | ny >= N || nx >= M || map[ny][nx] == '1')
                    continue;

                queue.offer(new Node(ny, nx));
            }
        }

        blockSizeMap.put(blockNum, totalSize % 10);
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
