package 백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//한 학생당 최대 1개의 블록만을 사용할 수 있다.
public class P18427_함께_블록_쌓기 {
    static final int MOD = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][H + 1]; // 해당 높이 만들수 있는거 개수 저장
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= H; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            while (st.hasMoreTokens()) {
                int height = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= H; j++) {
                    if (j - height >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - height]) % MOD;
                    }
                }
            }
        }
//        for (int[] d : dp) {
//            System.out.println(Arrays.toString(d));
//        }

        System.out.println(dp[N][H]);
    }
}
