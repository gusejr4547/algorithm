package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17089_세_친구 {
    static int N, M, answer;
    static Set<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new HashSet<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj[A].add(B);
            adj[B].add(A);
        }

        answer = Integer.MAX_VALUE;
        find(1, 0, new int[3]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void find(int idx, int count, int[] select) {
        if (count == 3) {
            int sum = 0;
            for (int num : select) {
                sum += adj[num].size() - 2;
            }

            answer = Math.min(answer, sum);
            return;
        }

        for (int i = idx; i <= N; i++) {
            if (isFriend(i, count, select)) {
                select[count] = i;
                find(i + 1, count + 1, select);
            }
        }
    }

    private static boolean isFriend(int num, int count, int[] select) {
        for (int i = 0; i < count; i++) {
            if (!adj[num].contains(select[i])) {
                return false;
            }
        }

        return true;
    }
}
