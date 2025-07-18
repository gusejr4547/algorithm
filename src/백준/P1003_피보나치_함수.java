package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1003_피보나치_함수 {
    static int[][] fibo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        fibo = new int[50][2];
        fibo[0][0] = 1;
        fibo[1][1] = 1;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] answer = fibonacci(N);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }

    private static int[] fibonacci(int n) {
        if (fibo[n][0] != 0 || fibo[n][1] != 0) {
            return fibo[n];
        }
        int[] a = fibonacci(n - 1);
        int[] b = fibonacci(n - 2);

        fibo[n][0] = a[0] + b[0];
        fibo[n][1] = a[1] + b[1];

        return fibo[n];
    }
}
