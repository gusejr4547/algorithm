package 코드트리.ACPC2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전이_부족해 {
    static int N, M;
    static int[] weight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        weight = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 여러 동전을 균형있게 사용.
        // 가장 많이 사용되는 동전의 개수를 최소로 하는 조합.

        int answer = Integer.MAX_VALUE;
        // 이진 탐색 활용
        int min = 1;
        int max = M;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canMake(mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    private static boolean canMake(int limit) {
        int[][] dp = new int[N + 1][M + 1]; // i번째 동전까지 사용해서 j금액을 만들 수 있는가?
        dp[0][0] = 1;
        int[] prefixSum = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            int w = weight[i];
            int over = w * (limit + 1);

            // j돈을 만들 수 있는지는 dp[i-1][j] or dp[i-1][j-w] or ... 이거니까
            // 값을 전부 더해서 1보다 크면 만들 수 있음.
            // w 간격으로 prefixSum
            for (int j = 0; j <= Math.min(w, M); j++) {
                prefixSum[j] = dp[i - 1][j];
            }
            for (int j = w; j <= M; j++) {
                prefixSum[j] = dp[i - 1][j] + prefixSum[j - w];
            }

            // dp 계산
            for (int j = 0; j <= M; j++) {
                if (j >= over) {
                    dp[i][j] = (prefixSum[j] - prefixSum[j - over]) > 0 ? 1 : 0;
                } else {
                    dp[i][j] = prefixSum[j] > 0 ? 1 : 0;
                }
            }
        }
        return dp[N][M] > 0;
    }

}
