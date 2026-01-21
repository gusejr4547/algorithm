package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2022_사다리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double min = 0;
        double max = Math.min(x, y);

        for (int i = 0; i < 10_000; i++) {
            double mid = (min + max) / 2; // 추정 거리

            double h1 = Math.sqrt(x * x - mid * mid);
            double h2 = Math.sqrt(y * y - mid * mid);

            double predictC = h1 * h2 / (h1 + h2);

            // 거리 좁다
            if (predictC >= c) {
                min = mid;
            } else {
                max = mid;
            }
        }

        System.out.println(min);
    }
}
