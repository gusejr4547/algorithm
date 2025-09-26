package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 우박수열_정적분 {
    public static void main(String[] args) {
        우박수열_정적분 Main = new 우박수열_정적분();
        int k = 5;
        int[][] ranges = {{0, 0}, {0, -1}, {2, -3}, {3, -3}};
        System.out.println(Arrays.toString(Main.solution(k, ranges)));
    }

    public double[] solution(double k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Double> seq = new ArrayList<>();
        seq.add(k);
        while (k > 1) {
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = k * 3 + 1;
            }
            seq.add(k);
        }
        int n = seq.size() - 1;

        // 누적합 미리 구하기
        double[] prefixSum = new double[seq.size() + 1];
        for (int i = 1; i < seq.size(); i++) {
            // i-1 ~ i 까지 넓이
            double area = Math.max(seq.get(i - 1), seq.get(i));
            // 빼야할 삼각형
            area = area - (Math.abs(seq.get(i - 1) - seq.get(i)) / 2);

            prefixSum[i] = prefixSum[i - 1] + area;
        }

        for (int i = 0; i < ranges.length; i++) {
            int left = ranges[i][0];
            int right = n + ranges[i][1];

            if (left > right) {
                answer[i] = -1.0;
            } else {
                double area = prefixSum[right] - prefixSum[left];
                answer[i] = area;
            }
        }

        return answer;
    }
}
