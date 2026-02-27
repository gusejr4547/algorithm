package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P35306_월간_향유회_시즌_종료 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] score = new int[N][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Set<Integer> answer = new HashSet<>();

        for (int j = 0; j < K; j++) {
            int max = 0;
            int maxCnt = 0;
            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (score[i][j] > max) {
                    max = score[i][j];
                    maxCnt = 1;
                    idx = i;
                } else if (score[i][j] == max) {
                    maxCnt++;
                }
            }

            if (maxCnt == 1) {
                answer.add(idx);
            }
        }

        System.out.println(answer.size());
    }
}
