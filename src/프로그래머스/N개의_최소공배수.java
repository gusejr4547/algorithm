package 프로그래머스;

public class N개의_최소공배수 {
    public int solution(int[] arr) {
        int answer = 1;

        for (int num : arr) {
            // 최소공배수 구하기
            int d = gcd(num, answer);
            // answer하고 num 곱한거 d로 나누기
            answer = answer * num / d;
        }

        return answer;
    }

    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }

        if (x >= y) {
            return gcd(y, x % y);
        } else {
            return gcd(x, y % x);
        }
    }
}
