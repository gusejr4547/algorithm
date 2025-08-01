package 코드트리;

import java.util.*;

public class 최소_점프_횟수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] count = new int[n];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int jump = 1; jump <= arr[i]; jump++) {
                if (i + jump < n) {
                    count[i + jump] = Math.min(count[i + jump], count[i] + 1);
                }
            }
        }

        System.out.println(count[n - 1] == Integer.MAX_VALUE ? -1 : count[n - 1]);
    }
}
