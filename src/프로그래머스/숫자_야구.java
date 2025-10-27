package 프로그래머스;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 숫자_야구 {
    public static void main(String[] args) {
        숫자_야구 Main = new 숫자_야구();
        // 프로그래머스에서 실행할것
    }

    public int solution(int n, Function<Integer, String> submit) {
        int answer = 0;
        // 기회 n번
        // 4자리 수 => 1~9 서로다른 4개의 숫자로 이루어짐.
        // 9*8*7*6 = 3024
        // 6번 이내에 가능?

        int[] num = new int[10];

        int[][] expect = new int[4][10];

        List<Result> resultList = new ArrayList<>();

        // 스트라이크만 사용해서 정답 구하기
        answer = useStrike(n, submit);


//        for (int i = 1000; i <= 9999; i++)
//            if (submit.apply(i).equals("4S 0B")) return i;
        return answer;
    }

    private int useStrike(int n, Function<Integer, String> submit) {
        int answer = 0;

        boolean[] used = new boolean[10];
        // 각 자리수에 strike를 맞춤.

        for (int i = 3; i >= 0; i--) {
            int value = (int) Math.pow(10, i);
            for (int num = 1; num <= 9; num++) {
                if (used[num]) {
                    continue;
                }

                String result = submit.apply(answer + num * value);
                int S = result.charAt(0) - '0';
                int B = result.charAt(3) - '0';

                if (S == 4 - i) {
                    answer = answer + num * value;
                    used[num] = true;
                    break;
                }
            }
        }

        return answer;
    }

    private class Result {
        String input, output;

        public Result(String input, String output) {
            this.input = input;
            this.output = output;
        }
    }
}
