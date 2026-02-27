package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P35307_하얀_전파 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Integer.parseInt(st.nextToken());
            long M = Integer.parseInt(st.nextToken());
            long L = Integer.parseInt(st.nextToken());
            long R = Integer.parseInt(st.nextToken());
            long U = Integer.parseInt(st.nextToken());
            long D = Integer.parseInt(st.nextToken());

            // 최대 넓이
            long width = 0;
            long height = 0;
            if (L != 0 || R != 0) {
                width = M;
            } else {
                width = 1;
            }
            if (U != 0 || D != 0) {
                height = N;
            } else {
                height = 1;
            }
            long area = width * height;

            // 시간
            long tWidth = L + R != 0 ? (M + L + R - 2) / (L + R) : 0;
            long tHeight = U + D != 0 ? (N + U + D - 2) / (U + D) : 0;

            long t = tHeight + tWidth;

            sb.append(area).append(' ').append(t).append('\n');
        }
        System.out.println(sb);
    }
}
