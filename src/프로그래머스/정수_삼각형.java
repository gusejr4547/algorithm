package 프로그래머스;

public class 정수_삼각형 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        정수_삼각형 Main = new 정수_삼각형();
        System.out.println(Main.solution(triangle));
    }

    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                // 위에꺼
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (0 <= j - 1 && j < i) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                }
            }
        }


        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp[N-1][i]);
        }

        return answer;
    }
}
