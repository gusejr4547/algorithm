package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P2668_숫자고르기 {
    static int[] grid;
    static boolean[] visit;
    static List<Integer> answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        grid = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            grid[i] = Integer.parseInt(br.readLine());
        }

        answer = new ArrayList<>();
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;
        }

        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    private static void dfs(int start, int origin) {
        int next = grid[start];

        if (!visit[next]) {
            visit[next] = true;
            dfs(next, origin);
            visit[next] = false;
        }

        if (next == origin) {
            answer.add(origin);
        }
    }
}
