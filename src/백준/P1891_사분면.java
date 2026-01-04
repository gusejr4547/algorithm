package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1891_사분면 {
    static int d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        String target = st.nextToken();

        st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        // 좌표로 변경 좌상 (0,0)
        long[] point = convertPoint(target);
        long r = point[0];
        long c = point[1];

        // 좌표 이동
        r -= y; // y 양수 위쪽
        c += x;

        // 최종 범위
        long size = 1L << d;
        if (r >= 0 && c >= 0 && r < size && c < size) {
            System.out.println(convertQuadrant(r, c));
        } else {
            System.out.println(-1);
        }
    }

    private static String convertQuadrant(long r, long c) {
        StringBuilder sb = new StringBuilder();
        long size = 1L << d;

        for (int i = 0; i < d; i++) {
            size /= 2;

            // 1사분면
            if (r < size && c >= size) {
                sb.append(1);
                c -= size; // 좌표 갱신
            }
            // 2사분면
            else if (r < size && c < size) {
                sb.append(2);
            }
            // 3사분면
            else if (r >= size && c < size) {
                sb.append(3);
                r -= size;
            } else {
                sb.append(4);
                r -= size;
                c -= size;
            }
        }
        return sb.toString();
    }

    private static long[] convertPoint(String target) {
        long r = 0;
        long c = 0;
        long size = 1L << d;

        for (int i = 0; i < d; i++) {
            size /= 2; // 사분면 크기
            int n = target.charAt(i) - '0'; // 사분면 위치

            if (n == 1) {
                c += size;
            } else if (n == 3) {
                r += size;
            } else if (n == 4) {
                r += size;
                c += size;
            }
        }

        return new long[]{r, c};
    }

    private static void solution(String target, long x, long y) {

    }

}
