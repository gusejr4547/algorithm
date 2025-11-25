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

        // 스트라이크만 사용해서 정답 구하기
//        answer = useStrike(n, submit);

        // 스트라이크 + 볼 정보 활용
        answer = useStrikeBall(n, submit);


//        for (int i = 1000; i <= 9999; i++)
//            if (submit.apply(i).equals("4S 0B")) return i;
        return answer;
    }

    private int useStrikeBall(int n, Function<Integer, String> submit) {
        int answer = 0;
        int fix = 0;
        List<Integer> numList = new ArrayList<>();
        boolean[] select = new boolean[10];
        boolean[] used = new boolean[10];

        // 1000
        int value = (int) Math.pow(10, 3);
        for (int num = 1; num <= 9; num++) {
            String result = submit.apply(num * value);
            int S = result.charAt(0) - '0';
            int B = result.charAt(2) - '0';

            // S > 0 or B > 0 면 숫자 포함.
            if (S > 0 || B > 0) {
                select[num] = true;
                numList.add(num);
            }

            // 스트라이크면 answer에 반영
            if (S > fix) {
                fix++;
                used[num] = true;
                answer = num * value;
            }
        }

        // 100
        value = value / 10;
        for (int num : numList) {
            // 사용한거 => 필요없음
            if (used[num]) {
                continue;
            }

            String result = submit.apply(answer + num * value);
            int S = result.charAt(0) - '0';
            int B = result.charAt(2) - '0';

            // strike 늘어나는거 찾음.
            if (S > fix) {
                fix++;
                used[num] = true;
                answer = answer + num * value;
                break;
            }
        }

        // 10, 1 남은 경우는 2가지 뿐
        List<Integer> remain = new ArrayList<>();
        for (int num : numList) {
            if (!used[num]) {
                remain.add(num);
            }
        }

        int num1 = remain.get(0);
        int num2 = remain.get(1);

        int guess = answer + num1 * 10 + num2;
        String result = submit.apply(guess);
        int S = result.charAt(0) - '0';
        if (S == fix) {
            guess = answer + num2 * 10 + num1;
            submit.apply(guess);
        }
        return guess;
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
