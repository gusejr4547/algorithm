package 프로그래머스.PCCP기출문제;

public class 붕대_감기 {
    public static void main(String[] args) {
        붕대_감기 Main = new 붕대_감기();
        int[] bandage = {};
        int health = 0;
        int[][] attacks = {};
        System.out.println(Main.solution(bandage, health, attacks));
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        int casting = bandage[0];
        int recoverPerSec = bandage[1];
        int additionalRecover = bandage[2];

        int answer = health - attacks[0][1];

        for (int i = 1; i < attacks.length; i++) {
            int diff = attacks[i][0] - attacks[i - 1][0] - 1;
            int addtional = diff / casting;

            answer += diff * recoverPerSec + addtional * additionalRecover;
            answer = Math.min(answer, health);

            answer -= attacks[i][1];
            if (answer <= 0) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
