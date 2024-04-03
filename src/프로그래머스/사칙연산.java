package 프로그래머스;

public class 사칙연산 {
    public static void main(String[] args) {
        사칙연산 Main = new 사칙연산();
        String[] arr = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};
        System.out.println(Main.solution(arr));
    }

    public int solution(String arr[]) {
        int size = arr.length / 2 + 1;

        int[][][] dp = new int[2][size][size]; // min,max / 시작 / 끝

        int[] numArr = new int[size];
        char[] operationArr = new char[size - 1];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0)
                numArr[i / 2] = Integer.parseInt(arr[i]);
            else
                operationArr[i / 2] = arr[i].charAt(0);
        }

        for (int step = 0; step < size; step++) {
            for (int i = 0; i < size - step; i++) {
                int j = i + step;

                if (step == 0) {
                    dp[0][i][j] = numArr[i];
                    dp[1][i][j] = numArr[i];
                } else {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;

                    for (int k = i; k < j; k++) {
                        if (operationArr[k] == '+') {
                            max = Math.max(max, dp[1][i][k] + dp[1][k + 1][j]);
                            min = Math.min(min, dp[1][i][k] + dp[1][k + 1][j]);
                        } else {
                            max = Math.max(max, dp[1][i][k] - dp[0][k + 1][j]);
                            min = Math.min(min, dp[0][i][k] - dp[1][k + 1][j]);
                        }
                    }

                    dp[0][i][j] = min;
                    dp[1][i][j] = max;
                }
            }
        }

        return dp[1][0][size - 1];
    }
}
