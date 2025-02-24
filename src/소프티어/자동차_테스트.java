package 소프티어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 자동차_테스트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 주어지는 자동차의 연비는 서로 다름을 가정
        int[] yearCosts = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            yearCosts[i] = Integer.parseInt(st.nextToken());
        }

        int[] expectation = new int[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            expectation[i] = Integer.parseInt(st.nextToken());
        }

        // 3대의 자동차에 대해서만 테스트가 가능
        // 임의로 3대의 자동차를 골라 테스트하여 중앙값이 expectation[i]값이 나오는 서로 다른 경우의 수

        // n <= 50_000
        // q <= 200_000
        // 50000C3 => 시간초과

        // 연비 정렬 후, 중앙값의 index를 찾아서 중앙값보다 작은 개수 * 중앙값보다 큰 개수. 중앙값이 연비에 없다면 0
        // 시험 칠때는 이진탐색으로 풀었던것 같음.

        // hashmap을 이용해 연비 - index 매핑

        // 정렬
        Arrays.sort(yearCosts);

        // 매핑
        Map<Integer, Integer> yearCostIndexMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            yearCostIndexMap.put(yearCosts[i], i);
        }

        // 계산
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int result = getCaseCount(expectation[i], yearCostIndexMap, N);
            resultSb.append(result).append('\n');
        }

        System.out.println(resultSb);
    }

    private static int getCaseCount(int median, Map<Integer, Integer> yearCostIndexMap, int N) {
        if (!yearCostIndexMap.containsKey(median)) {
            return 0;
        } else {
            int idx = yearCostIndexMap.get(median);
            return idx * (N - idx - 1);
        }
    }
}
