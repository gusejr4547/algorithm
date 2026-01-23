package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 초대장과_번호표 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        // N <= 100_000
        // G <= 250_000

        Set<Integer>[] set = new Set[G];
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            set[i] = new HashSet<>();
            for (int j = 0; j < p; j++) {
                set[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int answer = 0;
        boolean[] visit = new boolean[N + 1];
        ArrayDeque<Integer> invite = new ArrayDeque<>();
        invite.offer(1);
        visit[1] = true;

        while (!invite.isEmpty()) {
            int cur = invite.poll();

            for (Set<Integer> s : set) {
                s.remove(cur);
                if (s.size() == 1) {
                    int next = -1;
                    for (int n : s) {
                        next = n;
                    }

                    s.remove(next);
                    if (visit[next]) {
                        continue;
                    } else {
                        visit[next] = true;
                        invite.offer(next);
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (visit[i]) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
