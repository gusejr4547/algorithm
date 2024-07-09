package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class 시소_짝꿍 {
    public static void main(String[] args) {
        시소_짝꿍 Main = new 시소_짝꿍();
        int[] weights = {100, 180, 360, 100, 270};
        System.out.println(Main.solution(weights));
    }

    // 2(m), 3(m), 4(m) 거리의 지점
    public long solution(int[] weights) {
        long answer = 0;

        int[] weightCount = new int[1001]; // weight별로 개수
        for (int i = 0; i < weights.length; i++) {
            weightCount[weights[i]]++;
        }
        int[] distinctWeights = IntStream.of(weights).distinct().toArray();
//        System.out.println(Arrays.toString(distinctWeights));

        for (int weight : distinctWeights) {
            // 같은 무게 2명 선택
            answer += (long) weightCount[weight] * (weightCount[weight] - 1) / 2;

            answer += weight * 3 % 2 == 0 && weight * 3 / 2 <= 1000 ? (long) weightCount[weight] * weightCount[weight * 3 / 2] : 0;
            answer += weight * 4 / 2 <= 1000 ? (long) weightCount[weight] * weightCount[weight * 4 / 2] : 0;
            answer += weight * 4 % 3 == 0 && weight * 4 / 3 <= 1000 ? (long) weightCount[weight] * weightCount[weight * 4 / 3] : 0;
        }

        return answer;
    }
}
