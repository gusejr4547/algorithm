package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 기능개발 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        int N = progresses.length;
        int[] need = new int[N];
        for (int i = 0; i < N; i++) {
            need[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }

        int d = need[0];
        int count = 1;

        for (int i = 1; i < N; i++) {
            if (need[i] <= d) {
                count++;
            } else {
                answer.add(count);
                count = 1;
                d = need[i];
            }
        }
        answer.add(count);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
