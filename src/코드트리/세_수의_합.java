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

        Map<Integer, Integer> count = new HashMap<>();
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            arr[i] = n;
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        long answer = 0;
        // 배열 앞에서 순회
        for (int i = 0; i < N; i++) {
            // i번째 뒤에부터 사용할꺼기 때문에 count에서 날려
            if (count.containsKey(arr[i])) {
                int c = count.get(arr[i]) - 1;
                if (c == 0) {
                    count.remove(arr[i]);
                } else {
                    count.put(arr[i], c);
                }
            }

            for (int j = 0; j < i; j++) {
                // i, j 번째 수를 사용하고 뒤에 남은 수 1개를 골라서 K 만들기
                int target = K - arr[i] - arr[j];

                if (count.containsKey(target)) {
                    answer += count.get(target);
                }
            }
        }

        System.out.println(answer);
    }
}
