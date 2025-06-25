package 코드트리;

import java.util.*;

public class 알바로_부자_되기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Schedule> schedule = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int p = sc.nextInt();
            schedule.add(new Schedule(s, e, p));
        }
        // Please write your code here.
        Collections.sort(schedule, (o1, o2) -> o1.e - o2.e);

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = schedule.get(i).p;

            for (int j = 0; j < i; j++) {
                if (schedule.get(j).e < schedule.get(i).s) {
                    dp[i] = Math.max(dp[i], dp[j] + schedule.get(i).p);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    private static class Schedule {
        int s, e, p;

        public Schedule(int s, int e, int p) {
            this.s = s;
            this.e = e;
            this.p = p;
        }
    }
}
