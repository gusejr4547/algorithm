package 프로그래머스;

import java.util.Arrays;

public class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int N = commands.length;
        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            int[] cmd = commands[i];
            int[] sub = Arrays.copyOfRange(array, cmd[0] - 1, cmd[1]);
            Arrays.sort(sub);
            answer[i] = sub[cmd[2] - 1];
        }

        return answer;
    }
}
