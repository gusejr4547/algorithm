package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 카운트_다운 {
    public static void main(String[] args) {
        카운트_다운 Main = new 카운트_다운();
        int target = 58;
        System.out.println(Arrays.toString(Main.solution(target)));
    }

    // 최소 다트
    // 먼저 고려 -> 싱글, 불
    public int[] solution(int target) {
        final int BULL = 50;
        int[] answer = new int[2];
        // dp[i][0] : 점수 i, 던진 횟수
        // dp[i][1] : 싱글, 불 횟수
        int[][] dp = new int[target + 1][2];

        for (int i = 0; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[0][0] = 0;
        for (int score = 1; score <= target; score++) {
            if (score - BULL >= 0) {
                if (dp[score][0] > dp[score - BULL][0] + 1) {
                    dp[score][0] = dp[score - BULL][0] + 1;
                    dp[score][1] = dp[score - BULL][1] + 1;
                } else if (dp[score][0] == dp[score - BULL][0] + 1) {
                    dp[score][1] = Math.max(dp[score][1], dp[score - BULL][1] + 1);
                }
            }

            for (int t = 1; t <= 20; t++) {
                for (int multi = 1; multi <= 3; multi++) {
                    if (score - t * multi >= 0) {
                        if (dp[score][0] > dp[score - t * multi][0] + 1) {
                            dp[score][0] = dp[score - t * multi][0] + 1;
                            dp[score][1] = multi == 1 ? dp[score - t * multi][1] + 1 : dp[score - t * multi][1];
                        } else if (multi == 1 && dp[score][0] == dp[score - t * multi][0] + 1) {
                            dp[score][1] = Math.max(dp[score][1], dp[score - t * multi][1] + 1);
                        }
                    }
                }
            }
        }

        answer[0] = dp[target][0];
        answer[1] = dp[target][1];
        return answer;
    }
}
