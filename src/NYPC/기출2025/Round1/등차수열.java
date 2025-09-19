package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등차수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 각 항에 +1 or -1을 할 수 있음.
        // 최소한의 연산으로 공차 1인 등차수열을 만들기
        // 공차가 d인 등차수열은 i*d를 빼면 모두 같은 값이 된다.
        for (int i = 0; i < N; i++) {
            seq[i] -= i;
        }

        // 모든 값을 x로 만들기 위해서는 모든 값이 x-1 와 x+1사이에 있어야한다.
        // 후보값 선정
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            min = Math.min(min, seq[i]);
            max = Math.max(max, seq[i]);
        }

        int answer = 0;
        if (max - min == 2) {
            // x = min + 1
            for (int i = 0; i < N; i++) {
                if (seq[i] != min + 1) {
                    answer++;
                }
            }
        } else if (max - min == 1) {
            // x = min or max 값 중 작은거
            int minCnt = 0;
            int maxCnt = 0;
            for (int i = 0; i < N; i++) {
                if (seq[i] == min) {
                    minCnt++;
                }
                if (seq[i] == max) {
                    maxCnt++;
                }
            }
            answer = Math.min(minCnt, maxCnt);
        } else {
            answer = 0;
        }

        System.out.println(answer);
    }
}
