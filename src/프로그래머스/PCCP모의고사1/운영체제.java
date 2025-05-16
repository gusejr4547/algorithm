package 프로그래머스.PCCP모의고사1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 운영체제 {
    public static void main(String[] args) {
        운영체제 Main = new 운영체제();
        int[][] program = {{3, 6, 4}, {4, 2, 5}, {1, 0, 5}, {5, 0, 5}};
        System.out.println(Arrays.toString(Main.solution(program)));
    }

    public long[] solution(int[][] program) {
        long[] answer = new long[11];

        Arrays.sort(program, (o1, o2) -> o1[1] == o2[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

        PriorityQueue<Process> allProgram = new PriorityQueue<>((o1, o2) ->
                o1.callTime == o2.callTime ? Integer.compare(o1.priority, o2.priority) : Long.compare(o1.callTime, o2.callTime));

        PriorityQueue<Process> waitingQueue = new PriorityQueue<>((o1, o2) ->
                o1.priority == o2.priority ? Long.compare(o1.callTime, o2.callTime) : Integer.compare(o1.priority, o2.priority));

        for (int i = 0; i < program.length; i++) {
            allProgram.offer(new Process(program[i][0], program[i][1], program[i][2]));
        }


        long curTime = 0;

        while (!allProgram.isEmpty()) {
            while (!allProgram.isEmpty() && allProgram.peek().callTime <= curTime) {
                waitingQueue.offer(allProgram.poll());
            }

            // waitingQueue에서 작업 꺼내서 수행
            if (!waitingQueue.isEmpty() && waitingQueue.peek().callTime <= curTime) {
                Process process = waitingQueue.poll();
                answer[process.priority] += curTime - process.callTime;
                curTime += process.runTime;
                continue;
            }

            curTime++;
        }

        while (!waitingQueue.isEmpty()) {
            Process process = waitingQueue.poll();
            answer[process.priority] += curTime - process.callTime;
            curTime += process.runTime;
        }

        answer[0] = curTime;

        return answer;
    }

    private static class Process {
        int priority, runTime;
        long callTime;

        public Process(int priority, long callTime, int runTime) {
            this.priority = priority;
            this.callTime = callTime;
            this.runTime = runTime;
        }
    }
}
