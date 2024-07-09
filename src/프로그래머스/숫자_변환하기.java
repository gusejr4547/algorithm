package 프로그래머스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 숫자_변환하기 {
    public static void main(String[] args) {
        숫자_변환하기 Main = new 숫자_변환하기();
        int x = 10;
        int y = 40;
        int n = 5;
        System.out.println(Main.solution(x, y, n));
    }

    public int solution(int x, int y, int n) {
        int[] count = new int[y + 1];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[x] = 0;

        for (int i = x + 1; i <= y; i++) {
            // i를 만들기 위해 3가지 방법중 가장 count 작은거 선택
            int case1 = i - n >= 0 ? count[i - n] : Integer.MAX_VALUE;
            int case2 = i % 2 == 0 ? count[i / 2] : Integer.MAX_VALUE;
            int case3 = i % 3 == 0 ? count[i / 3] : Integer.MAX_VALUE;

            case1 += case1 != Integer.MAX_VALUE ? 1 : 0;
            case2 += case2 != Integer.MAX_VALUE ? 1 : 0;
            case3 += case3 != Integer.MAX_VALUE ? 1 : 0;

            count[i] = Math.min(case1, Math.min(case2, case3));
        }

        return count[y] != Integer.MAX_VALUE ? count[y] : -1;
    }
}
