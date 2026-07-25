package 프로그래머스;

import java.util.ArrayDeque;

public class 다리를_지나는_트럭 {
    public static void main(String[] args) {

    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 정해진 순서로 다리를 건너기.
        // 모든 트럭이 다리를 건너려면 최소 몇초가 걸리는가?
        int truckIdx = 0;
        int N = truck_weights.length;

        ArrayDeque<int[]> bridge = new ArrayDeque<>();
        int weightSum = 0;
        int time = 0;

        while (truckIdx < N || !bridge.isEmpty()) {
            time++;

            // 내보내기
            if (!bridge.isEmpty() && bridge.peek()[1] == time) {
                weightSum -= bridge.poll()[0];
            }

            // 트럭 올리기
            if (truckIdx < N && weightSum + truck_weights[truckIdx] <= weight) {
                weightSum += truck_weights[truckIdx];
                bridge.offer(new int[]{truck_weights[truckIdx], time + bridge_length});
                truckIdx++;
            }
        }

        return time;
    }
}
