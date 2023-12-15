package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1446_지름길 {
    static int N, D, MIN;
    static List<Road> roadList;
    static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        roadList = new ArrayList<>();
        visit = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            roadList.add(new Road(start, end, length));
        }
        roadList.sort((e1, e2) -> e1.start == e2.start ? e1.end - e2.end : e1.start - e2.start);

        MIN = D;
        dfs(0, 0, 0);

        System.out.println(MIN);
    }

    public static void dfs(int curr, int idx, int sum) {
        if (curr <= D) {
            MIN = Math.min(MIN, sum + D - curr);
        }
        if (curr > D) {
            return;
        }

        if (idx >= N) {
            return;
        }

        Road road = roadList.get(idx);
        if(curr <= road.start) {
            int diff = road.start - curr;
            dfs(road.end, idx + 1, sum + diff + road.length);
        }
        dfs(curr, idx + 1, sum);

    }

    static class Road {
        int start;
        int end;
        int length;

        public Road(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
