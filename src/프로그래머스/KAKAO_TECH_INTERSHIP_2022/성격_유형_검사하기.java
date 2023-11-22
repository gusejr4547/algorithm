package 프로그래머스.KAKAO_TECH_INTERSHIP_2022;

import java.util.HashMap;
import java.util.Map;

public class 성격_유형_검사하기 {
    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        System.out.println(solution(survey, choices));
    }

    /*
    R T
    C F
    J M
    A N
     */

    public static String solution(String[] survey, int[] choices) {
        int[] scoreResult = new int[26];

        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            int score = 0;
            char personality = 0;
            if (choice <= 3) {
                personality = survey[i].charAt(0);
                score = 4 - choice;
                scoreResult[personality - 'A'] += score;
            } else if (choice >= 5) {
                personality = survey[i].charAt(1);
                score = choice - 4;
                scoreResult[personality - 'A'] += score;
            }
        }

        StringBuilder answer = new StringBuilder();
        compare('R', 'T', answer, scoreResult);
        compare('C', 'F', answer, scoreResult);
        compare('J', 'M', answer, scoreResult);
        compare('A', 'N', answer, scoreResult);

        return answer.toString();
    }

    public static void compare(char first, char second, StringBuilder sb, int[] scoreResult){
        if (scoreResult[first - 'A'] >= scoreResult[second - 'A']) {
            sb.append(first);
        } else {
            sb.append(second);
        }
    }
}
