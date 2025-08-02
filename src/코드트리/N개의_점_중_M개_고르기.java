package 코드트리;

import java.util.Scanner;

public class N개의_점_중_M개_고르기 {
    static int n, m, answer;
    static int[] select;
    static int[][] points;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }
        // Please write your code here.
        answer = Integer.MAX_VALUE;
        select = new int[m];
        comb(0, 0);
        System.out.println(answer);
    }

    private static void comb(int idx, int pIdx) {
        if (idx == m) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    max = Math.max(max, calDist(points[select[i]], points[select[j]]));
                }
            }

            answer = Math.min(answer, max);
            return;
        }

        for (int i = pIdx; i < n; i++) {
            select[idx] = i;
            comb(idx + 1, i + 1);
        }
    }


    private static int calDist(int[] p1, int[] p2) {
        return (int) Math.pow((p1[0] - p2[0]), 2) + (int) Math.pow((p1[1] - p2[1]), 2);
    }
}
