package 백준._20251227;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A_2026년이_기대되는_이유 {
    static int MAX = 100000;
    // N <= 10만

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[MAX + 2];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        // 에라토스테네스의 체
        for (int n = 2; n * n <= MAX; n++) {
            // n이 true 이면 n의 배수 전부 false로 변경
            if (isPrime[n]) {
                for (int i = n * n; i <= MAX; i = i + n) {
                    isPrime[i] = false;
                }
            }
        }

        int[] prefixSum = new int[MAX + 2];
        for (int n = 1; n <= MAX; n++) {
            // 테스트
            boolean special = true;

            // n+1이 소수
            if (!isPrime[n + 1]) {
                special = false;
            } else {
                // 숫자 길이
                String num = String.valueOf(n);
                // 나누어서 곱 + 1
                for (int i = 1; i <= num.length() - 1; i++) {
                    // 앞
                    int front = Integer.parseInt(num.substring(0, i));
                    // 뒤
                    int back = Integer.parseInt(num.substring(i, num.length()));

                    if (!isPrime[front * back + 1]) {
                        special = false;
                        break;
                    }
                }
            }

            if (special) {
                prefixSum[n] = prefixSum[n - 1] + 1;
            } else {
                prefixSum[n] = prefixSum[n - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(prefixSum[N]).append("\n");
        }
        System.out.println(sb);
    }
}
