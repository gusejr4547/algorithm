package NYPC.기출2025.연습문제;

// 1부터 N까지 정확히 1번씩 등장하는 수열로 변경

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 순열로_고치기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[] maxIdx = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (seq[i] <= N) {
                maxIdx[seq[i]] = i;
            }
        }

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            if (seq[i] > N || maxIdx[seq[i]] != i) {
                answer += i;
            }
        }
        System.out.println(answer);


//        Map<Integer, Integer> m = new HashMap<>();
//        for (int i = 0; i < N; i++) {
//            m.put(seq[i], m.getOrDefault(seq[i], 0) + 1);
//        }
////        System.out.println(m);
//
//        long answer = 0;
//        // N 넘어가는거 무조건 바꿔야함.
//        // 중복되는거 있으면 앞에 나온거를 바꿔야함.
//        for (int i = 0; i < N; i++) {
//            if (seq[i] > N) {
//                answer += (i + 1);
//            } else {
//                if (m.get(seq[i]) > 1) {
//                    m.put(seq[i], m.get(seq[i]) - 1);
//                    answer += (i + 1);
//                }
//            }
//        }

        System.out.println(answer);
    }
}
