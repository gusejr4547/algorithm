package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P14938_서강그라운드 {
    static int n, m, r;
    static int[] item;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], 987654321);
            dist[i][i] = 0;
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = l;
            dist[b][a] = l;
        }

        floydWarshall();

//        for(int[] d : dist){
//            System.out.println(Arrays.toString(d));
//        }
        int answer = 0;
        for (int start = 1; start <= n; start++) {
            int itemSum = 0;
            for (int area = 1; area <= n; area++) {
                if (dist[start][area] <= m) {
                    itemSum += item[area];
                }
            }
            answer = Math.max(answer, itemSum);
        }

        System.out.println(answer);
    }

    private static void floydWarshall() {
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    dist[start][end] = Math.min(dist[start][end], dist[start][mid] + dist[mid][end]);
                }
            }
        }
    }
}
