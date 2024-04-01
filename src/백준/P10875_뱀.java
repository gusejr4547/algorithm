package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10875_뱀 {
    static boolean[][] map;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int Max = L * 2 + 1;
        map = new boolean[Max][Max];
        int N = Integer.parseInt(br.readLine());

        // 시작 중앙, 뱀의 머리는 격자판의 오른쪽
        int y = L;
        int x = L;
        int direction = 0;
        map[y][x] = true;

        int result = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().toCharArray()[0];

            for (int time = 0; time < t; time++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                if (ny < 0 || nx < 0 || ny >= Max || nx >= Max || map[ny][nx]) {
                    System.out.println(result + 1);
                    return;
                }
                map[ny][nx] = true;
                result++;
                y = ny;
                x = nx;
            }

            if (dir == 'R') {
                direction = (direction + 1) % 4;
            } else if (dir == 'L') {
                direction = direction - 1 < 0 ? 3 : direction - 1;
            }
        }
    }

    static class Line {
        int y1, y2, x1, x2;
        String direction;

        public Line(int y1, int y2, int x1, int x2) {
            this.y1 = y1;
            this.y2 = y2;
            this.x1 = x1;
            this.x2 = x2;

            if (x1 == x2) {
                direction = "VERTICAL";
            } else {
                direction = "HORIZON";
            }
            pointSort();
        }

        private void pointSort() {
            int tmp = 0;
            if (this.x1 > this.x2) {
                tmp = this.x1;
                this.x1 = this.x2;
                this.x2 = tmp;
            }
            if (this.y1 > this.y2) {
                tmp = this.y1;
                this.y1 = this.y2;
                this.y2 = tmp;
            }
        }
    }
}
