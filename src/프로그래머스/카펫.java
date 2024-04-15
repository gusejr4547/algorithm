package 프로그래머스;

import java.util.Arrays;

public class 카펫 {
    public static void main(String[] args) {
        카펫 Main = new 카펫();
        int brown = 10;
        int yellow = 2;
        System.out.println(Arrays.toString(Main.solution(brown, yellow)));
    }

    public int[] solution(int brown, int yellow) {
        // x y
        // brown / 2 + 2 = x + y;
        // x * y - yellow = brown;
        int[] answer = {};
        int x = 1;
        while (true) {
            int y = brown / 2 + 2 - x;
            if (x * y - yellow == brown) {
                answer = new int[]{y, x};
                break;
            }
            x++;
        }

        return answer;
    }
}
