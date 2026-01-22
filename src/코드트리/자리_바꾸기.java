package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 자리_바꾸기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] pair = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            pair[i][0] = Integer.parseInt(st.nextToken());
            pair[i][1] = Integer.parseInt(st.nextToken());
        }

        Set<Integer>[] sets = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            sets[i] = new HashSet<>();
        }

        int[] seat = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            seat[i] = i;
            sets[i].add(i);
        }

        // 3번 스왑
        for (int t = 0; t < 3; t++) {
            for (int[] p : pair) {
                swap(p[0], p[1], seat);
                sets[seat[p[0]]].add(p[0]);
                sets[seat[p[1]]].add(p[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(sets[i].size()).append('\n');
        }

        System.out.println(sb);
    }

    private static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
