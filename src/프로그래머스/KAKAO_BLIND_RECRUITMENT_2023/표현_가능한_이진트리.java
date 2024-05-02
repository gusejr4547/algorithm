package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.Arrays;

public class 표현_가능한_이진트리 {
    public static void main(String[] args) {
        표현_가능한_이진트리 Main = new 표현_가능한_이진트리();
        long[] numbers = {7, 42, 5};
        System.out.println(Arrays.toString(Main.solution(numbers)));
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = num2binary(numbers[i]);
            // 포화 이진트리 만들기
            int depth = 1;
            while (true) {
                if (binary.length() <= Math.pow(2, depth) - 1) {
                    binary = String.format("%" + ((int) Math.pow(2, depth) - 1) + "s", binary).replace(' ', '0');
                    break;
                }
                depth++;
            }
//            System.out.println(binary);
            int mid = (binary.length() - 1) / 2;
            if (binary.charAt(mid) == '0') {
                answer[i] = 0;
            } else {
                boolean result = search(0, binary.length() - 1, true, binary);
                answer[i] = result ? 1 : 0;
            }
        }


        return answer;
    }

    // 0밑에 1이 있으면 안됨.... 조건 추가할 것
    private boolean search(int left, int right, boolean expect, String binary) {
        if (left == right) {
            if (!expect && binary.charAt(left) == '1')
                return false;
            return true;
        }

        int mid = (left + right) / 2;
        if (!expect && binary.charAt(mid) == '1') {
            return false;
        }

        if (binary.charAt(mid) == '1') {
            return search(left, mid - 1, true, binary) && search(mid + 1, right, true, binary);

        } else {
            return search(left, mid - 1, false, binary) && search(mid + 1, right, false, binary);
        }

    }

    private String num2binary(long num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            result.insert(0, num % 2);
            num /= 2;
        }
        return result.toString();
    }
}
