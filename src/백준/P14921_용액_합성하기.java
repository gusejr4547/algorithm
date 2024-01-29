package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14921_용액_합성하기 {
    static int N;
    static int[] solution;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        solution = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solution);

        int left = 0;
        int right = N - 1;
        int result = Integer.MAX_VALUE;
        while (left < right) {
            int sum = solution[left] + solution[right];
            if (Math.abs(result) > Math.abs(sum)) {
                result = sum;
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(result);
    }
}
