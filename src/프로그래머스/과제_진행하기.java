package 프로그래머스;

import java.util.*;

public class 과제_진행하기 {
    public static void main(String[] args) {
        과제_진행하기 Main = new 과제_진행하기();
//        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"},
                {"computer", "12:30", "100"}};
        System.out.println(Arrays.toString(Main.solution(plans)));
    }

    public String[] solution(String[][] plans) {
        // plans = [name, start, playtime]
        int n = plans.length;
        String[] answer = new String[n];

        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            String name = plans[i][0];
            int start = getHourToMinute(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);

            tasks[i] = new Task(name, start, playtime);
        }

        // 정렬
        Arrays.sort(tasks);

        // 작업 큐
        ArrayDeque<Task> taskQueue = new ArrayDeque<>();

        int answerIdx = 0;
        int curTime = 0;

        for (int i = 0; i < n; i++) {
            Task cur = tasks[i];

            while (!taskQueue.isEmpty() && curTime + taskQueue.peek().playtime <= cur.start) {
                curTime += taskQueue.peek().playtime;
                answer[answerIdx++] = taskQueue.pollFirst().name;
            }
            if (taskQueue.isEmpty()) {
                taskQueue.addLast(new Task(cur.name, cur.playtime));
                curTime = cur.start;
            } else if (curTime + taskQueue.peek().playtime > cur.start) {
                taskQueue.peek().playtime = taskQueue.peek().playtime - (cur.start - curTime);
                taskQueue.addFirst(new Task(cur.name, cur.playtime));
                curTime = cur.start;
            }
        }
        while (!taskQueue.isEmpty()) {
            curTime += taskQueue.peek().playtime;
            answer[answerIdx++] = taskQueue.poll().name;
        }

        return answer;
    }

    private int getHourToMinute(String time) {
        String[] timeArr = time.split(":");
        return Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
    }

    private class Task implements Comparable<Task> {
        String name;
        int start;
        int playtime;

        public Task() {
        }

        public Task(String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }

        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Task o) {
            return this.start - o.start;
        }
    }
}
