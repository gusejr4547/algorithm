package 프로그래머스;

public class N2_배열_자르기 {
    /*
    1 2 3 4
    22 3 4
    333 4
    4444 5
    ....
    */

    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left) + 1;
        int[] answer = new int[size];

        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;

            answer[idx++] = (int) Math.max(row, col) + 1;
        }
        return answer;
    }
}
