package 프로그래머스.코드챌린지_예선_2025;

import java.util.ArrayDeque;

public class 서버_증설_횟수 {
    public static void main(String[] args) {
        서버_증설_횟수 Main = new 서버_증설_횟수();
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        System.out.println(Main.solution(players, m, k));
    }

    public int solution(int[] players, int m, int k) {
        ArrayDeque<Integer> additionalServerTime = new ArrayDeque<>();
        int answer = 0;
        for (int time = 0; time < players.length; time++) {
            while (!additionalServerTime.isEmpty() && additionalServerTime.peek() <= time) {
                additionalServerTime.poll();
            }

            int needAdditionalServerCnt = players[time] / m;
            int curAdditionalServerCnt = additionalServerTime.size();
            if (curAdditionalServerCnt < needAdditionalServerCnt) {
                answer += needAdditionalServerCnt - curAdditionalServerCnt;
                while (curAdditionalServerCnt < needAdditionalServerCnt) {
                    additionalServerTime.offer(time + k);
                    curAdditionalServerCnt++;
                }
            }
        }

        return answer;
    }
}
