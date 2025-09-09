package 코드트리.HSAT;

import java.util.Scanner;

public class 통근버스_출발_순서_검증하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // N <= 5000
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int up = 0;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] < arr[j]) {
                    up++;
                }
                if (arr[i] > arr[j]) {
                    answer += up;
                }
            }
        }

        System.out.println(answer);
    }
}
