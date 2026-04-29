package 프로그래머스;

import java.util.*;

public class 모의고사 {
    public static void main(String[] args) {

    }

    // 3명의 숫자찍는 순서
    int[] a = {1, 2, 3, 4, 5};
    int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
    int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public int[] solution(int[] answers) {
        int N = answers.length; // 문제수
        List<Integer> result = new ArrayList<>();

        int countA = 0;
        int countB = 0;
        int countC = 0;

        for (int i = 0; i < N; i++) {
            int ans = answers[i];

            int ansA = a[i % a.length];
            int ansB = b[i % b.length];
            int ansC = c[i % c.length];

            if (ans == ansA) {
                countA++;
            }
            if (ans == ansB) {
                countB++;
            }
            if (ans == ansC) {
                countC++;
            }
        }

        int maxV = Math.max(countA, Math.max(countB, countC));

        if (maxV == countA) {
            result.add(1);
        }
        if (maxV == countB) {
            result.add(2);
        }
        if (maxV == countC) {
            result.add(3);
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
