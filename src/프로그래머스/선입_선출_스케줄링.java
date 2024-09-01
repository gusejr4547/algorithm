package 프로그래머스;

import java.util.PriorityQueue;

public class 선입_선출_스케줄링 {
    public static void main(String[] args) {
        선입_선출_스케줄링 Main = new 선입_선출_스케줄링();
        int n = 6;
        int[] cores = {1, 2, 3};
        System.out.println(Main.solution(n, cores));
    }

    public int solution(int n, int[] cores) {
        int answer = 0;

        if (cores.length >= n) {
            return n;
        }

        int min = 0;
        int max = 10000 * n;
        int time = 0;
        int work = 0;
        while (min <= max) {
            int mid = (max + min) / 2;

            int workSum = cores.length;
            for (int i = 0; i < cores.length; i++) {
                workSum += mid / cores[i];
            }

            if (workSum >= n) {
                max = mid - 1;
            }
            if (workSum < n) {
                time = mid;
                work = workSum;
                min = mid + 1;
            }
        }

//        System.out.println(time);
//        System.out.println(work);

        // 다음 time에 work = n이 달성된다.
        // 1번 코어부터 작업 들어갈 수 있는지 확인
        time += 1;
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                work++;
            }
            if (work == n) {
                answer = i + 1;
                break;
            }
        }

        return answer;
    }
}
