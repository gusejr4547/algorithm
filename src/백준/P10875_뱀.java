package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P10875_뱀 {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        List<Line> lineList = new ArrayList<>();
        lineList.add(new Line(-L - 1, -L - 1, -L - 1, L + 1));
        lineList.add(new Line(L + 1, -L - 1, L + 1, L + 1));
        lineList.add(new Line(-L - 1, -L - 1, L + 1, -L - 1));
        lineList.add(new Line(-L - 1, L + 1, L + 1, L + 1));

        // 시작 중앙, 뱀의 머리는 격자판의 오른쪽
        int y = 0;
        int x = 0;
        int direction = 0;

        long result = 0;

        for (int i = 0; i <= N; i++) {
            int t = 0;
            char dir = 'L';
            if (i == N) {
                t = Integer.MAX_VALUE;
                dir = 'L';
            } else {
                StringTokenizer st = new StringTokenizer(br.readLine());
                t = Integer.parseInt(st.nextToken());
                dir = st.nextToken().toCharArray()[0];
            }

            int minMove = Integer.MAX_VALUE;
            for (Line line : lineList) {
                if ("HORIZONTAL".equals(line.direction)) {
                    if (direction == 0) {
                        if (y == line.y1 && x < line.x1) {
                            minMove = Math.min(minMove, line.x1 - x);
                        }
                    } else if (direction == 1) {
                        if (line.x1 <= x && x <= line.x2 && line.y1 < y) {
                            minMove = Math.min(minMove, y - line.y1);
                        }
                    } else if (direction == 2) {
                        if (y == line.y1 && line.x2 < x) {
                            minMove = Math.min(minMove, x - line.x2);
                        }
                    } else if (direction == 3) {
                        if (line.x1 <= x && x <= line.x2 && y < line.y1) {
                            minMove = Math.min(minMove, line.y1 - y);
                        }
                    }
                } else {
                    if (direction == 0) {
                        if (line.y1 <= y && y <= line.y2 && x < line.x1) {
                            minMove = Math.min(minMove, line.x1 - x);
                        }
                    } else if (direction == 1) {
                        if (line.x1 == x && line.y2 < y) {
                            minMove = Math.min(minMove, y - line.y2);
                        }
                    } else if (direction == 2) {
                        if (line.y1 <= y && y <= line.y2 && line.x1 < x) {
                            minMove = Math.min(minMove, x - line.x1);
                        }
                    } else if (direction == 3) {
                        if (line.x1 == x && y < line.y1) {
                            minMove = Math.min(minMove, line.y1 - y);
                        }
                    }
                }
            }

            // 닫는 최소 거리보다 이동거리가 적음
            if (t < minMove) {
                lineList.add(new Line(y, x, y + dy[direction] * t, x + dx[direction] * t));
                y = y + dy[direction] * t;
                x = x + dx[direction] * t;
                result += t;
                if (dir == 'R') {
                    direction = (direction + 1) % 4;
                } else if (dir == 'L') {
                    direction = (direction + 3) % 4;
                }
            } else {
                result += minMove;
                break;
            }
        }

        System.out.println(result);
    }

    static class Line {
        int y1, y2, x1, x2;
        String direction;

        public Line(int y1, int x1, int y2, int x2) {
            this.y1 = y1;
            this.y2 = y2;
            this.x1 = x1;
            this.x2 = x2;

            if (x1 == x2) {
                direction = "VERTICAL";
            } else {
                direction = "HORIZONTAL";
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
