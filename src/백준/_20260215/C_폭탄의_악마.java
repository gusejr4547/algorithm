package 백준._20260215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C_폭탄의_악마 {
    // N <= 200_000
    // M <= 200_000
    // A[i] <= 500_000


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[N + 2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            // A[l] ~ A[r] 에 폭탄을 던진다.
            // 각 원소는 자신의 서로 다른 소인수들 중 하나의 값으로 변한다.
            // 각 소인수로 변할 확률은 균일.
            diff[l]++;
            diff[r + 1]--;
        }

        // 각 위치에 폭탄이 있는지 한번에 알수있는 방법?
        // 누적합 + 차분배열
        int[] boomCnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            boomCnt[i] = boomCnt[i - 1] + diff[i];
        }
//        System.out.println(Arrays.toString(boomCnt));

        // 각 숫자의 기대값 미리 계산
        long[] primeSum = new long[500_001];
        int[] primeCnt = new int[500_001];
        for (int i = 2; i <= 500_000; i++) {
            // 소수가 아니면 다음
            if (primeCnt[i] != 0) {
                continue;
            }
            for (int j = i; j <= 500_000; j += i) {
                primeSum[j] += i;
                primeCnt[j]++;
            }
        }

        double[] expectValue = new double[500_001];
        for (int i = 2; i <= 500_000; i++) {
            expectValue[i] = (double) primeSum[i] / primeCnt[i];
        }

        // 폭탄이 터지면 소인수가됨. 한번더 터져도 자기자신
        double result = 0;
        for (int i = 1; i <= N; i++) {
            if (boomCnt[i] == 0) {
                result += A[i];
            } else {
                result += expectValue[A[i]];
            }
        }

        // 모든 폭발이 일어난 후 sum(A)의 기대값?
        System.out.println(result);
    }
}
