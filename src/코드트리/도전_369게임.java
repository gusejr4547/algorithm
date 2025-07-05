package 코드트리;

import java.util.*;

public class 도전_369게임 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();

        // Please write your code here.

        long[] totalNums = new long[n+1];
        totalNums[0] = 1;
        for(int i=1; i<=n; i++){
            totalNums[i] = (totalNums[i-1] * 10) % MOD;
        }

        // N의 자릿수를 모두 더한 값이 3의 배수이면 N은 3의 배수이다
        int numSum = 0;
        boolean is369 = false;

        long[][] dp = new long[n+1][3]; // N자리수까지 처리했을 때 3,6,9포함되지 않고 나머지 0,1,2 인 경우의 수

        long answer = 0;
        for(int i=0; i<n; i++){
            int num = s.charAt(i) - '0';

            for(int x = 0; x <= 9; x++){
                if(x == 3 || x == 6 || x == 9){
                    // i자리에서 3,6,9를 선택하면 앞이랑 뒤랑 올수있는거 전부 곱하기
                    // 3,6,9포함되지 않고 나머지 0,1,2 인 경우의 수 * 아래에 올수있는 숫자 개수
                    answer = (answer + (dp[i][0] + dp[i][1] + dp[i][2]) * totalNums[n-i-1]) % MOD;
                }else{
                    for(int k=0; k<3; k++){
                        // 지금까지 만든 숫자 합 % 3 = k 이고 여기다가 새로 추가할 숫자 x를 더해서 다음 숫자를 만듬
                        dp[i+1][(k+x) % 3] = (dp[i+1][(k+x) % 3] + dp[i][k]) % MOD;
                    }
                }
            }

            // num 보다 작은 경우
            for(int x=0; x < num; x++){
                if(is369 || x == 3 || x == 6 || x == 9){
                    answer = (answer + totalNums[n - i - 1]) % MOD;
                }else{
                    dp[i+1][(x+numSum) % 3] = (dp[i+1][(x+numSum) % 3] + 1) % MOD;
                }
            }

            // 현재 숫자 3, 6, 9
            if(num == 3 || num == 6 || num == 9){
                is369 = true;
            }else{
                numSum += num;
            }

            // for(long[] d : dp){
            //     System.out.println(Arrays.toString(d));
            // }

            // System.out.println("#######");

        }

        if(is369){
            answer = (answer + 1) % MOD;
        }else{
            dp[n][numSum % 3] = (dp[n][numSum % 3] + 1) % MOD;
        }

        answer = (answer + dp[n][0]) % MOD;
        answer = (answer + MOD-1) % MOD; // 0은 제외하기 위해  MOD-1을 해서 양수 유지

        System.out.println(answer);

    }
}
