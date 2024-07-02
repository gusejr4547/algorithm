package 프로그래머스;

import java.util.Arrays;

public class 연속된_부분_수열의_합 {
    public static void main(String[] args) {
        연속된_부분_수열의_합 Main = new 연속된_부분_수열의_합();
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        System.out.println(Arrays.toString(Main.solution(sequence, k)));
    }

    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + sequence[i];
        }


        int[] answer = new int[2];
        int start = 0;
        int end = 1;
        int sequenceLength = Integer.MAX_VALUE;

        while (end < sum.length) {
            int sequenceSum = sum[end] - sum[start];
            if (sequenceSum < k) {
                end++;
            } else if (sequenceSum > k) {
                start++;
            } else {
                if (sequenceLength > end - start) {
                    answer[0] = start;
                    answer[1] = end - 1;
                    sequenceLength = end - start;
                }
                end++;
            }
        }

        return answer;
    }
}
