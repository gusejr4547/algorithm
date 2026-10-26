package 코드트리.ACPC2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 고속도로_배송 {
    // 일직선 고속도로
    // a에 창고 좌표
    // b에 매장 좌표
    // 고속도로 한 지점을 지나는 트럭의 수를 혼잡도
    // c[x] 는 x혼잡도인 지점의 통행 비용

    // 매장 - 창고 일대일 매치
    // 전체 통행 비용의 최솟값

    // N <= 2000
    // a, b, c <= 1 000 000 000


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] b = new int[N];
        int[] c = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        // 좌표 합쳐서 오름차순 정렬
        int[][] points = new int[N * 2][2];
        for (int i = 0; i < N; i++) {
            points[i][0] = a[i];
            points[i][1] = 0;
        }
        for (int i = 0; i < N; i++) {
            points[i + N][0] = b[i];
            points[i + N][1] = 1;
        }
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        long[][] dp = new long[N * 2 + 1][N + 1];
        long INF = Long.MAX_VALUE;
        for (long[] d : dp) {
            Arrays.fill(d, INF);
        }
        dp[0][0] = 0; // dp[x][y] x좌표까지 확인했고 현재 복잡도는 y일때 최소비용

        int storage = 0;
        int store = 0;
        for (int i = 1; i <= N * 2; i++) {
            int type = points[i - 1][1];

            if (type == 0) {
                storage++;
            } else if (type == 1) {
                store++;
            }

            int minComplex = Math.abs(storage - store);
            int maxComplex = Math.min(i, 2 * N - i);

            long dist = (i == 1) ? 0 : points[i - 1][0] - points[i - 2][0];

            for (int j = minComplex; j <= maxComplex; j += 2) {
                // 이전 혼잡도가 c-1인 경우
                if (j > 0 && dp[i - 1][j - 1] != INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist * c[j - 1]);
                }

                // 이전 혼잡도가 c+1인 경우
                if (j + 1 <= N && dp[i - 1][j + 1] != INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + dist * c[j + 1]);
                }
            }
        }

//        for (long[] d : dp) {
//            System.out.println(Arrays.toString(d));
//        }

        long answer = INF;
        for (int i = 0; i <= N; i++) {
            answer = Math.min(answer, dp[2 * N][i]);
        }

        System.out.println(answer);
    }
}
