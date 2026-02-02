package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15661_링크와_스타트 {
    // 총 N명
    // 두 팀으로 나눈다. 팀에는 적어도 1명이상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        // N개의 bit, 0 1로 팀을 구분
        // 0111과 1000은 같다. => 한사람을 고정하면 중복을 방지할 수 있다.
        // 마지막 사람 0으로 고정
        for (int team = 1; team < (1 << (N - 1)); team++) {

            int teamA = 0;
            int teamB = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if ((team & (1 << i)) != 0 && (team & (1 << j)) != 0) {
                        teamA += S[i][j] + S[j][i];
                    }

                    if ((team & (1 << i)) == 0 && (team & (1 << j)) == 0) {
                        teamB += S[i][j] + S[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(teamA - teamB));
        }
        System.out.println(answer);
    }
}
