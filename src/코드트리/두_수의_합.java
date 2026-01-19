package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 두_수의_합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> count = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        long answer = 0;
        Set<Integer> used = new HashSet<>();
        for (int num : count.keySet()) {
            int pair = K - num;

            if(num == pair){
                int cnt = count.get(num);
                answer += (long) cnt * (cnt-1) / 2;
                used.add(num);
            }else{
                if (count.containsKey(pair) && !used.contains(pair)) {
                    answer += (long) count.get(num) * count.get(pair);
                    used.add(num);
                    used.add(pair);
                }
            }
        }

        System.out.println(answer);
    }
}
