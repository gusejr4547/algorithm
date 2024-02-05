package 프로그래머스;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 추석_트래픽 {
    public static void main(String[] args) {
        추석_트래픽 Main = new 추석_트래픽();
        String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(Main.solution(lines));
    }

    public int solution(String[] lines) {
        int len = lines.length;
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

        int answer = 0;
        for (int i = 0; i < len; i++) {
            int cntS = 1;
            int cntE = 1;
//            int startTime = taskList.get(i).start;
            int endTime = taskList.get(i).end;
            for (int j = i + 1; j < len; j++) {
                if (endTime + 1000 > taskList.get(j).start) cntE++;
            }

//            answer = Math.max(answer, cntS);
            answer = Math.max(answer, cntE);
        }


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
