package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11664_선분과_점 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A
        int ax = Integer.parseInt(st.nextToken());
        int ay = Integer.parseInt(st.nextToken());
        int az = Integer.parseInt(st.nextToken());
        // B
        int bx = Integer.parseInt(st.nextToken());
        int by = Integer.parseInt(st.nextToken());
        int bz = Integer.parseInt(st.nextToken());
        // C
        int cx = Integer.parseInt(st.nextToken());
        int cy = Integer.parseInt(st.nextToken());
        int cz = Integer.parseInt(st.nextToken());

        // 선분과 점사이의 거리는 2차함수 형태
        // 삼분탐색 => 2차함수 형태에서 사용가능...
        double lx = ax, ly = ay, lz = az;
        double rx = bx, ry = by, rz = bz;

        // 100번정도 반복해서 수렴하게 만듬
        for (int i = 0; i < 100; i++) {
            // 구간을 3등분 하는 점 2개
            double p1x = lx + (rx - lx) / 3.0;
            double p1y = ly + (ry - ly) / 3.0;
            double p1z = lz + (rz - lz) / 3.0;

            double p2x = rx - (rx - lx) / 3.0;
            double p2y = ry - (ry - ly) / 3.0;
            double p2z = rz - (rz - lz) / 3.0;

            double dist1 = getDist(p1x, p1y, p1z, cx, cy, cz);
            double dist2 = getDist(p2x, p2y, p2z, cx, cy, cz);

            if (dist1 < dist2) {
                // dist1이 더 가까움 => p2~right구간 버림
                rx = p2x;
                ry = p2y;
                rz = p2z;
            } else {
                // 반대
                lx = p1x;
                ly = p1y;
                lz = p1z;
            }
        }

        System.out.println(getDist(cx, cy, cz, lx, ly, lz));
    }

    private static double getDist(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

}
