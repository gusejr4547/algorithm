package 프로그래머스;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 추석_트래픽 {
    public static void main(String[] args) {
        추석_트래픽 Main = new 추석_트래픽();
        String[] lines = {"2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"};
        System.out.println(Main.solution(lines));
    }

    public int solution(String[] lines) {
        List<Task> taskList = new ArrayList<>();
        for (String log : lines) {
            StringTokenizer st = new StringTokenizer(log);
            String dayLog = st.nextToken();
            String endTimeLog = st.nextToken();
            String processTimeLog = st.nextToken().split("s")[0];

            int endTime = toMilliSec(endTimeLog);
            int processTime = (int) (Double.parseDouble(processTimeLog) * 1000);
            taskList.add(new Task(endTime - processTime + 1, endTime));
        }

        System.out.println(taskList);

        int answer = 0;
        return answer;
    }

    public int toMilliSec(String endTimeLog) {
        String[] time = endTimeLog.split(":");
        int result = 0;
        result += Integer.parseInt(time[0]) * 60 * 60 * 1000;
        result += Integer.parseInt(time[1]) * 60 * 1000;
        result += (int) (Double.parseDouble(time[2]) * 1000);

        return result;
    }

    static class Task {
        int start;
        int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
