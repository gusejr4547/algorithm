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
        int N = jobs.length;
        // jobs[i][0] - 요청 시점 [1] - 소요시간

        PriorityQueue<Task> waiting = new PriorityQueue<>((o1, o2) -> {
            if (o1.taskTime == o2.taskTime) {
                if (o1.startTime == o2.startTime) {
                    return Integer.compare(o1.id, o2.id);
                }
                return Integer.compare(o1.startTime, o2.startTime);
            }
            return Integer.compare(o1.taskTime, o2.taskTime);
        });

        // 모든 요청 작업의 반환 시간의 평균?
        boolean[] isWaiting = new boolean[N];
        int answer = 0;
        int time = 0;
        int count = 0;

        while (count < N) {
            // 현재 시간에 맞는거 넣기
            for (int i = 0; i < N; i++) {
                if (!isWaiting[i] && jobs[i][0] <= time) {
                    waiting.offer(new Task(i, jobs[i][0], jobs[i][1]));
                    isWaiting[i] = true;
                }
            }

            // 대기하는 작업 없으면 다음 시간으로
            if (waiting.isEmpty()) {
                time += 1;
            }
            // 작업 하나 골라서 시작
            else {
                Task task = waiting.poll();
                answer += task.taskTime + time - task.startTime;
                time += task.taskTime;
                count++;
            }
        }
        return answer / N;
    }

    private class Task {
        int id, startTime, taskTime;

        public Task(int id, int startTime, int taskTime) {
            this.id = id;
            this.startTime = startTime;
            this.taskTime = taskTime;
        }
    }

//    public int solution(int[][] jobs) {
//        // 최적은 수행시간 짧은거 먼저 하는거?
//        int size = jobs.length;
//
//        // 요청시간 순서로 정렬
//        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
//
//        // PriorityQueue 사용
//        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> o1.processingTime - o2.processingTime);
//
//        int endTime = 0;
//        int answer = 0;
//        int idx = 0;
//        int count = 0;
//        while (count < size) {
//            while (idx < size && jobs[idx][0] <= endTime) {
//                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
//                idx++;
//            }
//
//            if (pq.isEmpty()) {
//                endTime = jobs[idx][0];
//            } else {
//                // 다음 작업 수행
//                Job cur = pq.poll();
//                answer += cur.processingTime + endTime - cur.requestTime;
//                endTime += cur.processingTime;
//                count++;
//            }
//        }
//
//        return answer / size;
//    }
//
//    public class Job {
//        int requestTime;
//        int processingTime;
//
//        public Job(int requestTime, int processingTime) {
//            this.requestTime = requestTime;
//            this.processingTime = processingTime;
//        }
//
//        @Override
//        public String toString() {
//            return "Job{" +
//                    "requestTime=" + requestTime +
//                    ", processingTime=" + processingTime +
//                    '}';
//        }
//    }
}
