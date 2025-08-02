package 코드트리;

import java.util.Scanner;

public class _2N개_중에_N개의_숫자를_적절하게_고르기 {
    static int n, total, answer;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        total = 0;
        for (int i = 0; i < 2 * n; i++) {
            total += arr[i];
        }

        answer = Integer.MAX_VALUE;
        comb(0, 0, 0);

        System.out.println(answer);
    }

    private static void comb(int idx, int arrIdx, int sum) {
        if (idx == n) {
            int other = total - sum;
            int diff = Math.abs(sum - other);

            answer = Math.min(answer, diff);
            return;
        }

        for (int i = arrIdx; i < 2 * n; i++) {
            comb(idx + 1, i + 1, sum + arr[i]);
        }
    }
}
