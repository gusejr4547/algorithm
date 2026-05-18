package LeetCode.Weekly_Contest_502;

public class Q1_Check_Adjacent_Digit_Differences {
    // 숫자를 포함한 s
    // 연속된 2개 짝의 차이가 2이하.

    public boolean isAdjacentDiffAtMostTwo(String s) {
        for (int i = 1; i < s.length(); i++) {
            int a = s.charAt(i - 1) - '0';
            int b = s.charAt(i) - '0';
            if (Math.abs(a - b) > 2) {
                return false;
            }
        }
        return true;
    }
}
