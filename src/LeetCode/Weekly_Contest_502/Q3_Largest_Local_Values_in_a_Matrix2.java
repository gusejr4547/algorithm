package LeetCode.Weekly_Contest_502;

public class Q3_Largest_Local_Values_in_a_Matrix2 {
    // nxm 격자
    // 0이 아닌 숫자가 있으면 그거를 중심으로 x 범위의 사각형, 4개의 꼭지점 빼고 영역이다.
    // 그안에 다른 숫자가 x보다 작으면 local maximum 이다.
    // 이거 개수 구하기

    public int countLocalMaximums(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // matrix 값이 <= 200 이다
        // 2차원 누적합, x값보다 큰게 몇개있는지 미리 기록
        int[][][] pSum = new int[201][n + 1][m + 1];

        for (int x = 1; x < 200; x++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (matrix[i - 1][j - 1] > x) {
                        pSum[x][i][j] = pSum[x][i - 1][j] + pSum[x][i][j - 1] - pSum[x][i - 1][j - 1] + 1;
                    } else {
                        pSum[x][i][j] = pSum[x][i - 1][j] + pSum[x][i][j - 1] - pSum[x][i - 1][j - 1];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > 0) {
                    int x = matrix[i][j];
                    // 사각형 좌표
                    int r1 = i - x < 0 ? 0 : i - x;
                    int r2 = i + x >= n ? n - 1 : i + x;
                    int c1 = j - x < 0 ? 0 : j - x;
                    int c2 = j + x >= m ? m - 1 : j + x;
                    // 영역 내에 x보다 큰값 개수 pSum이용
                    int count = pSum[x][r2 + 1][c2 + 1] - pSum[x][r2 + 1][c1] - pSum[x][r1][c2 + 1] + pSum[x][r1][c1];

                    // 4개의 꼭지점은 실제 값 보고 계산
                    if (i - x >= 0 && j - x >= 0 && matrix[i - x][j - x] > x) {
                        count--;
                    }
                    if (i - x >= 0 && j + x < m && matrix[i - x][j + x] > x) {
                        count--;
                    }
                    if (i + x < n && j - x >= 0 && matrix[i + x][j - x] > x) {
                        count--;
                    }
                    if (i + x < n && j + x < m && matrix[i + x][j + x] > x) {
                        count--;
                    }

                    // x보다 큰게 없으면 answer 증가
                    if (count == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}
