package 프로그래머스.코드챌린지;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 봉인된_주문 {
    public static void main(String[] args) {
        봉인된_주문 Main = new 봉인된_주문();
//        long n = 7388;
//        String[] bans = {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"};
        long n = 30;
        String[] bans = {"d", "e", "bb", "aa", "ae"};
        System.out.println(Main.solution(n, bans));
    }

    // 주문의 문자는 최대 11글자
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());
        for (String ban : bans) {
            // ban이 몇번째? >> n보다 같거나 작다면 n+=1
            long order = ordering(ban);
            if (order <= n) {
                n += 1;
            }
        }
        // 최종 n번째 문자열 구하기
        String answer = orderToSpell(n);

        return answer;
    }

    private long ordering(String spell) {
        long result = 0;

        int pow = 0;
        for (int i = spell.length() - 1; i >= 0; i--) {
            result += (long) ((spell.charAt(i) - 'a' + 1) * Math.pow(26, pow));
            pow++;
        }

        return result;
    }

    private String orderToSpell(long order) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (order > 0) {
            order--; // 0부터 시작해야함. 1이오면 a이기 때문에 27이오면 26%26=>0=>a 26/26=>1, 0%26=>0=>a
            stack.push((int) (order % 26));
            order = order / 26;
        }

        // stack의 숫자 >> 알파벳의 순서 번호
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            int alphaNum = stack.pop();
            char alpha = (char) ('a' + alphaNum);
            result.append(alpha);
        }

        return result.toString();
    }
}
