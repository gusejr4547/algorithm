package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P12851_숨바꼭질2 {
    static int[] minTime;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        minTime = new int[200001];
        cnt = 0;
        bfs(N, K);
        System.out.println(minTime[K]);
        System.out.println(cnt);
    }

    private static void bfs(int start, int end) {
        Arrays.fill(minTime, Integer.MAX_VALUE);
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 0));

        minTime[start] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.num == end) {
                if (cur.time == minTime[cur.num]) {
                    cnt++;
                } else if (cur.time < minTime[cur.num]) {
                    cnt = 1;
                }
                continue;
            }

            if (cur.num - 1 >= 0) {
                if (minTime[cur.num - 1] >= cur.time + 1) {
                    minTime[cur.num - 1] = cur.time + 1;
                    queue.offer(new Node(cur.num - 1, cur.time + 1));
                }
            }

            if (cur.num + 1 <= 200000) {
                if (minTime[cur.num + 1] >= cur.time + 1) {
                    minTime[cur.num + 1] = cur.time + 1;
                    queue.offer(new Node(cur.num + 1, cur.time + 1));
                }
            }

            if (cur.num * 2 <= 200000) {
                if (minTime[cur.num * 2] >= cur.time + 1) {
                    minTime[cur.num * 2] = cur.time + 1;
                    queue.offer(new Node(cur.num * 2, cur.time + 1));
                }
            }
        }

//        System.out.println(Arrays.toString(minTime));
    }

    private static class Node {
        int num, time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
