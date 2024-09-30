package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2616_소형기관차 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] trainPeople = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            trainPeople[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        // 소형기관차는 3대 배치
        // 하나당 M개의 객차를 끌 수 있다.
        // 각 소형 기관차는 번호가 연속적으로 이어진 객차를 끌게 한다.
        // 객차는 기관차 바로 뒤에 있는 객차부터 시작하여 1번 부터 차례로 번호가 붙어있다

        int[] prefixSUm = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSUm[i] = prefixSUm[i - 1] + trainPeople[i];
        }

        int[][] dp = new int[4][N + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = i * M; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + prefixSUm[j] - prefixSUm[j - M]);
            }
        }

        System.out.println(dp[3][N]);
    }
}
