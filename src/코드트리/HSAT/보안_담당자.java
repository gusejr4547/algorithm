package 코드트리.HSAT;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 보안_담당자 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String S = sc.nextLine();
        int len = S.length();

        if (len % 2 != 0) {
            System.out.println("No");
            return;
        }

        int close = 0;
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == ')') {
                close++;
            }
        }

        boolean isValid = true;
        int closeNeed = len / 2 - close;
        int check = 0;

        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);

            if (')' == c) {
                check--;
            } else if ('(' == c) {
                check++;
            } else if ('?' == c) {
                if (closeNeed > 0) {
                    check--;
                    closeNeed--;
                } else {
                    check++;
                }
            }

            // 뒤에서 부터 계산했을 때 들어온 사람이 더 많음
            if (check > 0) {
                isValid = false;
                break;
            }
        }

        System.out.println(isValid ? "Yes" : "No");
    }
}
