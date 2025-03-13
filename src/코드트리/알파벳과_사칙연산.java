package 코드트리;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 알파벳과_사칙연산 {
    static int maxValue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.next();
        // Please write your code here.
        // a ~ f 까지, + - * 기호만 사용됨
        // 연산 순위는 모두 같다고 가정
        // 각 알파벳에 적절한 숫자를 대입해서 연산 최대값 만들기

        // 4^6 가지 경우의 수 존재 가능
        Map<Character, Integer> charMap = new HashMap<>();
        maxValue = Integer.MIN_VALUE;
        mappingChar(0, charMap, expression);
        System.out.println(maxValue);
    }

    private static void mappingChar(int charIdx, Map<Character, Integer> charMap, String expression) {
        if (charIdx == 6) {
            // 계산
            int result = calculateExpression(expression, charMap);
            maxValue = Math.max(maxValue, result);
            return;
        }

        for (int num = 1; num <= 4; num++) {
            charMap.put((char) ('a' + charIdx), num);
            mappingChar(charIdx + 1, charMap, expression);
        }
    }

    private static int calculateExpression(String expression, Map<Character, Integer> charMap) {
        int result = 0;

        for (int i = 0; i < expression.length(); i++) {
            char v = expression.charAt(i);
            if ('+' == v) {
                result = result + charMap.get(expression.charAt(i + 1));
                i++;
            } else if ('-' == v) {
                result = result - charMap.get(expression.charAt(i + 1));
                i++;
            } else if ('*' == v) {
                result = result * charMap.get(expression.charAt(i + 1));
                i++;
            } else {
                result = charMap.get(v);
            }
        }
        return result;
    }
}
