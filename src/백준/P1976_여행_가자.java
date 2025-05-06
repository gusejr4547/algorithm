package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P1976_여행_가자 {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] adjMatrix = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] tripPlan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            tripPlan[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (adjMatrix[i][j] == 1)
                    union(i, j);
            }
        }

        Set<Integer> section = new HashSet<>();
        for (int i = 0; i < M; i++) {
            section.add(find(tripPlan[i]));
        }

        if (section.size() == 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
