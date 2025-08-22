package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17471_게리맨더링 {
    static int N, answer;
    static int[] area;
    static List<Integer>[] adj;
    static boolean[] select;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        area = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            area[i] = Integer.parseInt(st.nextToken());
        }

        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int total = Integer.parseInt(st.nextToken());
            while (total-- > 0) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        select = new boolean[N + 1];
        answer = Integer.MAX_VALUE;
        comb(1, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void comb(int idx, int count) {
        if (idx == N + 1) {
            if (count == 0 || count == N) {
                return;
            }
            valid();
            return;
        }

        comb(idx + 1, count);
        select[idx] = true;
        comb(idx + 1, count + 1);
        select[idx] = false;
    }

    private static void valid() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (select[i]) {
                q.offer(i);
                visit[i] = true;
                break;
            }
        }

//        if (q.isEmpty()) {
//            return;
//        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if (select[next] && !visit[next]) {
                    visit[next] = true;
                    q.offer(next);
                }
            }
        }


        for (int i = 1; i <= N; i++) {
            if (!select[i]) {
                q.offer(i);
                visit[i] = true;
                break;
            }
        }

//        if (q.isEmpty()) {
//            return;
//        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if (!select[next] && !visit[next]) {
                    visit[next] = true;
                    q.offer(next);
                }
            }
        }

        // 전부 방문 불가능 return
        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                return;
            }
        }

        int A = 0;
        int B = 0;
        for (int i = 1; i <= N; i++) {
            if (select[i]) {
                A += area[i];
            } else {
                B += area[i];
            }
        }
//        System.out.println(Arrays.toString(select));
        answer = Math.min(answer, Math.abs(A - B));
    }

}
