package 코드트리.ACPC2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 큐브_위_도미노 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        // N <= 99 999, N은 홀수

        // WW, BB 는 같은 개수
        // 면의 가장자리에 색 개수 (N / 2 * 4) * 6/2

        // WB는 N * N * 6 / 2

        System.out.println((N / 2 * 4) * 6/2 + " " + (N / 2 * 4) * 6/2 + " " + N * N * 3);

    }
}
