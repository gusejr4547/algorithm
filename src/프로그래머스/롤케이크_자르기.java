package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;

public class 롤케이크_자르기 {
    public static void main(String[] args) {
        롤케이크_자르기 Main = new 롤케이크_자르기();
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(Main.solution(topping));
    }

    public int solution(int[] topping) {
        int answer = 0;

        int N = topping.length;

        // 양쪽 끝에서 출발해서 토핑 개수 count
        int[] leftCount = new int[N];
        int[] rightCount = new int[N];
        boolean[] type = new boolean[10001];

        leftCount[0] = 1;
        type[topping[0]] = true;
        for (int i = 1; i < N; i++) {
            leftCount[i] = leftCount[i - 1];
            if (!type[topping[i]]) {
                type[topping[i]] = true;
                leftCount[i]++;
            }
        }

        Arrays.fill(type, false);
        rightCount[N - 1] = 1;
        type[topping[N - 1]] = true;
        for (int i = N - 2; i >= 0; i--) {
            rightCount[i] = rightCount[i + 1];
            if (!type[topping[i]]) {
                type[topping[i]] = true;
                rightCount[i]++;
            }
        }

        // 한명이 0 ~ i 까지 토핑을 가져갔을 경우 공평한지 판단
        for (int i = 0; i < N - 1; i++) {
            if (leftCount[i] == rightCount[i + 1]) {
//                System.out.println(i);
                answer++;
            }
        }

        return answer;
    }
}
