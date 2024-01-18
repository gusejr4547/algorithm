package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 셔틀버스 {
    public static void main(String[] args) {
        셔틀버스 Main = new 셔틀버스();
        int n = 10;
        int t = 60;
        int m = 45;
        String[] timetable = {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        System.out.println(Main.solution(n, t, m, timetable));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Integer> waiters = new PriorityQueue<>();
        for (String time : timetable) {
            waiters.offer(time2Int(time));
        }
        int busTime = time2Int("09:00");
        int busCount = 0;

        while (busCount < n) {
            if (busCount != 0)
                busTime += t;
            int pCount = 0;
            while (waiters.size() > 0 && pCount < m) {
                if (waiters.peek() > busTime)
                    break;
                if (busCount == n - 1 && pCount == m - 1) {
                    return answer = int2Time(waiters.poll() - 1);
                }
                waiters.poll();
                pCount++;
            }
            busCount++;
        }

        answer = int2Time(busTime);

        return answer;
    }

    public int time2Int(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        return hour * 60 + minute;
    }

    public String int2Time(int time) {
        String hour = String.format("%02d", time / 60);
        String minute = String.format("%02d", time % 60);
        return hour + ":" + minute;
    }
}
