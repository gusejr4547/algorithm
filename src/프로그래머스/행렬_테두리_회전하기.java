package 프로그래머스;

import java.util.Arrays;

public class 행렬_테두리_회전하기 {
    public static void main(String[] args) {
        행렬_테두리_회전하기 Main = new 행렬_테두리_회전하기();
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(Main.solution(rows, columns, queries)));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = i * columns + j + 1;
            }
        }

        int[] answer = new int[queries.length];
        // {x1,y1,x2,y2} 회전 범위 x가 row, y가 column
        // x1 -> x2, y1 -> y2
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;

            int minValue = map[x1][y1];
            // 회전
            int tmp = map[x1][y1];
            for (int x = x1; x < x2; x++) {
                map[x][y1] = map[x + 1][y1];
                minValue = Math.min(minValue, map[x][y1]);
            }
            for (int y = y1; y < y2; y++) {
                map[x2][y] = map[x2][y + 1];
                minValue = Math.min(minValue, map[x2][y]);
            }
            for (int x = x2; x > x1; x--) {
                map[x][y2] = map[x - 1][y2];
                minValue = Math.min(minValue, map[x][y2]);
            }
            for (int y = y2; y > y1; y--) {
                map[x1][y] = map[x1][y - 1];
                minValue = Math.min(minValue, map[x1][y]);
            }
            map[x1][y1 + 1] = tmp;

            answer[i] = minValue;
        }
        return answer;
    }
}
