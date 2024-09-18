package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9843_촛불_이벤트 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            long N = Long.parseLong(br.readLine());

            long min = 1;
            long max = 10_000_000_000L;
            long ans = 0;
            while (min <= max) {
                long mid = (min + max) / 2;
                long need = mid * (mid + 1) / 2;

                if (N < need) {
                    max = mid - 1;
                } else if (N >= need) {
                    ans = mid;
                    min = mid + 1;
                }
            }
            long need = ans * (ans + 1) / 2;
            if (need != N) {
                ans = -1;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
