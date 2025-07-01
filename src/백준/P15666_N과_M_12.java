package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P15666_N과_M_12 {
    static int N, M;
    static List<Integer> arr;
    static int[] result;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        arr = new ArrayList<>(set);
        Collections.sort(arr);

        sb = new StringBuilder();
        result = new int[M];
        repeatedCombination(0, 0);
        System.out.println(sb);
    }

    private static void repeatedCombination(int depth, int idx) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < arr.size(); i++) {
            result[depth] = arr.get(i);
            repeatedCombination(depth + 1, i);
        }
    }
}
