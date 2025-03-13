package 코드트리;

import java.util.Arrays;
import java.util.Scanner;

public class _2명의_도둑 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        // 2명, 겹치기는 불가능

        int[][] maxValues = new int[n][n - m + 1]; // value[y][x] (y,x) 부터 m개 선택했을때 가치 최대값

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - m; j++) {
                // 최대 무게 담는 방법 => dp로 해결
                int[][] dp = new int[m + 1][c + 1];
                for (int k = 0; k < m; k++) {
                    int weight = grid[i][j + k];
                    for (int maxWeight = 1; maxWeight <= c; maxWeight++) {
                        if (maxWeight - weight < 0)
                            dp[k + 1][maxWeight] = dp[k][maxWeight];
                        else {
                            dp[k + 1][maxWeight] = Math.max(dp[k][maxWeight], dp[k][maxWeight - weight] + weight * weight);
                        }
                    }
                }

                maxValues[i][j] = dp[m][c];
            }
        }

//        for (int[] mv : maxValues) {
//            System.out.println(Arrays.toString(mv));
//        }

        int answer = 0;
        // 2개 선택
        for (int a = 0; a < n; a++) {
            for (int b = a; b < n; b++) {
                if (a == b) {
                    // 같은 라인을 선택한 경우 => 2명 선택이 가능 한지? => 가능하면 조합들 중 최대
                    int sum = getSameLineMaxValue(a, m, maxValues);
                    answer = Math.max(answer, sum);
                } else {
                    // 다른 라인을 선택한 경우 => 각 라인의 최대값
                    int aMax = getMaxValue(a, maxValues);
                    int bMax = getMaxValue(b, maxValues);

                    answer = Math.max(answer, aMax + bMax);
                }
            }
        }

        System.out.println(answer);
    }

    private static int getSameLineMaxValue(int line, int m, int[][] maxValues) {
        // 2명 같은 줄 선택 불가
        if (maxValues[0].length <= m) return 0;

        int maxValue = 0;
        for (int a = 0; a < maxValues[0].length - m; a++) {
            for (int b = a + m; b < maxValues[0].length; b++) {
                maxValue = Math.max(maxValue, maxValues[line][a] + maxValues[line][b]);
            }
        }

        return maxValue;
    }

    private static int getMaxValue(int line, int[][] maxValues) {
        int maxValue = 0;
        for (int i = 0; i < maxValues[0].length; i++) {
            maxValue = Math.max(maxValue, maxValues[line][i]);
        }
        return maxValue;
    }
}
