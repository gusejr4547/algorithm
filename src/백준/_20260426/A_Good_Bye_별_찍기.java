package 백준._20260426;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_Good_Bye_별_찍기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            // 한 줄에 * 3개 찍음.
            int a = 2 * N - 1 - i;
            int offset = 2 * N + 1;
            // 2*N+1/2 중간
            int mid = (2 * N + 1) / 2;
            int b = mid - (i + 1) + offset;
            int c = mid + (i + 1) + offset;

            for (int j = 0; j < N * 4 + 2; j++) {
                if (j == a || j == b || j == c) {
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        for (int i = 0; i < N; i++) {
            // 한 줄에 * 3개 찍음.
            int a = N - 1 - i;
            int offset = 2 * N + 1;
            // 2*N+1/2 중간
            int b = i + offset;
            int c = 2 * N - i + offset;

            for (int j = 0; j < N * 4 + 2; j++) {
                if (j == a || j == b || j == c) {
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
