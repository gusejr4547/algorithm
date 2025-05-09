package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2473_세_용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquid);

        long featureValue = Long.MAX_VALUE;
        int[] answer = new int[3];

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = (long) liquid[i] + liquid[left] + liquid[right];

                if (Math.abs(sum) < Math.abs(featureValue)) {
                    featureValue = sum;
                    answer[0] = liquid[i];
                    answer[1] = liquid[left];
                    answer[2] = liquid[right];
                }

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    break;
                }
            }
        }


        System.out.printf("%d %d %d", answer[0], answer[1], answer[2]);
    }
}
