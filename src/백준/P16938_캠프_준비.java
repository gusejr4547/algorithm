package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16938_캠프_준비 {
    static int N, L, R, X, answer;
    static int[] difficulties;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        difficulties = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            difficulties[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
//        backtrack(0, 0, Integer.MAX_VALUE, 0, 0);
        for (int mask = 1; mask < (1 << N); mask++) {
            int sum = 0;
            int count = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                if((mask & (1 << i)) != 0){
                    count++;
                    sum += difficulties[i];
                    min = Math.min(min, difficulties[i]);
                    max = Math.max(max, difficulties[i]);
                }
            }

            if(count >= 2 && L <= sum && sum <= R && max - min >= X){
                answer++;
            }
        }


        System.out.println(answer);
    }

    private static void backtrack(int idx, int sum, int min, int max, int count) {
        if (idx == N) {
            if (count >= 2 && L <= sum && sum <= R && max - min >= X) {
                answer++;
            }
            return;
        }

        if (sum > R) {
            return;
        }

        backtrack(idx + 1, sum, min, max, count);

        min = Math.min(min, difficulties[idx]);
        max = Math.max(max, difficulties[idx]);
        backtrack(idx + 1, sum + difficulties[idx], min, max, count + 1);
    }


}
