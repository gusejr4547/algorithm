package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9466_텀_프로젝트 {
    static int teamMemberCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] matchStudentId = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                matchStudentId[i] = Integer.parseInt(st.nextToken());
            }

            teamMemberCnt = 0;
            boolean[] cycleCheck = new boolean[n + 1];
            boolean[] visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (cycleCheck[i])
                    continue;
                dfs(i, visit, cycleCheck, matchStudentId);
            }

            System.out.println(n-teamMemberCnt);
        }
    }

    private static void dfs(int now, boolean[] visit, boolean[] cycleCheck, int[] matchStudentId) {
        if (cycleCheck[now]) {
            return;
        }

        if (visit[now]) {
            cycleCheck[now] = true;
            teamMemberCnt++;
        }

        visit[now] = true;
        dfs(matchStudentId[now], visit, cycleCheck, matchStudentId);
        visit[now] = false;
        cycleCheck[now] = true;
    }
}
