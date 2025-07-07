package 코드트리;

import java.util.*;

public class 회전_마법진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String current = sc.next();
        String target = sc.next();
        // Please write your code here.
        // 시계 반대방향 => 뒤에꺼 같이 돈다.
        // 시계 방향 => 그것만 돈다

        int[][] dp = new int[n + 1][10];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            int currNum = current.charAt(i) - '0';
            int targetNum = target.charAt(i) - '0';

            if (i == 0) {
                if (targetNum >= currNum) {
                    int backMove = targetNum - currNum;
                    int frontMove = currNum + 10 - targetNum;
                    dp[i + 1][0] = frontMove;
                    dp[i + 1][backMove] = backMove;
                } else {
                    int backMove = targetNum + 10 - currNum;
                    int frontMove = currNum - targetNum;
                    dp[i + 1][0] = frontMove;
                    dp[i + 1][backMove] = backMove;
                }

                continue;
            }

            for (int rotate = 0; rotate <= 9; rotate++) {
                if (dp[i][rotate] == Integer.MAX_VALUE) {
                    continue;
                }

                int num = (currNum + rotate) % 10;

                if (targetNum >= num) {
                    int backMove = targetNum - num;
                    int frontMove = num + 10 - targetNum;

                    dp[i + 1][rotate] = Math.min(dp[i + 1][rotate], dp[i][rotate] + frontMove);
                    dp[i + 1][(rotate + backMove) % 10] = Math.min(dp[i + 1][(rotate + backMove) % 10], dp[i][rotate] + backMove);


                } else {
                    int backMove = targetNum + 10 - num;
                    int frontMove = num - targetNum;

                    dp[i + 1][rotate] = Math.min(dp[i + 1][rotate], dp[i][rotate] + frontMove);
                    dp[i + 1][(rotate + backMove) % 10] = Math.min(dp[i + 1][(rotate + backMove) % 10], dp[i][rotate] + backMove);

                }
            }
        }

        // for(int[] d : dp){
        //     System.out.println(Arrays.toString(d));
        // }

        int answer = Integer.MAX_VALUE;

        for (int rotate = 0; rotate <= 9; rotate++) {
            answer = Math.min(answer, dp[n][rotate]);
        }

        System.out.println(answer);
    }
}
