package 코드트리;

import java.util.*;

public class 신전_탐험하기2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        // Please write your code here.
        int[][][] dp = new int[3][n+1][3];

        for(int first=0; first<3; first++){
            for(int i=1; i<=n; i++){
                if(i == 1){
                    dp[first][i][first] = arr[i][first];
                }
                else{
                    for(int d=0; d<3; d++){
                        for(int pd=0; pd<3; pd++){
                            if(i == n){
                                if(d != pd && first != d){
                                    dp[first][i][d] = Math.max(dp[first][i][d], dp[first][i-1][pd] + arr[i][d]);
                                }
                            }else{
                                if(d != pd){
                                    dp[first][i][d] = Math.max(dp[first][i][d], dp[first][i-1][pd] + arr[i][d]);
                                }
                            }
                        }
                    }
                }
            }
        }

        // for(int[] d : dp[0]){
        //     System.out.println(Arrays.toString(d));
        // }

        // System.out.println("####################");
        // for(int[] d : dp[1]){
        //     System.out.println(Arrays.toString(d));
        // }
        // System.out.println("####################");
        // for(int[] d : dp[2]){
        //     System.out.println(Arrays.toString(d));
        // }


        int answer = 0;
        for(int first=0; first<3; first++){
            for(int d=0; d<3; d++){
                answer = Math.max(answer, dp[first][n][d]);
            }
        }

        System.out.println(answer);
    }
}
