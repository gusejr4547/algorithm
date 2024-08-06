package 프로그래머스;

import java.util.Arrays;

public class 억억단을_외우자 {
    public static void main(String[] args) {
        억억단을_외우자 Main = new 억억단을_외우자();
        int e = 8;
        int[] starts = {1, 3, 7};
        System.out.println(Arrays.toString(Main.solution(e, starts)));
    }

    // 약수의 개수
    public int[] solution(int e, int[] starts) {

        int[] freqs = new int[e + 1]; // i 빈도수
        int[] maxValues = new int[e + 1]; // i 값에서 빈도 가장 높은 수
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                freqs[j]++;
            }
        }
//        System.out.println(Arrays.toString(freqs));

        int maxFreq = 0;
        int maxValue = 0;
        for (int i = e; i >= 1; i--) {
            if (freqs[i] >= maxFreq) {
                maxFreq = freqs[i];
                maxValue = i;
            }
            maxValues[i] = maxValue;
        }
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maxValues[starts[i]];
        }

        return answer;
    }
}
