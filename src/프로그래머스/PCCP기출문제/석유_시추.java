package 프로그래머스.PCCP기출문제;

import java.util.*;

public class 석유_시추 {
    public static void main(String[] args) {
        석유_시추 PCCP = new 석유_시추();
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(PCCP.solution(land));
    }

    private final int[] dy = {0, 0, 1, -1};
    private final int[] dx = {1, -1, 0, 0};

    public int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        boolean[][] visit = new boolean[N][M];
        int blockIndex = 1;
        Map<Integer, Integer> blockSize = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && land[i][j] == 1) {
                    makeBlock(i, j, blockIndex, blockSize, land, visit, N, M);
                    blockIndex += 1;
                }
            }
        }

        int maxOil = 0;
        for (int j = 0; j < M; j++) {
            Set<Integer> blockIndexSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                if (land[i][j] > 0) {
                    blockIndexSet.add(land[i][j]);
                }
            }
            int oil = 0;
            for (Integer index : blockIndexSet) {
                oil += blockSize.get(index);
            }

            maxOil = Math.max(maxOil, oil);
        }

        return maxOil;
    }


    public void makeBlock(int y, int x, int blockIndex, Map<Integer, Integer> blockSize, int[][] land, boolean[][] visit, int N, int M) {
        visit[y][x] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        int count = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            count += 1;
            land[curr[0]][curr[1]] = blockIndex;

            for (int d = 0; d < 4; d++) {
                int ny = curr[0] + dy[d];
                int nx = curr[1] + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx])
                    continue;

                if (land[ny][nx] == 1) {
                    queue.offer(new int[]{ny, nx});
                    visit[ny][nx] = true;
                }
            }
        }

        blockSize.put(blockIndex, count);
    }
}
