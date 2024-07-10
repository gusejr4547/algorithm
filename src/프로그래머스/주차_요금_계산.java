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
        HashMap<String, Integer> totalParkingTime = new HashMap<>();
        HashMap<String, Integer> parking = new HashMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            int time = hourToMinute(st.nextToken());
            String carNum = st.nextToken();
            String state = st.nextToken();

            if ("IN".equals(state)) {
                parking.put(carNum, time);
            } else {
                int parkingTime = parking.get(carNum);
                parking.remove(carNum);
                totalParkingTime.put(carNum, totalParkingTime.getOrDefault(carNum, 0) + time - parkingTime);
            }
        }

        // 남아있는 차
        int endTime = hourToMinute("23:59");
        for (String carNum : parking.keySet()) {
            int parkingTime = parking.get(carNum);
            totalParkingTime.put(carNum, totalParkingTime.getOrDefault(carNum, 0) + endTime - parkingTime);
        }

        List<String> carNumArr = new ArrayList<>(totalParkingTime.keySet());
        Collections.sort(carNumArr);

        int[] answer = new int[carNumArr.size()];
        for (int i = 0; i < carNumArr.size(); i++) {
            String carNum = carNumArr.get(i);
            int totalTime = totalParkingTime.get(carNum);

            if (totalTime <= fees[0]) {
                answer[i] = fees[1];
            } else {
                answer[i] = fees[1] + (int) Math.ceil((totalTime - fees[0]) / (double) fees[2]) * fees[3];
            }
        }

        return answer;
    }

    private int hourToMinute(String hour) {
        String[] time = hour.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
