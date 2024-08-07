package 프로그래머스;

import java.util.Arrays;

public class 공_이동_시뮬레이션 {
    public static void main(String[] args) {
        공_이동_시뮬레이션 Main = new 공_이동_시뮬레이션();
        int n = 2;
        int m = 5;
        int x = 0;
        int y = 1;
        int[][] queries = {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}};
        System.out.println(Main.solution(n, m, x, y, queries));
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        int sx, ex, sy, ey;
        sx = ex = x;
        sy = ey = y;
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
//            System.out.println("query = " + Arrays.toString(query));
            int dir = query[0];
            int delta = query[1];

            if (dir == 0 || dir == 1) {
                int[] result = nextRange(sy, ey, dy[dir] * delta, m);
                if (result[0] == -1)
                    return 0;
                sy = result[0];
                ey = result[1];
            } else {
                int[] result = nextRange(sx, ex, dx[dir] * delta, n);
                if (result[0] == -1)
                    return 0;
                sx = result[0];
                ex = result[1];
            }
        }

        return (long) (ex - sx + 1) * (long) (ey - sy + 1);
    }

    private int[] nextRange(int s, int e, int move, int maxValue) {
        int nextS = (s == 0 && move > 0) ? 0 : s + move;
        int nextE = (e == maxValue - 1 && move < 0) ? maxValue - 1 : e + move;
//        System.out.println("nextS = " + nextS);
//        System.out.println("nextE = " + nextE);

        if ((nextS < 0 || nextS >= maxValue) && (nextE < 0 || nextE >= maxValue)) {
            return new int[]{-1, -1};
        }
        if (nextS < 0 && 0 <= nextE && nextE < maxValue) {
            return new int[]{0, nextE};
        }
        if (0 <= nextS && nextS < maxValue && nextE >= maxValue) {
            return new int[]{nextS, maxValue - 1};
        }
        return new int[]{nextS, nextE};
    }
}
