package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14003_가장_긴_증가하는_부분_수열_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int[] LISIdx = new int[N];
        int len = 1;
        dp[0] = arr[0];
        LISIdx[0] = 0;
        int maxArrIdx = 0;

        for (int i = 1; i < N; i++) {
            int num = arr[i];

            if (dp[len - 1] < num) {
                dp[len] = num;
                LISIdx[i] = len;
                len++;
            } else {
                int idx = binarySearch(dp, len, num);
                dp[idx] = num;
                LISIdx[i] = idx;
            }

            if (maxArrIdx <= LISIdx[i]) {
                maxArrIdx = i;
            }
        }

        int[] result = new int[len];
        int resultIdx = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (LISIdx[i] == resultIdx) {
                result[resultIdx] = arr[i];
                resultIdx--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.length).append("\n");
        for (int n : result) {
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }

    // key가 들어갈 자리를 찾음
    private static int binarySearch(int[] arr, int len, int key) {
        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
