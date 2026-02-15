package 백준._20260215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_지금부터_완장_찬양을_시작하겠습니다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String visitHistory = br.readLine();

        // 방문 1
        // 0이 K개 이상 연속되면 x
        // k개 연속 구간의 합이 0이면 x
        boolean get = true;
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += visitHistory.charAt(i) - '0';
        }
        if (sum == 0) {
            get = false;
        }

        for (int i = K; i < N; i++) {
            // i를 넣고 i-K를 뺀다
            sum = sum + (visitHistory.charAt(i) - '0') - (visitHistory.charAt(i - K) - '0');
            if (sum == 0) {
                get = false;
            }
        }

        System.out.println(get ? 1 : 0);
    }
}
