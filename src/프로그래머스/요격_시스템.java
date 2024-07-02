package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 요격_시스템 {
    public static void main(String[] args) {
        요격_시스템 Main = new 요격_시스템();
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        System.out.println(Main.solution(targets));
    }

    public int solution(int[][] targets) {
        // 끝 점 기준으로 오름차순
//        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
//
//        for (int[] target : targets) {
//            pq.offer(new int[]{target[0], target[1]});
//        }
//
//        int answer = 0;
//        int x = -1;
//        while (!pq.isEmpty()) {
//            int[] cur = pq.poll();
//            if (x <= cur[0]) {
//                x = cur[1];
//                answer++;
////                System.out.println("cur = " + Arrays.toString(cur));
//            }
//        }

        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        int answer = 0;
        int x = 0;
        for (int i = 0; i < targets.length; i++) {
            if (x <= targets[i][0]) {
                x = targets[i][1];
                answer++;
            }
        }

        return answer;
    }
}
