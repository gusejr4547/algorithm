package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2022;

import java.util.Arrays;

public class 양궁대회 {
    public static void main(String[] args) {
        양궁대회 Main = new 양궁대회();
        int n = 9;
        int[] info = {0,0,1,2,0,1,1,1,1,1,1};
        System.out.println(Arrays.toString(Main.solution(n, info)));
    }

    int maxDiffScore;
    int[] answer;

    public int[] solution(int n, int[] info) {
        answer = new int[11];
        maxDiffScore = Integer.MIN_VALUE;
        // 11개 중에 n개 점수 먹기
        comb(0, 0, new int[11], n, info);

        if (maxDiffScore <= 0) {
            return new int[]{-1};
        }

        return answer;
    }

    private void comb(int idx, int count, int[] select, int n, int[] info) {
        if (idx == 10 || count == n) {
            // 마지막 idx에 도달 => 0점에 남은 화살 다 사용
            if (count != n) {
                select[idx] = n - count;
            }
            // 화살 다씀 => select 배열이 최종 배열
            int diffScore = calDiffScore(info, select);
            if (maxDiffScore < diffScore) {
                maxDiffScore = diffScore;
                answer = select.clone();
            } else if (maxDiffScore == diffScore) {
                // 점수 같으면 가장 낮은 점수 많이 맞춘것 선택
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] == 0 && select[i] == 0) {
                        continue;
                    }

                    if (answer[i] < select[i]) {
                        answer = select.clone();
                        break;
                    } else if (answer[i] > select[i]) {
                        break;
                    }
                }
            }
            select[idx] = 0;
            return;
        }
        // 현재 과녁 점수 먹나?
        // 먹기
        int shot = info[idx] + 1;
        if (shot <= n - count) {
            select[idx] = shot;
            comb(idx + 1, count + shot, select, n, info);
            select[idx] = 0;
        }
        // 안먹기
        comb(idx + 1, count, select, n, info);
    }

    private int calDiffScore(int[] info, int[] select) {
        int apeach = 0;
        int ryan = 0;

        for (int i = 0; i < 11; i++) {
            if (info[i] == 0 && select[i] == 0) {
                continue;
            }

            if (info[i] < select[i]) {
                ryan += 10 - i;
            } else {
                apeach += 10 - i;
            }
        }
        return ryan - apeach;
    }
}
