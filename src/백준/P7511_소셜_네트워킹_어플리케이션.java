package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.IntUnaryOperator;

public class P7511_소셜_네트워킹_어플리케이션 {
    //

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= t; tc++) {
            sb.append("Scenario ").append(tc).append(":\n");

            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());

            int[] parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // a와 b는 친구
                union(a, b, parents);
            }
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (find(u, parents) == find(v, parents)) {
                    sb.append(1).append('\n');
                } else {
                    sb.append(0).append('\n');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void union(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x != y) {
            parents[x] = y;
        }
    }

    private static int find(int x, int[] parents) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x], parents);
    }
}
