package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 점_찍기 {
    public static void main(String[] args) {
        점_찍기 Main = new 점_찍기();
        System.out.println(Main.solution(1, 5));

    }

    public long solution(int k, int d) {
        long answer = 0;

        // y축에 찍히는 점 최대
        int y = 0;
        while (y <= d) {
            y += k;
        }
        y -= k;

        // x 위치를 옮겨 가며 최대 y 값을 조정
        for (int x = 0; x <= d; x += k) {
            while (y >= 0) {
                if (Math.ceil(getDistance(x, y)) <= d) {
                    answer += y / k + 1;
                    break;
                }
                y -= k;
            }
        }

        return answer;
    }

    private static double getDistance(long a, long b) {
        return Math.sqrt(a * a + b * b);
    }
}
