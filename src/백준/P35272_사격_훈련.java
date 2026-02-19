package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P35272_사격_훈련 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double P = Double.parseDouble(st.nextToken());
        double Q = Double.parseDouble(st.nextToken());

        // N발을 쏜다. 명중한 총알 개수가 점수.

        // 내 명중률 P, 다른사람 명중률 Q
        // 내가 몇발 다른사람에게 쏜다. 적절한 개수를 쏴서 기대값을 높이는것이 목적
        // N <= 20

        double[][] A = new double[N][N]; // 사격 결과 확률
        double[][] B = new double[N][N];

        // 0발 중 0발 적중 확률 무조건 1
        A[0][0] = 1.0;
        B[0][0] = 1.0;

        for (int i = 1; i <= N; i++) {
            A[i][0] = A[i - 1][0] * (1 - P);
            B[i][0] = B[i - 1][0] * (1 - Q);
            for (int j = 1; j <= i; j++) {
                A[i][j] = A[i - 1][j - 1] * P + A[i - 1][j] * (1 - P);
                B[i][j] = B[i - 1][j - 1] * Q + B[i - 1][j] * (1 - Q);
            }
        }

        double answer = 0.0;

        // A의 사격 횟수를 늘리면서 최대 기대값 찾기
        for (int i = 0; i <= N; i++) {
            double exp = 0.0;

            // x는 A가 맞춘 횟수, y는 B가 맞춘 횟수
            for (int x = 0; x <= i; x++) {
                for (int y = 0; x + y <= N; y++) {
                    // i개중 x개 맞추고, N개중 y개 맞출 확률 * 점수
                    exp += A[i][x] * B[N][y] * (x + y);
                }
            }

            answer = Math.max(answer, exp);
        }
        System.out.println(answer);
    }
}
