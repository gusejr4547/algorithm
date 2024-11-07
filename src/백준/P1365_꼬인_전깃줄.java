package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1365_꼬인_전깃줄 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] LIS = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        LIS[0] = arr[0];
        int len = 1;
        for (int i = 1; i < N; i++) {
            int num = arr[i];
            if (LIS[len - 1] < num) {
                LIS[len] = num;
                len++;
            } else {
                int idx = binarySearch(LIS, len, num);
                LIS[idx] = num;
            }
        }
        System.out.println(N - len);
    }

    private static int binarySearch(int[] LIS, int len, int key) {
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (LIS[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
