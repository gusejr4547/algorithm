package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;

import java.util.*;

public class 주사위_고르기 {
    public static void main(String[] args) {
        주사위_고르기 Main = new 주사위_고르기();
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
        System.out.println(Arrays.toString(Main.solution(dice)));
    }

    int N;
    List<int[]> diceComb;

    public int[] solution(int[][] dice) {
        N = dice.length;
        int[] answer = new int[N / 2];
        diceComb = new ArrayList<>();

        combination(0, 0, new int[N / 2]);

//        for (int[] arr : diceComb) {
//            System.out.print(Arrays.toString(arr));
//        }
//        System.out.println();
        HashMap<Integer, Integer>[] scoreCnts = new HashMap[diceComb.size()];
        for (int i = 0; i < scoreCnts.length; i++) {
            scoreCnts[i] = new HashMap<>();
        }
        // 각 경우마다 가능한 점수 기록
        for (int i = 0; i < diceComb.size(); i++) {
            calculateScoreCnt(i, scoreCnts[i], dice);
//            System.out.println(scoreCnts[i]);
        }

        int max = 0;
        // 승률계산
        for (int i = 0; i < scoreCnts.length / 2; i++) {
            int aIdx = i;
            int bIdx = scoreCnts.length - 1 - i;

            Map<Integer, Integer> aScoreCnt = scoreCnts[aIdx];
            Map<Integer, Integer> bScoreCnt = scoreCnts[bIdx];

            int win = 0;
            int lose = 0;
            for (int a : aScoreCnt.keySet()) {
                for (int b : bScoreCnt.keySet()) {
                    if (a > b) {
                        win += aScoreCnt.get(a) * bScoreCnt.get(b);
                    } else if (b > a) {
                        lose += aScoreCnt.get(a) * bScoreCnt.get(b);
                    }
                }
            }
            if (max < win) {
                max = win;
                answer = diceComb.get(aIdx);
            }
            if (max < lose) {
                max = lose;
                answer = diceComb.get(bIdx);
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }

        return answer;
    }

    private void calculateScoreCnt(int idx, HashMap<Integer, Integer> scoreCnt, int[][] dice) {
        int[] selectDiceIdx = diceComb.get(idx);
        calculateScore(0, 0, selectDiceIdx, dice, scoreCnt);
    }

    private void calculateScore(int depth, int sum, int[] selectDiceIdx, int[][] dice, HashMap<Integer, Integer> scoreCnt) {
        if (depth == N / 2) {
            scoreCnt.put(sum, scoreCnt.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int i = 0; i < 6; i++) {
            calculateScore(depth + 1, sum + dice[selectDiceIdx[depth]][i], selectDiceIdx, dice, scoreCnt);
        }
    }

    private void combination(int depth, int idx, int[] select) {
        if (depth == N / 2) {
            diceComb.add(select.clone());
            return;
        }

        for (int i = idx; i < N; i++) {
            select[depth] = i;
            combination(depth + 1, i + 1, select);
        }
    }
}
