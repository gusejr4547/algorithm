package 코드트리;

import java.util.Scanner;

public class K개_중에_1개를_N번_뽑기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        // Please write your code here.

        selectNum(new int[n], 0, k, n);
    }

    private static void selectNum(int[] select, int selectIdx, int k, int n) {
        if (selectIdx == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < select.length; i++) {
                sb.append(select[i]).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for (int num = 1; num <= k; num++) {
            select[selectIdx] = num;
            selectNum(select, selectIdx + 1, k, n);
        }
    }
}
