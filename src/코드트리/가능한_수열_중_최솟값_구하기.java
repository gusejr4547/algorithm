package 코드트리;

import java.util.Arrays;
import java.util.Scanner;

public class 가능한_수열_중_최솟값_구하기 {
    static int N;
    static boolean exit;
    static String answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        // Please write your code here.

        int[] seq = new int[N];
        exit = false;
        makeSequence(seq, 0);
        System.out.println(answer);
    }

    private static void makeSequence(int[] seq, int seqIdx) {
        // 찾으면 빨리 종료
        if (exit) return;

        // 끝내는 조건
        if (seqIdx == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(seq[i]);
            }
            answer = sb.toString();
            exit = true;
            return;
        }

        for (int num = 4; num <= 6; num++) {
            seq[seqIdx] = num;
            // 새로 추가한 숫자를 포함한 인접 수열을 확인한다.
            if (isValid(seq, seqIdx + 1)) {
                makeSequence(seq, seqIdx + 1);
            }
        }
    }

    private static boolean isValid(int[] seq, int seqLen) {
        // 새로 추가한 숫자를 포함한 수열 비교
        for (int length = 1; length <= seqLen / 2; length++) {
            boolean isEqual = true;
            for (int i = 0; i < length; i++) {
                if (seq[seqLen - length + i] != seq[seqLen - (length * 2) + i]) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) return false;
        }
        return true;
    }
}
