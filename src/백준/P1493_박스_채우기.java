package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1493_박스_채우기 {
    static int length, width, height, N;
    static int[] cube;
    static boolean isDivide = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        cube = new int[20];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            cube[A] = B;
        }

        int answer = divide(length, width, height);
        System.out.println(isDivide ? answer : -1);
    }

    public static int divide(int l, int w, int h) {
        if (l == 0 || w == 0 || h == 0)
            return 0;

        int k = Math.min(l, Math.min(w, h));

        int maxBoxSize = (int) (Math.log(k) / Math.log(2));

        for (int i = maxBoxSize; i >= 0; i--) {
            if (N <= i || cube[i] <= 0)
                continue;
            cube[i]--;
            int size = (int) Math.pow(2, i);

            return divide(l - size, w, h) + divide(size, w - size, h) + divide(size, size, h - size) + 1;
        }
        isDivide = false;
        return -1;
    }

}
