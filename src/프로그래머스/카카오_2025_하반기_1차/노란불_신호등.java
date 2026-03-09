package 프로그래머스.카카오_2025_하반기_1차;

public class 노란불_신호등 {
    public static void main(String[] args) {
        노란불_신호등 Main = new 노란불_신호등();
        int[][] signals = {{2, 3, 2}, {3, 1, 3}, {2, 1, 1}};
        System.out.println(Main.solution(signals));
    }

    public int solution(int[][] signals) {
        int N = signals.length;

        int maxTime = 20 * 20 * 20 * 20 * 20;

        int[] yellowCnt = new int[maxTime + 1];

        for (int[] signal : signals) {
            int s = signal[0] + 1;
            int cycle = signal[0] + signal[1] + signal[2];
            while (s <= maxTime) {
                for (int i = 0; i < signal[1] && s + i <= maxTime; i++) {
                    yellowCnt[s + i]++;
                }
                s = s + cycle;
            }
        }

        int answer = -1;
        for(int i=1; i<=maxTime; i++){
            if(yellowCnt[i] == N){
                answer = i;
                break;
            }
        }

        return answer;
    }
}
