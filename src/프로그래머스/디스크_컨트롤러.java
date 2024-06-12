package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크_컨트롤러 {
    public static void main(String[] args) {
        디스크_컨트롤러 Main = new 디스크_컨트롤러();
        int[][] jobs = {{7, 8}, {3, 5}, {9, 6}};
        System.out.println(Main.solution(jobs));
    }

    public int solution(int[][] jobs) {
        // 최적은 수행시간 짧은거 먼저 하는거?
        int size = jobs.length;

        // 요청시간 순서로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // PriorityQueue 사용
        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> o1.processingTime - o2.processingTime);

        int endTime = 0;
        int answer = 0;
        int idx = 0;
        int count = 0;
        while (count < size) {
            while (idx < size && jobs[idx][0] <= endTime) {
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if (pq.isEmpty()) {
                endTime = jobs[idx][0];
            } else {
                // 다음 작업 수행
                Job cur = pq.poll();
                answer += cur.processingTime + endTime - cur.requestTime;
                endTime += cur.processingTime;
                count++;
            }
        }

        return answer / size;
    }

    public class Job {
        int requestTime;
        int processingTime;

        public Job(int requestTime, int processingTime) {
            this.requestTime = requestTime;
            this.processingTime = processingTime;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "requestTime=" + requestTime +
                    ", processingTime=" + processingTime +
                    '}';
        }
    }
}
