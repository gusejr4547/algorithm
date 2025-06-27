package 코드트리;

import java.util.*;

public class 최대_합_분할 {
    static final int MAX = 200_000;
    static final int offset = 100_000; // 음수를 배열에 넣기 위함
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n+1][MAX+1]; // dp[i][j] i번째 수까지 고려했을 때 A-B가 j인 경우 A의 숫자 합 최대
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], -1);
        }

        dp[0][offset] = 0;

        for(int i=1; i<=n; i++){
            int num = arr[i-1];
            for(int j=0; j<=MAX; j++){
                // A에 추가
                if(j >= num && dp[i-1][j-num] != -1)
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-num] + num);

                // B에 추가
                if(j+num <= MAX && dp[i-1][j+num] != -1)
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j+num]);

                // C에 추가
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
            }
        }

        System.out.println(dp[n][offset]);
    }
}
