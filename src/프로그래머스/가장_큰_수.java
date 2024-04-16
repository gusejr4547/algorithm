package 프로그래머스;

import java.util.Arrays;

public class 가장_큰_수 {
    public static void main(String[] args) {
        가장_큰_수 Main = new 가장_큰_수();
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(Main.solution(numbers));
    }

    public String solution(int[] numbers) {
        String[] numStringArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStringArr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(numStringArr, (o1, o2) -> {
            while (true) {
                if (o1.length() == o2.length()) {
                    return o2.compareTo(o1);
                }
                if (o1.length() < o2.length()) {
                    for (int i = 0; i < o1.length(); i++) {
                        if (o1.charAt(i) != o2.charAt(i)) {
                            return o2.charAt(i) - o1.charAt(i);
                        }
                    }
                    o2 = o2.substring(o1.length());
                }
                if (o1.length() > o2.length()) {
                    for (int i = 0; i < o2.length(); i++) {
                        if (o1.charAt(i) != o2.charAt(i)) {
                            return o2.charAt(i) - o1.charAt(i);
                        }
                    }
                    o1 = o1.substring(o2.length());
                }
            }
        });

        StringBuilder result = new StringBuilder();
        for (String numString : numStringArr) {
            result.append(numString);
        }
        return result.toString().charAt(0) == '0' ? "0" : result.toString();
    }
}
