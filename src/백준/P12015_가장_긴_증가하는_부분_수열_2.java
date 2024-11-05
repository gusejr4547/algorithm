package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class P12015_가장_긴_증가하는_부분_수열_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        int len = 1;

        for (int i = 1; i < N; i++) {
            int num = arr[i];

            if (dp[len - 1] < num) {
                dp[len] = num;
                len++;
            } else {
                int idx = binarySearch(dp, len, num);
                dp[idx] = num;
            }
        }

        System.out.println(len);
    }

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
