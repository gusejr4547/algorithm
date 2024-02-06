package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2467_용액 {
    static int N;
    static int[] liquid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        liquid = new int[N];
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        int minDiff = Integer.MAX_VALUE;
        int liq1 = 0;
        int liq2 = 0;

        int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = liquid[left] + liquid[right];

            if (minDiff > Math.abs(sum)) {
                minDiff = Math.abs(sum);
                liq1 = liquid[left];
                liq2 = liquid[right];
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break;
            }
        }

        System.out.println(liq1 + " " + liq2);
    }
}
