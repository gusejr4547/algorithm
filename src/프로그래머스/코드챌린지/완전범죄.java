package 프로그래머스.코드챌린지;

import java.util.Arrays;

public class 완전범죄 {
    public static void main(String[] args) {
        완전범죄 Main = new 완전범죄();
        int[][] info = {{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;
        System.out.println(Main.solution(info, n, m));
    }

    // info.length < 40
    public int solution(int[][] info, int n, int m) {

        // top-down 방식 풀이
        // dp[idx][a의 흔적][b의 흔적] = a의 흔적 최소값
        // idx번째 선택 a,b의 흔적이 이전에 계산했던적이 있으면 >> 이후 결과도 같기때문에 다시계산할 필요없음.
        int[][][] dp = new int[info.length][n][m];
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = dfs(0, 0, 0, n, m, info, dp);
        answer = answer == Integer.MAX_VALUE ? -1 : answer;

        return answer;
    }

    private int dfs(int idx, int aHunjuk, int bHunjuk, int n, int m, int[][] info, int[][][] dp) {
        if (aHunjuk >= n || bHunjuk >= m) {
            return Integer.MAX_VALUE;
        }

        // 전부 훔침 return A의 흔적
        if (idx == info.length) {
            return aHunjuk;
        }

        // 결과값 있는지?
        if (dp[idx][aHunjuk][bHunjuk] != -1) {
            return dp[idx][aHunjuk][bHunjuk];
        }

        // A가 idx물건 훔쳤을때 A흔적
        int aResult = dfs(idx + 1, aHunjuk + info[idx][0], bHunjuk, n, m, info, dp);
        // B가 idx물건 훔쳤을때 A흔적
        int bResult = dfs(idx + 1, aHunjuk, bHunjuk + info[idx][1], n, m, info, dp);

        return dp[idx][aHunjuk][bHunjuk] = Math.min(aResult, bResult);
    }
}
