package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;

public class 테이블_해시_함수 {
    public static void main(String[] args) {
        테이블_해시_함수 Main = new 테이블_해시_함수();
        int[][] data = {{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        System.out.println(Main.solution(data, col, row_begin, row_end));
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            return o1[col - 1] == o2[col - 1] ? o2[0] - o1[0] : o1[col - 1] - o2[col - 1];
        });
        int answer = 0;

        for (int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for (int e : data[i]) {
                sum += e % (i + 1);
            }
//            System.out.println("sum = " + sum);
            answer ^= sum;
        }

        return answer;
    }
}
