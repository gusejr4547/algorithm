package 프로그래머스;

import java.util.Arrays;

public class 최고의_집합 {
    public static void main(String[] args) {
        최고의_집합 Main = new 최고의_집합();
        System.out.println(Arrays.toString(Main.solution(2, 9)));
    }

    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (n > s) {
            return new int[]{-1};
        }

        for (int i = 0; i < n; i++) {
            answer[i] = s / n;
        }
        for (int i = 0; i < s % n; i++) {
            answer[i]++;
        }

        Arrays.sort(answer);

        return answer;
    }
}
