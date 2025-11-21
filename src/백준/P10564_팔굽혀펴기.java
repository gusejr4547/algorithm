package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10564_팔굽혀펴기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] type = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                type[i] = Integer.parseInt(st.nextToken());
            }

            // N번 팔굽혀펴기 했음.
            // 득점할 때 현재 점수만큼 팔굽혀펴기를 함.
            // 득점한 점수 최대값. 없으면 -1

            //
            boolean[][] dp = new boolean[501][5001]; // dp[score][count] score일때 count 만큼 팔굽혀펴기 가능?
            dp[0][0] = true;

            for (int score = 1; score <= 501; score++) {
                for (int count = 1; count <= N; count++) {
                    int prevCount = count - score;
                    for (int t = 0; t < M; t++) {
                        int prevScore = score - type[t];
                        if (prevScore >= 0 && prevCount >= 0 && dp[prevScore][prevCount]) {
                            dp[score][count] = true;
                            break;
                        }
                    }
                }
            }

            // 최대값
            int answer = -1;
            for (int score = 500; score >= 1; score--) {
                if (dp[score][N]) {
                    answer = score;
                    break;
                }
            }

            System.out.println(answer);
        }
    }
}
