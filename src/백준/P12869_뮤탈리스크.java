package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12869_뮤탈리스크 {
    // 9, 3, 1 데미지
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] hp = new int[3];
        for (int i = 0; i < N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[hp[0] + 1][hp[1] + 1][hp[2] + 1];

        // 공격할 수 있는 경우는
        int[][] dealCase = {{-9, -3, -1}, {-9, -1, -3}, {-3, -9, -1}, {-1, -9, -3}, {-3, -1, -9}, {-1, -3, -9}};

        findMinAttackCnt(hp, dp, dealCase);

        System.out.println(dp[hp[0]][hp[1]][hp[2]]);
    }

    private static int findMinAttackCnt(int[] scvHP, int[][][] dp, int[][] dealCase) {
        int scv1 = Math.max(0, scvHP[0]);
        int scv2 = Math.max(0, scvHP[1]);
        int scv3 = Math.max(0, scvHP[2]);

        // 끝나는 조건
        if (scv1 == 0 && scv2 == 0 && scv3 == 0) {
            return 0;
        }

        // dp에 계산된값 있는 경우
        if (dp[scv1][scv2][scv3] != 0) {
            return dp[scv1][scv2][scv3];
        }

        // 6가지 방법을 실행 후 최소 값 구하기
        int minAttackCnt = Integer.MAX_VALUE;
        for (int[] deal : dealCase) {
            int[] newScvHP = {scv1 + deal[0], scv2 + deal[1], scv3 + deal[2]};
            minAttackCnt = Math.min(findMinAttackCnt(newScvHP, dp, dealCase) + 1, minAttackCnt);
        }

        return dp[scv1][scv2][scv3] = minAttackCnt;
    }

}
