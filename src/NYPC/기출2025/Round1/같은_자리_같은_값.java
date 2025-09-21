package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 같은_자리_같은_값 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // A에서 i번째 값부터 길이가 k인 수열을 뽑아
        // B에서 j번째 값부터 길이가 k인 수열을 뽑아
        // 동일한 위치에 동일한 값이 등장하는 횟수를 D(i,j,k)
        // D(i,j,k) 최대값.
        // N, M <= 20만
        // 원소값 <= 100만
        // 동일한 값은 A와 B를 합쳐 최대 3번 등장한다

        // 같은 위치에 같은 값이 여러개 존재한다는 것은 i-j 값이 같은게 여러개 있다는 것이다.
        Map<Integer, List<Integer>> aM = new HashMap<>();
        Map<Integer, List<Integer>> bM = new HashMap<>();
        for (int i = 0; i < N; i++) {
            aM.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < M; i++) {
            bM.computeIfAbsent(b[i], k -> new ArrayList<>()).add(i);
        }

        int answer = 0;
        Map<Integer, Integer> idxDiff = new HashMap<>();

        for (int key : aM.keySet()) {
            if (!bM.containsKey(key)) {
                continue;
            }
            for (int i : aM.get(key)) {
                for (int j : bM.get(key)) {
                    int d = i - j;
                    int count = idxDiff.getOrDefault(d, 0) + 1;
                    idxDiff.put(d, count);

                    answer = Math.max(answer, count);
                }
            }
        }

        System.out.println(answer);
    }
}
