package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2502_떡_먹는_호랑이 {
    static int D, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] A = new int[D + 1];
        int[] B = new int[D + 1];
        A[3] = 1;
        B[3] = 1;

        for (int i = 4; i <= D; i++) {
            A[i] = B[i - 1];
            B[i] = A[i - 1] + B[i - 1];
        }

        for (int b = 1; b <= K; b++) {
            for (int a = 1; a <= b; a++) {
                if (a * A[D] + b * B[D] == K) {
                    System.out.println(a);
                    System.out.println(b);
                    return;
                }
            }
        }
    }

}
