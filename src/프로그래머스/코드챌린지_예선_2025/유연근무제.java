package 프로그래머스.코드챌린지_예선_2025;

/*
Lv.1
일주일동안 출근희망시각 + 10분 까지 출근해야함.
토요일, 일요일은 이벤트에 영향X
출근 희망 시각과 실제로 출근한 기록을 바탕으로 상품을 받을 직원이 몇 명?
*/
public class 유연근무제 {
    public static void main(String[] args) {
        유연근무제 Main = new 유연근무제();
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659}, {800, 801, 805, 800, 759, 810, 809}, {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startday = 5;
        System.out.println(Main.solution(schedules, timelogs, startday));
    }

    // startday 1 > 월요일, 7 > 일요일
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        // 토요일, 일요일 찾기
        boolean[] isWeekend = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if (startday == 6 || startday == 7) {
                isWeekend[i] = true;
            }
            startday = startday == 7 ? 1 : startday + 1;
        }

        for (int i = 0; i < timelogs.length; i++) {
            int schedule = schedules[i] + 10;
            if (schedule % 100 >= 60) {
                schedule += 40;
            }
            int cnt = 0;
            for (int j = 0; j < 7; j++) {
                if (!isWeekend[j] && schedule >= timelogs[i][j]) {
                    cnt++;
                }
            }

            if (cnt == 5) {
                answer++;
            }
        }


        return answer;
    }
}
