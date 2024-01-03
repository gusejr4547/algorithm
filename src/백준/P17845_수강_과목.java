package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17845_수강_과목 {
    static int N, K;
    static Subject[] subjects;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        subjects = new Subject[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            subjects[i] = new Subject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (subjects[i - 1].time <= j)
                    dp[i][j] = Math.max(subjects[i - 1].important + dp[i - 1][j - subjects[i - 1].time], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[K][N]);

    }

    static class Subject {
        int important;
        int time;

        public Subject() {
        }

        public Subject(int important, int time) {
            this.important = important;
            this.time = time;
        }
    }
}
