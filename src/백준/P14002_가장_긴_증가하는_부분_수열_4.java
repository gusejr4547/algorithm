package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14002_가장_긴_증가하는_부분_수열_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] seq = new int[N];
        int[] LIS = new int[N];
        LIS[0] = arr[0];
        int len = 1;
        for (int i = 1; i < N; i++) {
            if (LIS[len - 1] < arr[i]) {
                LIS[len] = arr[i];
                seq[i] = len;
                len++;
            } else {
                int idx = binarySearch(LIS, arr[i], len);
                LIS[idx] = arr[i];
                seq[i] = idx;
            }
        }

//        System.out.println(Arrays.toString(LIS));
//        System.out.println(Arrays.toString(seq));

        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');

        int[] result = new int[len];
        int resultIdx = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (seq[i] == resultIdx) {
                result[resultIdx] = arr[i];
                resultIdx--;
            }
        }

        for (int i = 0; i < len; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int[] LIS, int target, int len) {
        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (LIS[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
