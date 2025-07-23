package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P20303_할로윈의_양아치 {
    static int N, M, K;
    static int[] candy, parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        Map<Integer, Bucket> groupMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            int group = find(i);
            Bucket b = groupMap.computeIfAbsent(group, k -> new Bucket(0, 0));
            b.children++;
            b.amount += candy[i];

//            if (!groupMap.containsKey(group)) {
//                groupMap.put(group, new Bucket(1, candy[i]));
//            } else {
//                Bucket b = groupMap.get(group);
//                b.children++;
//                b.amount += candy[i];
//                groupMap.put(group, b);
//            }
        }
        List<Bucket> group = new ArrayList<>(groupMap.values());
        int[][] dp = new int[group.size() + 1][K];

        for (int i = 1; i <= group.size(); i++) {
            Bucket b = group.get(i - 1);
            for (int j = 1; j < K; j++) {
                if (j - b.children >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - b.children] + b.amount);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[group.size()][K - 1]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) {
            parents[x] = y;
        } else if (y > x) {
            parents[y] = x;
        }
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static class Bucket {
        int children, amount;

        public Bucket(int children, int amount) {
            this.children = children;
            this.amount = amount;
        }
    }
}
