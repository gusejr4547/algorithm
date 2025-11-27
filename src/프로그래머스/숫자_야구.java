package 프로그래머스;

import java.util.*;
import java.util.function.Function;

public class 숫자_야구 {
    public static void main(String[] args) {
        숫자_야구 Main = new 숫자_야구();
        int hiddenNum = 2619;

        Function<Integer, String> submit = guess -> {
            int[] SB = Main.getStrikeBall(hiddenNum, guess);
            return SB[0] + "S " + SB[1] + "B";
        };

        int answer = Main.solution(6, submit);
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
//        answer = useStrikeBall(n, submit);

        // 최적 minimax?
        answer = predict(n, submit);

        return answer;
    }

    private int predict(int n, Function<Integer, String> submit) {
        // 전체 경우의 수.
        List<Integer> candidate = new ArrayList<>();
        generateCandidate(candidate);

        // 각 단계를 거치며 가장 필터링이 많이 되는 경우를 선택...

        // 가장 처음은 1234로 지정
        String result = submit.apply(1234);
        // candidate 중에 result에 부합하는 결과만 남기기
        candidate = filter(candidate, 1234, result.charAt(0) - '0', result.charAt(3) - '0');

        // 가장 최적의 질의를 찾고 후보 필터링 반복
        while (candidate.size() > 1) {
            int guess = chooseBestGuess(candidate);
            result = submit.apply(guess);
            // candidate 중에 result에 부합하는 결과만 남기기
            candidate = filter(candidate, guess, result.charAt(0) - '0', result.charAt(3) - '0');
        }

        return candidate.get(0);
    }

    private int chooseBestGuess(List<Integer> candidate) {
        int bestGuess = 0;
        int bestWorst = Integer.MAX_VALUE;

        for (int guess : candidate) { // 질의할꺼
            Map<String, Integer> map = new HashMap<>(); // guess로 질의했을때 각 결과(0S0B ~ 4S0B) 몇개로 나누어지나?
            int worst = 0; // map 중 가장 큰 값.
            for (int answer : candidate) { // answer 정답인 경우
                int[] SB = getStrikeBall(answer, guess);
                String key = SB[0] + "_" + SB[1];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for (int cnt : map.values()) {
                worst = Math.max(worst, cnt);
            }

            // 이번 guess의 worst가 bestWorst 보다 작으면 갱신
            if (worst < bestWorst) {
                bestGuess = guess;
                bestWorst = worst;
            }
        }

        return bestGuess;
    }

    private List<Integer> filter(List<Integer> candidate, int guess, int strike, int ball) {
        List<Integer> filteredCandidate = new ArrayList<>();

        for (int c : candidate) {
            int[] SB = getStrikeBall(c, guess);

            if (SB[0] == strike && SB[1] == ball) {
                filteredCandidate.add(c);
            }
        }

        return filteredCandidate;
    }

    private int[] getStrikeBall(int answer, int guess) {
        String a = String.valueOf(answer);
        String g = String.valueOf(guess);

        Set<Character> gSet = new HashSet<>();
        for (char c : g.toCharArray()) {
            gSet.add(c);
        }

        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 4; i++) {
            if (a.charAt(i) == g.charAt(i)) {
                strike++;
            } else if (gSet.contains(a.charAt(i))) {
                ball++;
            }
        }

        return new int[]{strike, ball};
    }

    private void generateCandidate(List<Integer> list) {
        boolean[] used = new boolean[10];
        List<Integer> current = new ArrayList<>();
        // 중복없는 4자리 수 생성.
        selectNum(current, used, list);
    }

    private void selectNum(List<Integer> current, boolean[] used, List<Integer> list) {
        if (current.size() == 4) {
            list.add(current.get(0) * 1000 + current.get(1) * 100 + current.get(2) * 10 + current.get(3));
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            current.add(i);
            selectNum(current, used, list);
            current.remove(current.size() - 1);
            used[i] = false;
        }
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
}
