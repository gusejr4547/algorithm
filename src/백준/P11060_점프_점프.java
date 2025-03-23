package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P11060_점프_점프 {
    static int MAX = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] maze = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N]; // dp[i] i 위치에 오기까지 걸린 최소 점프수
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            // MAX값이면 도달 못하는 지점
            if (dp[i] == MAX) continue;
            for (int dist = 1; dist <= maze[i]; dist++) {
                if (i + dist >= N) break;
                dp[i + dist] = Math.min(dp[i + dist], dp[i] + 1);
            }
        }

//        int answer = jump(maze);

        System.out.println(dp[N - 1] == MAX ? -1 : dp[N - 1]);
    }

    private static int jump(int[] maze) {
        int N = maze.length;
        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> o1.jumpCnt - o2.jumpCnt);
        pq.offer(new State(0, 0));
        boolean[] visit = new boolean[N];

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.idx == N - 1) {
                return cur.jumpCnt;
            }

            if (visit[cur.idx]) continue;
            visit[cur.idx] = true;

            // 다음 점프 가능한 곳
            for (int dist = 1; dist <= maze[cur.idx]; dist++) {
                int nextIdx = cur.idx + dist;
                if (nextIdx >= N || visit[nextIdx]) continue;

                pq.offer(new State(nextIdx, cur.jumpCnt + 1));
            }
        }
        return -1;
    }

    private static class State {
        int idx, jumpCnt;

        public State(int idx, int jumpCnt) {
            this.idx = idx;
            this.jumpCnt = jumpCnt;
        }
    }
}
