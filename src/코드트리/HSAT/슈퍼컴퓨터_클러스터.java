package 코드트리.HSAT;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 슈퍼컴퓨터_클러스터 {
    static int N;
    static long B;
    static int[] performance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        B = sc.nextLong();
        performance = new int[N];
        for (int i = 0; i < N; i++) {
            performance[i] = sc.nextInt();
        }

        // 성능이 가장 낮은 컴퓨터의 성능을 최대화 하는 것이 목표
//        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
//        for (int i = 0; i < N; i++) {
//            pq.offer(performance[i]);
//        }

        long answer = 0;
        long min = 1;
        long max = 2000000000;
        while (min <= max) {
            long mid = (min + max) / 2;

            if (canChange(mid)) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canChange(long target) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if (performance[i] < target) {
                sum += (target - performance[i]) * (target - performance[i]);
            }
            if (sum > B) {
                return false;
            }
        }

        return true;
    }
}
