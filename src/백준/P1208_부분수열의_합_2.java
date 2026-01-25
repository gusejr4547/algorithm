package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P1208_부분수열의_합_2 {
    static Map<Integer, Integer> leftSum, rightSum;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        leftSum = new HashMap<>();
        rightSum = new HashMap<>();

        makeSum(0, N / 2, 0, leftSum);
        makeSum(N / 2, N, 0, rightSum);

        long answer = 0;

        for (int k : leftSum.keySet()) {
            int need = S - k;
            if (rightSum.containsKey(need)) {
                answer += (long) leftSum.get(k) * rightSum.get(need);
            }
        }

        if (S == 0) {
            answer -= 1;
        }

        System.out.println(answer);
    }

    private static void makeSum(int idx, int end, int sum, Map<Integer, Integer> map) {
        if (idx == end) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }

        makeSum(idx + 1, end, sum, map);

        makeSum(idx + 1, end, sum + arr[idx], map);
    }
}
