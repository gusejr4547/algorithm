package 백준._20260102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A_자기장_측정기 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        String seq = br.readLine();

        List<State> points = new ArrayList<>();
        points.add(new State(0, 0, 0));
        int idx = 0;
        while (idx < seq.length()) {
            if ('S' == seq.charAt(idx)) {
                // 이동 범위
                int s = idx + 1;
                int e = idx + 2;
                while (e < seq.length() && seq.charAt(e) != 'S' && seq.charAt(e) != 'T') {
                    e++;
                }
                int move = Integer.parseInt(seq.substring(s, e));

                State cur = points.get(points.size() - 1);
                State next = new State(cur.x + dx[cur.dir] * move, cur.y + dy[cur.dir] * move, cur.dir);
                points.add(next);

                idx = e;
            } else if ('T' == seq.charAt(idx)) {
                // 회전
                int rotate = seq.charAt(idx + 1) - '0';
                State cur = points.get(points.size() - 1);
                cur.dir = (cur.dir + rotate) % 4;

                idx += 2;
            }
        }

//        System.out.println(points);
        // 움직임 x
        if (points.size() == 1) {
            if (x == 0 && y == 0) {
                System.out.println(-1);
            } else {
                System.out.println("0 0");
            }
            return;
        }

        int rx = 0;
        int ry = 0;
        long minDist = Long.MAX_VALUE;
        for (int i = 1; i < points.size(); i++) {
            int x1 = points.get(i - 1).x;
            int y1 = points.get(i - 1).y;
            int x2 = points.get(i).x;
            int y2 = points.get(i).y;
            // y축 평행 직선
            if (x1 == x2) {
                int tx = x1;
                int ty;
                if (y1 < y2) {
                    ty = Math.max(y1, Math.min(y, y2));
                } else {
                    ty = Math.max(y2, Math.min(y, y1));
                }
                long dist = getDist(tx, ty, x, y);
                if (minDist > dist) {
                    minDist = dist;
                    rx = tx;
                    ry = ty;
                }
            }

            // x축 평행 직선
            if (y1 == y2) {
                int ty = y1;
                int tx;
                if (x1 < x2) {
                    tx = Math.max(x1, Math.min(x, x2));
                } else {
                    tx = Math.max(x2, Math.min(x, x1));
                }
                long dist = getDist(tx, ty, x, y);
                if (minDist > dist) {
                    minDist = dist;
                    rx = tx;
                    ry = ty;
                }
            }
        }

        if (minDist == 0) {
            System.out.println(-1);
        } else {
            System.out.println(rx + " " + ry);
        }
    }

    private static long getDist(long x1, long y1, long x2, long y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    private static class State {
        int x, y, dir;

        public State(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

//        @Override
//        public String toString() {
//            return "State{" +
//                    "x=" + x +
//                    ", y=" + y +
//                    ", dir=" + dir +
//                    '}';
//        }
    }
}
