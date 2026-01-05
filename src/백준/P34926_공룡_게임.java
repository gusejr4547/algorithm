package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P34926_공룡_게임 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] map = br.readLine().toCharArray();

        // 공룡 2가지
        // 1. 걷기 i -> i+1
        // 2. 점프 i -> i+K
        // 게임 클리어 YES, 불가능 NO
        // K < N <= 1 000 000

        boolean[] visit = new boolean[N];
        visit[0] = true;

        for (int i = 0; i < N - 1; i++) {
            if (!visit[i]) {
                continue;
            }
            // 걷기
            if ('_' == map[i + 1]) {
                visit[i + 1] = true;
            }

            // 점프
            if (i + K < N && '_' == map[i + K]) {
                visit[i + K] = true;
            }
        }

        System.out.println(visit[N - 1] ? "YES" : "NO");
    }
}
