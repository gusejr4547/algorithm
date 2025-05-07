package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13397_구간_나누기2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = 0;
        int max = 10000;
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (valid(mid, M, arr)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean valid(int target, int M, int[] arr) {
        int sectionCnt = 1;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            int diff = max - min;
            if (diff > target) {
                sectionCnt++;
                min = arr[i];
                max = arr[i];
            }
        }

        if (sectionCnt <= M) {
            return true;
        } else {
            return false;
        }
    }
}
