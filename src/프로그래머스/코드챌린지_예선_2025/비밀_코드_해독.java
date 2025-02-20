package 프로그래머스.코드챌린지_예선_2025;

import java.util.Arrays;

/*
1부터 n까지의 서로 다른 정수 5개가 오름차순으로 정렬된 비밀 코드
m번의 시도 이력이 주어짐

비밀코드로 가능한 정수 조합을 return
 */
public class 비밀_코드_해독 {
    public static void main(String[] args) {
        비밀_코드_해독 Main = new 비밀_코드_해독();
        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans = {2, 3, 4, 3, 3};
        System.out.println(Main.solution(n, q, ans));
    }

    int answer;

    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        // 5개 숫자
        // n개 중 5개 뽑기 >> 조합
        combination(1, n, 0, new int[5], q, ans);

        return answer;
    }

    private void combination(int num, int maxNum, int selectIdx, int[] select, int[][] inputs, int[] results) {
        if (selectIdx == 5) {
            // 검증
            if (isFit(select, inputs, results)) {
                answer++;
            }
            return;
        }

        for (int i = num; i <= maxNum; i++) {
            select[selectIdx] = i;
            combination(i + 1, maxNum, selectIdx + 1, select, inputs, results);
        }
    }

    private boolean isFit(int[] select, int[][] inputs, int[] results) {
        for (int i = 0; i < results.length; i++) {
            int[] input = inputs[i];
            int result = results[i];

            int cnt = 0;
            for (int num : select) {
                int idx = Arrays.binarySearch(input, num);
                if (idx >= 0) {
                    cnt++;
                }
            }

            if (cnt != result) {
                return false;
            }
        }

        return true;
    }
}
