package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P12893_적의_적 {
    static int N, M;
    static List<Integer>[] adj;
    static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            adj[num1].add(num2);
            adj[num2].add(num1);
        }

        visit = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visit[i] == 0) {
                visit[i] = 1;
                dfs(i, 1);
            }
        }

//        System.out.println(Arrays.toString(visit));
        int answer = 1;
        for (int i = 0; i <= N; i++) {
            if (visit[i] < 0) {
                answer = 0;
                break;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int curr, int depth) {
        for (int next : adj[curr]) {
            if (visit[next] != 0) {
                if (visit[next] % 2 != (depth + 1) % 2)
                    visit[next] = -1;
            } else {
                visit[next] = depth + 1;
                dfs(next, depth + 1);
            }
        }
    }
}
