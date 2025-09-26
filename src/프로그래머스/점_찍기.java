package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 점_찍기 {
    public static void main(String[] args) {
        점_찍기 Main = new 점_찍기();
        System.out.println(Main.solution(1, 5));

    }

    public long solution(int k, long d) {
        long answer = 0;

        for (long x = 0; x <= d; x += k) {
            long maxY = (long) Math.sqrt(d * d - x * x);
            answer += maxY/k + 1;
        }

        return answer;
    }
}
