package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2098_외판원_순회 {
    static int INF = 999_999_999;
    static int N;
    static int[][] edge, dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edge = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                edge[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[N][1 << N]; // dist[a][b] 현재도시 a, 방문한 도시 리스트 b => 남은 도시 경유하는데 드는 비용

        int answer = tsp(0, 1);

        System.out.println(answer);
    }

    private static int tsp(int node, int visit) {
        // 전부 방문
        if (visit == (1 << N) - 1) {
            // 시작도시로 이동
            if (edge[node][0] == 0) {
                return INF;
            } else {
                return edge[node][0];
            }
        }

        if (dist[node][visit] != 0) {
            return dist[node][visit];
        }

        dist[node][visit] = INF;

        for (int next = 0; next < N; next++) {
            // 방문
            if ((visit & (1 << next)) != 0) {
                continue;
            }
            // edge 없음
            if (edge[node][next] == 0) {
                continue;
            }

            dist[node][visit] = Math.min(dist[node][visit], tsp(next, (visit | (1 << next))) + edge[node][next]);
        }

        return dist[node][visit];
    }
}
