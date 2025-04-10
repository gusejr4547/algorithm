package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1005_ACM_Craft {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] constructionTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                constructionTime[i] = Integer.parseInt(st.nextToken());
            }

            int[] inCount = new int[N + 1];
            List<Integer>[] adj = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adj[X].add(Y);
                inCount[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            int answer = calMinConstructionTime(inCount, constructionTime, adj, N, W);

            System.out.println(answer);
        }

    }

    private static int calMinConstructionTime(int[] inCount, int[] constructionTime, List<Integer>[] adj, int N, int W) {
        int[] time = new int[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // 0인거로부터 시작
        for (int i = 1; i <= N; i++) {
            if (inCount[i] == 0) {
                queue.offer(i);
                time[i] = 0;
            }
        }

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int nextNode : adj[curNode]) {
                inCount[nextNode]--;
                time[nextNode] = Math.max(time[nextNode], time[curNode] + constructionTime[curNode]);
                if (inCount[nextNode] == 0) {
                    queue.offer(nextNode);
                }
            }
        }

        return time[W] + constructionTime[W];
    }
}
