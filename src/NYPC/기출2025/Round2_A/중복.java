package NYPC.기출2025.Round2_A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 중복 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 최소한의 연산으로 수열 A에서 어떤 값 X가 K번 이상 등장하도록 만드는 프로그램을 작성하라.
        // K번 이상 등장하는 값이 2개 이상이어도 상관 없다.
        // 최소 연산 횟수 출력

        // 정렬
        Arrays.sort(arr);

        long[] prefixSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= N - K; i++) {
            int midIdx = (i + K - 1 + i) / 2;
            // 누적합을 이용
            long leftSum = prefixSum[midIdx + 1] - prefixSum[i];
            long rightSum = prefixSum[i + K] - prefixSum[midIdx + 1];

            long target = arr[midIdx];
            // left는 target - left
            // right는 right - target
            long opCount = target * (midIdx + 1 - i) - leftSum;
            opCount += rightSum - target * (i + K - midIdx - 1);

            answer = Math.min(answer, opCount);
        }

        System.out.println(answer);
    }
}
