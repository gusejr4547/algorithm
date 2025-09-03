package 코드트리.HSAT;

import java.util.Arrays;
import java.util.Scanner;

public class 염기서열_커버 {
    static int N, M;
    static String[] sequences, superDNA;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sequences = new String[N];
        for (int i = 0; i < N; i++) {
            sequences[i] = sc.next();
        }

        // N개의 문자열이 있음. N개 문자열 모든 조합 2^N 개임. N개의 원소 집합에서 부분집합 개수와 같다.
        superDNA = new String[1 << N]; // 조합에 맞는 superDNA 계산
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append('.');
        }
        superDNA[0] = sb.toString();
        for (int i = 1; i < (1 << N); i++) {
            // 1110 이 조합일 때  1100 조합과 0010 조합을 더하는 방식으로 조합 계산
            makeSuperDNA(i);
        }

//        System.out.println(Arrays.toString(sequences));
//        System.out.println(Arrays.toString(superDNA));

        int[] cost = new int[1 << N];
        // 조합 비용 계산
        for (int i = 1; i < (1 << N); i++) {
            // 조합 가능함
            if (!superDNA[i].isEmpty()) {
                cost[i] = 1;
            }
            // 조합 불가능
            else {
                cost[i] = 1000;

                // i의 부분집합 계산
                // j는 가장 큰 부분집합부터 작은 순으로 탐색
                for (int j = (i - 1) & i; j > 0; j = (j - 1) & i) {
                    if (!superDNA[i - j].isEmpty()) {
                        if (cost[i] > cost[j] + 1) {
                            cost[i] = cost[j] + 1;
                        }
                    }
                }
            }
        }

        System.out.println(cost[(1 << N) - 1]);
    }

    private static void makeSuperDNA(int index) {
        int loc = 0;
        int temp = index;
        while (temp % 2 == 0) {
            temp = temp / 2;
            loc++;
        }

        superDNA[index] = merge(sequences[loc], superDNA[index - (1 << loc)]);
    }

    private static String merge(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // 1글자씩 비교
        for (int i = 0; i < M; i++) {
            if (s1.charAt(i) == '.' && s2.charAt(i) == '.') {
                sb.append('.');
            } else if (s1.charAt(i) == '.') {
                sb.append(s2.charAt(i));
            } else if (s2.charAt(i) == '.') {
                sb.append(s1.charAt(i));
            } else if (s1.charAt(i) == s2.charAt(i)) {
                sb.append(s1.charAt(i));
            }
            // 다르면 끝
            else {
                return "";
            }
        }
        return sb.toString();
    }
}
