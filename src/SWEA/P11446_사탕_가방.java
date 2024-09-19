package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가방 안에 정확히 M개의 사탕이 들어 있어야 한다.
// 모든 가방에 들어 있는 사탕 종류의 구성이 같아야 한다.
public class P11446_사탕_가방 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int typeCnt = Integer.parseInt(st.nextToken());
            long bagMax = Long.parseLong(st.nextToken());

            long[] candy = new long[typeCnt];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < typeCnt; i++) {
                candy[i] = Long.parseLong(st.nextToken());
            }

            long min = 1;
            long max = 1_000_000_000_000_000_000L;
            long ans = 0;
            while (min <= max) {
                long mid = (min + max) / 2;

                // 가방을 mid개 만들수 있나?
                if (isValid(mid, bagMax, candy)) {
                    min = mid + 1;
                    ans = mid;
                } else {
                    max = mid - 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean isValid(long totalBagCnt, long bagMax, long[] candy) {
        int typeCnt = candy.length;

        long weight = 0;
        for (int i = 0; i < typeCnt; i++) {
            weight += candy[i] / totalBagCnt;
        }

        return bagMax <= weight;
    }
}