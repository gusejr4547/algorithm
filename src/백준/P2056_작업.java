package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2056_작업 {
    static List<Integer>[] adj;
    static int[] taskCostArray;
    static int[] degree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        taskCostArray = new int[N + 1];
        degree = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            taskCostArray[i] = cost;
            int numberOfTask = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int task = Integer.parseInt(st.nextToken());
                adj[task].add(i);
                degree[i]++;
            }
        }

        System.out.println(topologySort(N));
    }

    static int topologySort(int N) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = taskCostArray[i];

            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : adj[curr]) {
                degree[next]--;

                result[next] = Math.max(result[next], result[curr] + taskCostArray[next]);

                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, result[i]);
        }

        return answer;
    }
}
