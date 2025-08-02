package 코드트리;

import java.util.Scanner;

public class XOR_결과_최대_만들기 {
    static int n, m, answer;
    static int[] A, select;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        // Please write your code here.
        answer = Integer.MIN_VALUE;
        select = new int[m];
        comb(0, 0);
        System.out.println(answer);
    }

    private static void comb(int idx, int aIdx) {
        if (idx == m) {
            int result = 0;
            for (int i = 0; i < m; i++) {
                result ^= select[i];
                // System.out.println(result);
            }
            // System.out.println("!!!!!!!!!!!!!!!!!!");
            answer = Math.max(answer, result);
            return;
        }

        for (int i = aIdx; i < n; i++) {
            select[idx] = A[i];
            comb(idx + 1, i + 1);
        }
    }
}
