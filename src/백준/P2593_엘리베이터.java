package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2593_엘리베이터 {
    static int N, M, total, lastElv;
    static int[][] infos;
    static List<Integer>[] adj;
    static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        infos = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // i번 엘리베이터는 x층에서 시작해서 y번째 층 마다 선다
            infos[i][0] = x;
            infos[i][1] = y;
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 엘리베이터 사이 adj
        adj = new List[M + 1];
        for (int i = 1; i <= M; i++) {
            adj[i] = new ArrayList<>();
        }

        makeAdj();

        visit = new int[M + 1];
        Arrays.fill(visit, -1);
        bfs(A, B);

        if (total == 0) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(total).append('\n');

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int idx = lastElv;
        while (idx != 0) {
            stack.push(idx);
            idx = visit[idx];
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append('\n');
        }

        System.out.println(sb);
    }

    private static void bfs(int s, int e) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        for (int i = 1; i <= M; i++) {
            // s층 탈 수 있는 엘리베이터
            if (s >= infos[i][0] && (s - infos[i][0]) % infos[i][1] == 0) {
                queue.offer(new Node(i, 1));
                visit[i] = 0;
            }
        }


        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // cur번째 엘리베이터는 e에 도달할 수 있는가?
            if (isOut(e, cur.elv)) {
                // 뒤로 돌아가기
                lastElv = cur.elv;
                total = cur.count;
                return;
            }

            for (int next : adj[cur.elv]) {
                if (visit[next] == -1) {
                    visit[next] = cur.elv;
                    queue.offer(new Node(next, cur.count + 1));
                }
            }
        }
    }

    private static boolean isOut(int end, int elv) {
        return end >= infos[elv][0] && (end - infos[elv][0]) % infos[elv][1] == 0;
    }

    private static void makeAdj() {
        for (int i = 1; i <= M; i++) {
            int[] a = infos[i];
            for (int j = i + 1; j <= M; j++) {
                int[] b = infos[j];

                if (a[0] > b[0]) {
                    int floor = a[0];
                    while (floor <= N) {
                        if ((floor - b[0]) % b[1] == 0) {
                            adj[i].add(j);
                            adj[j].add(i);
                            break;
                        }
                        floor += a[1];
                    }
                } else {
                    int floor = b[0];
                    while (floor <= N) {
                        if ((floor - a[0]) % a[1] == 0) {
                            adj[i].add(j);
                            adj[j].add(i);
                            break;
                        }
                        floor += b[1];
                    }
                }
            }
        }
    }

    private static class Node {
        int elv, count;

        public Node(int elv, int count) {
            this.elv = elv;
            this.count = count;
        }
    }
}
