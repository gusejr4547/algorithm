package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P10775_공항 {
    static int G, P;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parents = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parents[i] = i;
        }
        int count = 0;
        for (int i = 0; i < P; i++) {
            int limit = Integer.parseInt(br.readLine());
            if (find(limit) == 0) break;
            count++;
            union(find(limit), find(limit) - 1);
        }

        System.out.println(count);
    }

    public static int find(int x) {
        if (x == parents[x])
            return x;

        return parents[x] = find(parents[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x > y) {
                parents[x] = y;
                ;
            } else {
                parents[y] = x;
            }
        }
    }
}
