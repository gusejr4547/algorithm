package 프로그래머스.KAKAO_TECH_INTERSHIP_2022;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 두_큐_합_같게_만들기 {
    public static void main(String[] args) {
        int[] queue1 = {1, 1, 1, 1, 1, 1};
        int[] queue2 = {1, 1, 1, 1, 11, 1};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int minimumMove = 0;

        int len = queue1.length;
        int q1Idx = 0;
        int q2Idx = 0;
        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();

        while (q1Idx < len * 2 && q2Idx < len * 2) {
            if (q1Sum == q2Sum) {
                break;
            }

            int pick = 0;
            if (q1Sum < q2Sum) {
                if (q2Idx < len) {
                    pick = queue2[q2Idx];
                } else {
                    pick = queue1[q2Idx % len];
                }
                q1Sum += pick;
                q2Sum -= pick;
                q2Idx++;
            } else {
                if (q1Idx < len) {
                    pick = queue1[q1Idx];
                } else {
                    pick = queue2[q1Idx % len];
                }
                q1Sum -= pick;
                q2Sum += pick;
                q1Idx++;
            }
            minimumMove++;
        }

        if (q1Sum == q2Sum) {
            answer = minimumMove;
        } else {
            answer = -1;
        }

        return answer;
    }
}
