package 코드트리;

import java.util.*;

public class 올바른_등식_만들기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = sc.nextInt();
        }
        // Please write your code here.
        int offset = 20;

        long[][] make = new long[N + 1][offset * 2 + 1];
        make[0][offset] = 1;

        for (int i = 1; i <= N; i++) {
            for (int sum = 0; sum <= offset * 2; sum++) {
                if (make[i - 1][sum] >= 1) {
                    if (0 <= sum + numbers[i] && sum + numbers[i] <= offset * 2) {
                        make[i][sum + numbers[i]] += make[i - 1][sum];
                    }
                    if (0 <= sum - numbers[i] && sum - numbers[i] <= offset * 2) {
                        make[i][sum - numbers[i]] += make[i - 1][sum];
                    }
                }
            }
        }

        // for(int[] m : make){
        //     System.out.println(Arrays.toString(m));
        // }

        System.out.println(make[N][M + offset]);

    }
}
