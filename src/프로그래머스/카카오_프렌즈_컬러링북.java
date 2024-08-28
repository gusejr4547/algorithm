package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 카카오_프렌즈_컬러링북 {
    public static void main(String[] args) {
        카카오_프렌즈_컬러링북 Main = new 카카오_프렌즈_컬러링북();
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(Arrays.toString(Main.solution(m, n, picture)));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};
    boolean[][] visit;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visit[i][j]) {
                    numberOfArea++;
                    int size = coloring(i, j, picture);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                }
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int coloring(int i, int j, int[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int cnt = 0;
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(i, j));
        int color = picture[i][j];

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;
            cnt += 1;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= m || nx >= n || picture[ny][nx] != color) {
                    continue;
                }

                queue.offer(new Pos(ny, nx));
            }
        }
        return cnt;
    }


    private class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
