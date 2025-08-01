package 코드트리;

import java.util.*;

public class _1차원_윷놀이 {
    static int n, m, k, answer;
    static int[] nums, piece;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // Please write your code here.
        piece = new int[k];
        Arrays.fill(piece, 1);
        selectPiece(0);

        System.out.println(answer);
    }

    private static void selectPiece(int count) {
        if (count == n) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                if (piece[i] >= m) {
                    sum++;
                }
            }

            answer = Math.max(answer, sum);

            return;
        }

        for (int i = 0; i < k; i++) {
            piece[i] += nums[count];
            selectPiece(count + 1);
            piece[i] -= nums[count];
        }
    }
}
