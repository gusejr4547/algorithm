package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P3430_용이_산다 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Z = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Z; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] t = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                t[i] = Integer.parseInt(st.nextToken());
            }

            solution(n, m, t);
        }
        System.out.println(sb);
    }

    static void solution(int n, int m, int[] t) {
        boolean[] fill = new boolean[n + 1];
        int[] next = new int[m];
        int[] prev = new int[n + 1];
        boolean flood = false;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        ArrayDeque<Integer> result = new ArrayDeque<>();

        Arrays.fill(fill, true);

        // 각 호수에 비가오는 가장 빠른 시간을 pq에 추가
        for (int i = 0; i < m; i++) {
            int lakeNum = t[i];
            if (lakeNum > 0) {
                if (prev[lakeNum] > 0) {
                    next[prev[lakeNum]] = i;
                } else {
                    pq.offer(i);
                }
                prev[lakeNum] = i;
            }
        }

        for (int i = 0; i < m; i++) {
            int lakeNum = t[i];
            if (lakeNum > 0) {
                if (fill[lakeNum]) {
                    flood = true;
                    break;
                } else {
                    fill[lakeNum] = true;
                    if (next[i] > 0) {
                        pq.offer(next[i]);
                    }
                }
            } else {
                if (!pq.isEmpty()) {
                    int selectDay = pq.poll();
                    int select = t[selectDay];
                    fill[select] = false;
                    result.offer(select);
                } else {
                    result.offer(0);
                }
            }
        }


        if (!flood) {
            sb.append("YES\n");
            while(!result.isEmpty()){
                sb.append(result.poll()).append(" ");
            }
            sb.append("\n");
        } else {
            sb.append("NO\n");
        }
    }
}
