package 프로그래머스;

import java.util.Arrays;

public class K진수에서_소수_개수_구하기 {
    public static void main(String[] args) {
        K진수에서_소수_개수_구하기 Main = new K진수에서_소수_개수_구하기();
        int n = 110011;
        int k = 10;
        System.out.println(Main.solution(n, k));
    }

    public int solution(int n, int k) {
        // k진수로 변경
        String num = changeNotation(n, k);
//        System.out.println(num);

        String[] splitNum = num.split("0");
//        System.out.println(Arrays.toString(splitNum));

        int answer = 0;
        // split 된 것 중에 소수 있나?
        for (String number : splitNum) {
            if (!number.isEmpty() && isPrime(Long.parseLong(number))) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(long n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private String changeNotation(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % k);
            n = n / k;
        }

        return sb.reverse().toString();
    }
}
