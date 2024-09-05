package SWEA;

import java.util.Scanner;

class 새로운_불면증_치료법 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            boolean[] number = new boolean[10];
            int cnt = 0;
            long num = 0;
            while (true) {
                cnt++;
                num = (long) N * cnt;
                while (num > 0) {
                    int n = (int) (num % 10);
                    number[n] = true;
                    num /= 10;
                }

                boolean end = true;
                for (int i = 0; i < 10; i++) {
                    if (!number[i]) {
                        end = false;
                        break;
                    }
                }

                if (end) {
                    break;
                }
            }

            sb.append("#").append(test_case).append(" ").append(cnt * N).append("\n");
        }

        System.out.println(sb.toString());
    }
}