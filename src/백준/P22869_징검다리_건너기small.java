package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// i -> j로 이동 : (j-i)*(1 + |Ai - Aj|) 힘
public class P22869_징검다리_건너기small {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] dp = new boolean[N];
        Arrays.fill(dp, false);
        dp[0] = true;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // j->i로 이동
                int str = (i - j) * (1 + Math.abs(arr[j] - arr[i]));
                if (dp[j] && str <= K) {
                    // 이동 가능
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.println(dp[N - 1] ? "YES" : "NO");
    }
}
