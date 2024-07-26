package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class 인사고과 {
    public static void main(String[] args) {
        인사고과 Main = new 인사고과();
//        int[][] scores = {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        int[][] scores = {{4, 0}, {2, 3}, {4, 4},{2,6}};
        System.out.println(Main.solution(scores));
    }

    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int wanhoScore = wanho[0] + wanho[1];
        int[][] others = scores.clone();
        TreeMap<Integer, Integer> sum = new TreeMap<>(Comparator.reverseOrder());

        Arrays.sort(others, (o1, o2) -> o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        int maxB = Integer.MIN_VALUE;

        for (int i = 0; i < others.length; i++) {
            int score = others[i][0] + others[i][1];
            if (maxB <= others[i][1]) {
                maxB = others[i][1];
                sum.put(score, sum.getOrDefault(score, 0) + 1);
//                System.out.println(Arrays.toString(others[i]));
            } else {
                if (others[i][0] == wanho[0] && others[i][1] == wanho[1]) {
                    return -1;
                }
            }
        }

        int answer = 0;

        for (int score : sum.keySet()) {
//            System.out.println(score);
            if (score > wanhoScore) {
                answer += sum.get(score);
            } else {
                break;
            }
        }
        answer += 1;
        return answer;
    }
}
