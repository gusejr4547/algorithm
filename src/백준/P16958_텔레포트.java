package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16958_텔레포트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        City[] cities = new City[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // s == 1 특별 => 텔레포트 가능
            cities[i] = new City(s, x, y);
        }

        // 가장 가까운 텔레포트 도시
        int[] nearTp = new int[N + 1];
        Arrays.fill(nearTp, 999_999_999);

        for (int i = 1; i <= N; i++) {
            if (cities[i].s == 1) {
                nearTp[i] = 0;
            }

            for (int j = 1; j <= N; j++) {
                if (cities[j].s == 1) {
                    nearTp[i] = Math.min(nearTp[i], getDist(cities[i].x, cities[i].y, cities[j].x, cities[j].y));
                }
            }
        }

//        int[][] dist = new int[N + 1][N + 1];
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                if (i == j) {
//                    dist[i][j] = 0;
//                    continue;
//                }
//                dist[i][j] = getDist(cities[i].x, cities[i].y, cities[j].x, cities[j].y);
//                if (cities[i].s == 1 && cities[j].s == 1) {
//                    dist[i][j] = Math.min(dist[i][j], T);
//                }
//            }
//        }
//
//        for (int k = 1; k <= N; k++) {
//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= N; j++) {
//                    // i에서 j로 가는 거리 vs k를 중간에 경유하는 거리
//                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
//                }
//            }
//        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

//            sb.append(dist[A][B]).append('\n');
            int walk = getDist(cities[A].x, cities[A].y, cities[B].x, cities[B].y);

            // 텔레포트
            int tp = nearTp[A] + T + nearTp[B];

            sb.append(Math.min(walk, tp)).append('\n');
        }

        System.out.println(sb);
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class City {
        int s, x, y;

        public City(int s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
    }
}
