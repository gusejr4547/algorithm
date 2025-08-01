package 코드트리;

import java.util.*;

public class 특정_조건에_맞게_K개_중에_1개를_N번_뽑기 {
    static int K, N;
    static int[] select;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = sc.nextInt();
        // Please write your code here.
        sb = new StringBuilder();
        select = new int[N];
        selectNum(0);

        System.out.println(sb);
    }

    private static void selectNum(int count) {
        if (count == N) {
            for (int i = 0; i < N; i++) {
                sb.append(select[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= K; i++) {
            if (count >= 2 && i == select[count - 1] && i == select[count - 2]) {
                continue;
            }
            select[count] = i;
            selectNum(count + 1);
        }
    }
}
