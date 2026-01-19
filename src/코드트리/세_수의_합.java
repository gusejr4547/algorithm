package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 세_수의_합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> sum = new HashMap<>();

        long answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            // n을 선택했을 때 3개 수의 합이 K가 되는 것을 만들 수 있나
            int diff = K - n;
            if (sum.containsKey(diff)) {
                answer += sum.get(diff);
            }

            // n을 이용해 sum을 만들기
            for (int a : count1.keySet()) {
                sum.put(n + a, sum.getOrDefault(n + a, 0) + count1.get(a));
            }

            // count1 추가
            count1.put(n, count1.getOrDefault(n, 0) + 1);
        }

        System.out.println(answer);
    }
}
