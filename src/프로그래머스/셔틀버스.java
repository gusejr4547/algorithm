package 프로그래머스;

import java.util.Arrays;

public class 셔틀버스 {
    public static void main(String[] args) {
        셔틀버스 Main = new 셔틀버스();
        int n = 10;
        int t = 60;
        int m = 45;
        String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        System.out.println(Main.solution(n, t, m, timetable));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] timeTable = new int[timetable.length];
        for (int i = 0; i < timeTable.length; i++) {
            timeTable[i] = Integer.parseInt(timetable[i].replace(":", ""));
        }

        // 이진탐색으로 출발 시간 전 태움?
        int busTime = 900;
        int index = 0;
        int last = 0;
        while (index < timeTable.length) {
            int i = binarySearch(timeTable, index, timeTable.length - 1, busTime);
            if (i - index + 1 <= m) {
                last = i - index + 1;
                index = i + 1;
            } else {
                last = m;
                index = index + m;
            }
            if (index < timeTable.length)
                busTime = (busTime + t) % 100 == 60 ? busTime + 40 : busTime + t;
        }

        if (last < m) {
            answer = busTime / 100 + ":" + busTime % 100;
        }else{
            busTime = (busTime + t) % 100 == 60 ? busTime + 40 : busTime + t;
            answer = busTime / 100 + ":" + busTime % 100;
        }

        return answer;
    }

    public int binarySearch(int[] timeTable, int start, int end, int busTime) {
        int s = start;
        int e = end;
        int result = 0;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (timeTable[mid] == busTime) {
                result = mid;
                break;
            } else if (timeTable[mid] < busTime) {
                s = mid + 1;
                result = mid;
            } else {
                e = mid - 1;
            }
        }
        return result;
    }
}
