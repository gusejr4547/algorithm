package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P4195_친구_네트워크 {
    static int T;
    static int[] parent, rank;

    // 유니온 - 파인드
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            rank = new int[F * 2];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                if (!map.containsKey(friend1))
                    map.put(friend1, idx++);
                if (!map.containsKey(friend2))
                    map.put(friend2, idx++);

                sb.append(union(map.get(friend1), map.get(friend2))).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static int find(int x) {
        if (x == parent[x])
            return x;

        return parent[x] = find(parent[x]);
    }

    public static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x > y) {
                parent[x] = y;
                rank[y] += rank[x];
                return rank[y];
            } else {
                parent[y] = x;
                rank[x] += rank[y];

            }
        }

        return rank[x];
    }
}
