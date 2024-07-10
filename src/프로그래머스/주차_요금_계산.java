package 프로그래머스;

import java.util.*;

public class 주차_요금_계산 {
    public static void main(String[] args) {
        주차_요금_계산 Main = new 주차_요금_계산();
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(Main.solution(fees, records)));
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> totalParkingTime = new TreeMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            int time = hourToMinute(st.nextToken());
            String carNum = st.nextToken();
            String state = st.nextToken();

            if ("IN".equals(state)) {
                totalParkingTime.put(carNum, totalParkingTime.getOrDefault(carNum, 0) + time * -1);
            } else {
                totalParkingTime.put(carNum, totalParkingTime.getOrDefault(carNum, 0) + time);
            }
        }

        // 남아있는 차
        int endTime = hourToMinute("23:59");
        int[] answer = new int[totalParkingTime.size()];

        int idx = 0;
        for (int totalTime : totalParkingTime.values()) {
            if (totalTime < 1) {
                totalTime += endTime;
            }

            if (totalTime <= fees[0]) {
                answer[idx] = fees[1];
            } else {
                answer[idx] = fees[1] + (int) Math.ceil((totalTime - fees[0]) / (double) fees[2]) * fees[3];
            }

            idx++;
        }

        return answer;
    }

    private int hourToMinute(String hour) {
        String[] time = hour.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
