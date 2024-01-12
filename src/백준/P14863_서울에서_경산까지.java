package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14863_서울에서_경산까지 {
    static int N, K;
    static Road[] roads;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        roads = new Road[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int walkTime = Integer.parseInt(st.nextToken());
            int walkDonate = Integer.parseInt(st.nextToken());
            int bicycleTime = Integer.parseInt(st.nextToken());
            int bicycleDonate = Integer.parseInt(st.nextToken());

            roads[i] = new Road(walkTime, walkDonate, bicycleTime, bicycleDonate);
        }

        int[][] dp = new int[N + 1][K + 1];


        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i][roads[0].walkTime] = roads[0].walkDonate;
                dp[i][roads[0].bicycleTime] = Math.max(dp[i][roads[0].bicycleTime], roads[0].bicycleDonate);
            } else {
                for (int j = 1; j <= K; j++) {
                    if (dp[i - 1][j] == 0)
                        continue;
                    if (j + roads[i - 1].walkTime <= K) {
                        dp[i][j + roads[i - 1].walkTime] = Math.max(dp[i][j + roads[i - 1].walkTime], dp[i - 1][j] + roads[i - 1].walkDonate);
                    }
                    if (j + roads[i - 1].bicycleTime <= K) {
                        dp[i][j + roads[i - 1].bicycleTime] = Math.max(dp[i][j + roads[i - 1].bicycleTime], dp[i - 1][j] + roads[i - 1].bicycleDonate);
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= K; i++) {
            answer = Math.max(answer, dp[N][i]);
        }
        System.out.println(answer);

    }

    static class Road {
        int walkTime;
        int walkDonate;
        int bicycleTime;
        int bicycleDonate;

        public Road(int walkTime, int walkDonate, int bicycleTime, int bicycleDonate) {
            this.walkTime = walkTime;
            this.walkDonate = walkDonate;
            this.bicycleTime = bicycleTime;
            this.bicycleDonate = bicycleDonate;
        }
    }
}
