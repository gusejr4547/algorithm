package 프로그래머스;

import java.util.Arrays;

public class 연속_펄스_부분_수열의_합 {
    public static void main(String[] args) {
        연속_펄스_부분_수열의_합 Main = new 연속_펄스_부분_수열의_합();
        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        System.out.println(Main.solution(sequence));
    }

    public long solution(int[] sequence) {
        int[] seqTypeA = new int[sequence.length];
        int[] seqTypeB = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            seqTypeA[i] = sequence[i] * (i % 2 == 0 ? 1 : -1);
            seqTypeB[i] = sequence[i] * (i % 2 == 1 ? 1 : -1);
        }

//        System.out.println(Arrays.toString(seqTypeA));
//        System.out.println(Arrays.toString(seqTypeB));

        // A, B 배열의 부분수열 합이 최대
        long[] cacheA = new long[sequence.length];
        long[] cacheB = new long[sequence.length];
        cacheA[0] = seqTypeA[0];
        cacheB[0] = seqTypeB[0];

        long answer = Math.max(cacheA[0], cacheB[0]);

        for (int i = 1; i < sequence.length; i++) {
            // 이전까지의 최대가 음수이면 현재값을 더하면 더 작아지기 때문에 초기화
            cacheA[i] = Math.max(0, cacheA[i - 1]) + seqTypeA[i];
            cacheB[i] = Math.max(0, cacheB[i - 1]) + seqTypeB[i];

            answer = Math.max(answer, Math.max(cacheA[i], cacheB[i]));
        }

//        System.out.println(Arrays.toString(cacheA));
//        System.out.println(Arrays.toString(cacheB));

        return answer;
    }
}
