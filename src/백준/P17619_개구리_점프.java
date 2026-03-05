package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17619_개구리_점프 {
    static int[] parent;
    static List<Log> logs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        logs = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            logs.add(new Log(x1, x2, i));
        }

        // 정렬
        logs.sort((o1, o2) -> o1.x1 != o2.x1 ? o1.x1 - o2.x1 : o1.x2 - o2.x2);

        // union-find
        int id = logs.get(0).id;
        int end = logs.get(0).x2;

        for (int i = 1; i < N; i++) {
            Log cur = logs.get(i);
            if (cur.x1 <= end) {
                // 합치기
                union(id, cur.id);
                end = Math.max(end, cur.x2);
            }
            // 안겹침
            else {
                // 새 그룹
                id = cur.id;
                end = cur.x2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) == find(b)) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static class Log {
        int x1, x2, id;

        public Log(int x1, int x2, int id) {
            this.x1 = x1;
            this.x2 = x2;
            this.id = id;
        }
    }
}
