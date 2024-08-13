package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1516_게임_개발 {
    static int N;
    static List<Integer>[] adj;
    static int[] buildTime;
    static int[] canBuild;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        buildTime = new int[N + 1];
        canBuild = new int[N + 1];
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            String[] info = br.readLine().split(" ");
            buildTime[i] = Integer.parseInt(info[0]);
            int size = 0;
            for (int j = 1; j < info.length - 1; j++) {
                int prev = Integer.parseInt(info[j]);
                adj[prev].add(i);
                size++;
            }
            canBuild[i] = size;
        }

        int[] minBuildTime = topology();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(minBuildTime[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] topology() {
        int[] minTime = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            minTime[i] = Integer.MAX_VALUE;
//        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // 지을 수 있는 건물 => canBuild가 0인거
        for (int i = 1; i <= N; i++) {
            if (canBuild[i] == 0) {
                minTime[i] = 0;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            minTime[cur] += buildTime[cur];

            for (int next : adj[cur]) {
                canBuild[next] -= 1;
                minTime[next] = Math.max(minTime[next], minTime[cur]);
                if (canBuild[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return minTime;
    }
}
