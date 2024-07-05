package 프로그래머스;

import java.util.Arrays;

public class 당구_연습 {
    public static void main(String[] args) {
        당구_연습 Main = new 당구_연습();
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
        System.out.println(Arrays.toString(Main.solution(m, n, startX, startY, balls)));
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int distance = Integer.MAX_VALUE;

            // 4개 벽 기준으로 좌표 뒤집기
            if (startX != targetX) {
                // 위
                int up = euclideanDistance(startX, startY, targetX, n + (n - targetY));
//                System.out.println("up = " + up);
                // 아래
                int down = euclideanDistance(startX, startY, targetX, -targetY);
//                System.out.println("down = " + down);
                distance = Math.min(distance, Math.min(up, down));
            } else {
                if (startY < targetY) {
                    // 아래
                    int down = euclideanDistance(startX, startY, targetX, -targetY);
                    distance = Math.min(distance, down);
                } else {
                    // 위
                    int up = euclideanDistance(startX, startY, targetX, n + (n - targetY));
                    distance = Math.min(distance, up);
                }
            }


            if (startY != targetY) {
                // 왼
                int left = euclideanDistance(startX, startY, -targetX, targetY);
//                System.out.println("left = " + left);
                // 오른
                int right = euclideanDistance(startX, startY, m + (m - targetX), targetY);
//                System.out.println("right = " + right);

                distance = Math.min(distance, Math.min(left, right));
            } else {
                if (startX < targetX) {
                    // 왼
                    int left = euclideanDistance(startX, startY, -targetX, targetY);
                    distance = Math.min(distance, left);
                } else {
                    // 오른
                    int right = euclideanDistance(startX, startY, m + (m - targetX), targetY);
                    distance = Math.min(distance, right);
                }
            }


            answer[i] = distance;
//            System.out.println("-----------------------");
        }

        return answer;
    }

    private int euclideanDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
    }
}
