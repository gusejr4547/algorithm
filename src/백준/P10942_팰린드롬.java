package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P10942_팰린드롬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        for (int length = 2; length <= N; length++) {
            for (int start = 0; start <= N - length; start++) {
                if (length == 2 && arr[start] == arr[start + length - 1]) {
                    dp[start][start + length - 1] = true;
                }
                if (length > 2 && arr[start] == arr[start + length - 1]) {
                    dp[start][start + length - 1] = dp[start + 1][start + length - 2];
                }
            }
        }
//        for(boolean[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S - 1][E - 1] ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
