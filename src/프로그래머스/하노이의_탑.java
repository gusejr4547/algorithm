package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 하노이의_탑 {
    public static void main(String[] args) {
        하노이의_탑 Main = new 하노이의_탑();
        int[][] answer = Main.solution(2);
        for(int[] move : answer){
            System.out.println(Arrays.toString(move));
        }
    }

    public int[][] solution(int n) {
        List<int[]> result = new ArrayList<>();
        hanoi(n, 1, 2, 3, result);

        int[][] answer = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public void hanoi(int N, int start, int mid, int end, List<int[]> result) {
        if (N == 1) {
            result.add(new int[]{start, end});
            return;
        }
        hanoi(N - 1, start, end, mid, result);
        result.add(new int[]{start, end});
        hanoi(N - 1, mid, start, end, result);
    }
}
