package 코드트리;

import java.util.Scanner;

public class 아름다운_수 {
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.

        // 1~4 정수
        // N자리 수
        // 1, 22, 333, 4444 4가지 선택해서 N자리 수 만드는 경우의 수랑 같음

        makeBeautifulNumber(0, n);

        System.out.println(result);
    }

    private static void makeBeautifulNumber(int currentNumberLength, int numberLength) {
        if (currentNumberLength == numberLength) {
            result++;
            return;
        }
        if (currentNumberLength > numberLength) {
            return;
        }

        for (int length = 1; length <= 4; length++) {
            makeBeautifulNumber(currentNumberLength + length, numberLength);
        }
    }
}
