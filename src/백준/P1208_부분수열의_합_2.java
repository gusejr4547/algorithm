package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P1208_부분수열의_합_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> leftSum = new HashMap<>();
        Map<Integer, Integer> rightSum = new HashMap<>();
        leftSum.put(0, 1);
        rightSum.put(0, 1);
        for (int i = 0; i < N / 2; i++) {
            Map<Integer, Integer> map = new HashMap<>(leftSum);
            // sum 갱신
            for (int k : leftSum.keySet()) {
                int cnt = leftSum.get(k);
                map.put(k + arr[i], leftSum.getOrDefault(k + arr[i], 0) + cnt);
            }

            leftSum = map;
        }
        for (int i = N / 2; i < N; i++) {
            Map<Integer, Integer> map = new HashMap<>(rightSum);
            for (int k : rightSum.keySet()) {
                int cnt = rightSum.get(k);
                map.put(k + arr[i], rightSum.getOrDefault(k + arr[i], 0) + cnt);
            }
            rightSum = map;
        }

//        System.out.println(leftSum);
//        System.out.println(rightSum);

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
}
