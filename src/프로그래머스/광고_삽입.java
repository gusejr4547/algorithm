package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 광고_삽입 {
    public static void main(String[] args) {
        광고_삽입 Main = new 광고_삽입();
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(Main.solution(play_time, adv_time, logs));
    }

    int[][] timeLogs;

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeConvertStringToInt(play_time);
        int advTime = timeConvertStringToInt(adv_time);
        timeLogs = new int[logs.length][2];
        for (int i = 0; i < logs.length; i++) {
            String[] SE = logs[i].split("-");
            timeLogs[i][0] = timeConvertStringToInt(SE[0]);
            timeLogs[i][1] = timeConvertStringToInt(SE[1]);
        }


        long[] time = new long[playTime + 1];
        for (int[] timeLog : timeLogs) {
            int start = timeLog[0];
            int end = timeLog[1];

            time[start]++;
            time[end]--;
        }

        // 시간별 시청자수
        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
        }
        // 시간별 누적 시청시간
        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
        }


        int maxStartTime = 0;
        long maxPlayTimeSum = time[advTime - 1];
        for (int i = advTime; i < time.length; i++) {
            long playTimeSum = time[i] - time[i - advTime];
            if (maxPlayTimeSum < playTimeSum) {
                maxPlayTimeSum = playTimeSum;
                maxStartTime = i - advTime + 1;
            }
        }

        return timeConvertIntToString(maxStartTime);
    }

    private String timeConvertIntToString(int time) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", time / (60 * 60))).append(":");
        time = time % (60 * 60);
        sb.append(String.format("%02d", time / 60)).append(":");
        time = time % 60;
        sb.append(String.format("%02d", time));
        return sb.toString();
    }

    private int timeConvertStringToInt(String time) {
        String[] HMS = time.split(":");
        int t = 0;
        t += Integer.parseInt(HMS[0]) * 60 * 60;
        t += Integer.parseInt(HMS[1]) * 60;
        t += Integer.parseInt(HMS[2]);
        return t;
    }
}
