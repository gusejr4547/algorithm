package 백준._20260110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_히든_이벤트 {
    // N개 지역 있고, 그중에 M개 지역에 히든 이벤트
    // P(x) = x개 지역을 골라 탐색할때, 히든 이벤트가 적어도 하나 포함될 확률

    // 1 ~ K 의 모든 정수에 대해서 P(x) 출력

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // P(x) = 1 - x개에 히든이벤트 없는 경우 n-mCx / nCx

        // m <= n <= 200 000

        StringBuilder sb = new StringBuilder();

        double none = 1.0;
        for (int i = 1; i <= K; i++) {
            // i번째 히든이벤트 아닌 지역 뽑을 확률 곱하기

            double noHidden = (N - M) - (i - 1);
            double total = N - (i - 1);

            if (noHidden <= 0) {
                none = 0.0;
            } else {
                none *= (noHidden / total);
            }

            sb.append(1.0 - none).append('\n');
        }

        System.out.println(sb);
    }
}
