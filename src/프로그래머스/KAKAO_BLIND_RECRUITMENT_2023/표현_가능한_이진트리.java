package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.Arrays;

public class 표현_가능한_이진트리 {
    public static void main(String[] args) {
        표현_가능한_이진트리 Main = new 표현_가능한_이진트리();
        long[] numbers = {7, 42, 5};
        System.out.println(Arrays.toString(Main.solution(numbers)));
    }

    public int[] solution(long[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            // 10진수 => 2진수
//            String binary = num2Binary(numbers[i]);
            String binary = Long.toBinaryString(numbers[i]);

            // 길이에 맞게 포화 이진트리
            // 1 3 7 15
            // 포화 이진트리 노드 개수 = 2^n -1
            int total = 1;
            while (binary.length() > total) {
                total = total * 2 + 1;
            }
            // 길이만큼 앞에 0으로 padding
            binary = "0".repeat(total - binary.length()) + binary;

            // binary의 중간이 root
            // 양 옆으로 재귀로 가면서 subtree의 root가 0이면 불가능
            if (isMake(binary, true, 0, binary.length() - 1)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean isMake(String str, boolean parent, int left, int right) {
        // 리프노드
        if (left == right) {
            if (!parent && str.charAt(left) == '1') {
                return false;
            } else {
                return true;
            }
        }

        int mid = (left + right) / 2;
        // 부모 노드와 비교 0 -> 1 이면 false
        if (!parent && str.charAt(mid) == '1') {
            return false;
        }

        // 왼쪽 && 오른쪽
        boolean current = (str.charAt(mid) == '1');
        return isMake(str, current, left, mid - 1) && isMake(str, current, mid + 1, right);
    }

    private String zeroPadding(String str, long length) {
        StringBuilder sb = new StringBuilder(str);

        while (sb.length() < length) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }

    private String num2Binary(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        return sb.reverse().toString();
    }

//    public int[] solution(long[] numbers) {
//        int[] answer = new int[numbers.length];
//
//        for (int i = 0; i < numbers.length; i++) {
//            String binary = num2binary(numbers[i]);
//            // 포화 이진트리 만들기
//            int depth = 1;
//            while (true) {
//                if (binary.length() <= Math.pow(2, depth) - 1) {
//                    binary = String.format("%" + ((int) Math.pow(2, depth) - 1) + "s", binary).replace(' ', '0');
//                    break;
//                }
//                depth++;
//            }
////            System.out.println(binary);
//            int mid = (binary.length() - 1) / 2;
//            if (binary.charAt(mid) == '0') {
//                answer[i] = 0;
//            } else {
//                boolean result = search(0, binary.length() - 1, true, binary);
//                answer[i] = result ? 1 : 0;
//            }
//        }
//
//
//        return answer;
//    }
//
//    // 0밑에 1이 있으면 안됨.... 조건 추가할 것
//    private boolean search(int left, int right, boolean expect, String binary) {
//        if (left == right) {
//            if (!expect && binary.charAt(left) == '1')
//                return false;
//            return true;
//        }
//
//        int mid = (left + right) / 2;
//        if (!expect && binary.charAt(mid) == '1') {
//            return false;
//        }
//
//        if (binary.charAt(mid) == '1') {
//            return search(left, mid - 1, true, binary) && search(mid + 1, right, true, binary);
//
//        } else {
//            return search(left, mid - 1, false, binary) && search(mid + 1, right, false, binary);
//        }
//
//    }
//
//    private String num2binary(long num) {
//        StringBuilder result = new StringBuilder();
//        while (num > 0) {
//            result.insert(0, num % 2);
//            num /= 2;
//        }
//        return result.toString();
//    }
}
