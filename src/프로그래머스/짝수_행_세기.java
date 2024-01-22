package 프로그래머스;

import java.util.Arrays;

public class 짝수_행_세기 {
    public static void main(String[] args) {
        짝수_행_세기 Main = new 짝수_행_세기();
        int[][] a = {{1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 1}};
        System.out.println(Main.solution(a));
    }

    final int MOD = 10_000_019;

    public int solution(int[][] a) {
        int answer = -1;
        int row = a.length;
        int col = a[0].length;


        int[] oneCountByCol = new int[col+1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (a[i][j] == 1)
                    oneCountByCol[j]++;
            }
        }

        long[][] comb = new long[row + 1][row + 1];
        comb[0][0] = 1;
        combination(comb, row);

//        System.out.println(Arrays.deepToString(comb));

        long[][] dp = new long[col + 2][row + 1];
        dp[1][row - oneCountByCol[0]] = comb[row][row - oneCountByCol[0]];

        for (int curCol = 1; curCol < col; curCol++) {
            int oneCount = oneCountByCol[curCol];
            for (int evenCount = 0; evenCount <= row; evenCount++) {
                if (dp[curCol][evenCount] == 0) continue;

                for (int one = 0; one <= oneCount; one++) {
                    int oddCount = evenCount - one;
                    // 1짝수개에서 선택 X + 1홀수개에서 선택
                    int nextEvenCount = oddCount + (oneCount - one);

                    if (nextEvenCount > row || evenCount < one)
                        continue;

                    long nextCase = (comb[evenCount][one] * comb[row - evenCount][oneCount - one]) % MOD;

                    dp[curCol + 1][nextEvenCount] = (dp[curCol + 1][nextEvenCount] + dp[curCol][evenCount] * nextCase) % MOD;
                }
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        answer = (int) dp[col][row];
        return answer;
    }

    public void combination(long[][] comb, int row) {
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j <= row; j++) {
                if (j == 0) {
                    comb[i][j] = 1;
                } else if (j == i) {
                    comb[i][j] = 1;
                } else {
                    comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
                }
            }
        }
    }
}
