package 프로그래머스;

import java.util.*;

public class 게임_맵_최단거리 {
    public int solution(int[][] maps) {
        int answer = 0;

        int N = maps.length;
        int M = maps[0].length;

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int[][] dist = new int[N][M];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][0] = 1;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || maps[ny][nx] == 0) {
                    continue;
                }

                if (dist[ny][nx] > dist[cur[0]][cur[1]] + 1) {
                    dist[ny][nx] = dist[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

        // for(int[] d : dist){
        //     System.out.println(Arrays.toString(d));
        // }

        return dist[N - 1][M - 1] == Integer.MAX_VALUE ? -1 : dist[N - 1][M - 1];
    }
}
