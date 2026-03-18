package 프로그래머스.카카오_2025_하반기_2차;

import java.util.Arrays;

public class 선인장_숨기기 {
    public static void main(String[] args) throws Exception {
        선인장_숨기기 Main = new 선인장_숨기기();
        int m = 4;
        int n = 5;
        int h = 2;
        int w = 2;
        int[][] drops = {{0, 0}, {3, 1}, {1, 3}, {2, 4}, {1, 1}, {2, 2}, {2, 3}, {0, 4}};
        System.out.println(Arrays.toString(Main.solution(m, n, h, w, drops)));
    }

    // 선인장 구역 크기는 wxh
    // 선인장이 가장 늦게 비를 맞도록 구역을 정하기. 안맞아도 됨
    // 후보가 여러개이면 위쪽, 왼쪽 작은 것 선택
    // 선인장 구역의 왼쪽 위 칸 좌표를 return

    // m, n <= 500 000
    // m*n <= 500 000
    int M, N, H, W;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        N = m;
        M = n;
        H = h;
        W = w;
        // 2차원 누적합으로 0이 되는 영역을 찾기


        int[] answer = new int[2];
        int l = 1;
        int r = drops.length;
        while (l <= r) {
            int mid = (l + r) / 2;

            // mid 시간에 h*w 크기의 빈공간이 존재하는가?
            int[] p = isValid(mid, drops);
            if (p[0] != -1) {
                l = mid + 1;
                answer = p;
            } else {
                r = mid - 1;
            }
        }

        return answer;
    }

    private int[] isValid(int time, int[][] drops) {
        int[][] grid = new int[N][M];
        for (int i = 0; i < time; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            grid[r][c] = 1;
        }

        int[][] prefixSum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= N - H + 1; i++) {
            for (int j = 1; j <= M - W + 1; j++) {
                // i,j를 왼쪽 상단으로하는 HxW 크기 누적합
                int sum = prefixSum[i + H - 1][j + W - 1] - prefixSum[i - 1][j + W - 1] - prefixSum[i + H - 1][j - 1] + prefixSum[i - 1][j - 1];
                // 0이 있으면 ok
                if (sum == 0) {
                    return new int[]{i - 1, j - 1};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
