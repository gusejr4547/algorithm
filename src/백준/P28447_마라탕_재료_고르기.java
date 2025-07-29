package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P28447_마라탕_재료_고르기 {
    static int N, K, answer;
    static int[][] score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        score = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MIN_VALUE;
        select(0, 0, new int[K]);

        System.out.println(answer);
    }

    private static void select(int idx, int sIdx, int[] s) {
        if (sIdx == K) {
            int sum = 0;
            for (int i = 0; i < K - 1; i++) {
                for (int j = i + 1; j < K; j++) {
                    sum += score[s[i]][s[j]];
                }
            }

            answer = Math.max(answer, sum);

            return;
        }

        for (int i = idx; i < N; i++) {
            s[sIdx] = i;
            select(i + 1, sIdx + 1, s);
        }
    }
}
