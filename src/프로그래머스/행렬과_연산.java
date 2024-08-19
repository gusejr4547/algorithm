package 프로그래머스;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;

public class 행렬과_연산 {
    public static void main(String[] args) {
        행렬과_연산 Main = new 행렬과_연산();
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] operations = {"Rotate", "ShiftRow"};
        System.out.println(Arrays.deepToString(Main.solution(rc, operations)));
    }

    // ShiftRow : 모든 행을 i => i+1 로, 마지막행은 1행이됨
    // Rotate : 제일 바깥쪽 시계방향 1칸 회전

    // 5x5 이면 이렇게 7 덩어리로 나눔
    /*
    1 / 1 1 1 / 1
    2 / 2 2 2 / 2
    3 / 3 3 3 / 3
    4 / 4 4 4 / 4
    5 / 5 5 5 / 5
     */
    ArrayDeque<Integer> leftSide;
    ArrayDeque<Integer> rightSide;
    ArrayDeque<ArrayDeque<Integer>> mid;

    public int[][] solution(int[][] rc, String[] operations) {
        int R = rc.length;
        int C = rc[0].length;
        leftSide = new ArrayDeque<>();
        rightSide = new ArrayDeque<>();
        mid = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int j = 0; j < C; j++) {
                if (j == 0) {
                    leftSide.offer(rc[i][j]);
                } else if (j == C - 1) {
                    rightSide.offer(rc[i][j]);
                } else {
                    queue.offer(rc[i][j]);
                }
            }
            mid.offer(queue);
        }

        for (String operation : operations) {
            if ("ShiftRow".equals(operation)) {
                shift();
            } else if ("Rotate".equals(operation)) {
                rotate();
            }
        }

        int[][] answer = new int[R][C];
        for (int i = 0; i < R; i++) {
            int j = 0;
            answer[i][j] = leftSide.pollFirst();
            j++;
            ArrayDeque<Integer> queue = mid.pollFirst();
            while (!queue.isEmpty()) {
                answer[i][j] = queue.pollFirst();
                j++;
            }
            answer[i][j] = rightSide.pollFirst();
        }

        return answer;
    }

    private void shift() {
        mid.offerFirst(mid.pollLast()); // 뒤에꺼 빼서 앞으로 이동
        leftSide.offerFirst(leftSide.pollLast());
        rightSide.offerFirst(rightSide.pollLast());
    }

    private void rotate() {
        // left.앞 -> mid.앞.앞,
        // mid.앞.뒤 -> right.앞,
        // right.뒤 -> mid.뒤.뒤,
        // mid.뒤.앞 -> left.뒤
        mid.peekFirst().offerFirst(leftSide.pollFirst());
        rightSide.offerFirst(mid.peekFirst().pollLast());
        mid.peekLast().offerLast(rightSide.pollLast());
        leftSide.offerLast(mid.peekLast().pollFirst());
    }
}
