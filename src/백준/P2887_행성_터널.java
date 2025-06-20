package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class P2887_행성_터널 {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]>[] point = new List[3];
        for (int i = 0; i < 3; i++) {
            point[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            point[0].add(new int[]{x, i});
            point[1].add(new int[]{y, i});
            point[2].add(new int[]{z, i});
        }

        // 연결 비용 min(|xA-xB|, |yA-yB|, |zA-zB|)
        // x, y, z 각각 정렬
        point[0].sort((o1, o2) -> o1[0] - o2[0]);
        point[1].sort((o1, o2) -> o1[0] - o2[0]);
        point[2].sort((o1, o2) -> o1[0] - o2[0]);

        List<Edge> edgeList = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int cost = point[j].get(i)[0] - point[j].get(i - 1)[0];
                int a = point[j].get(i)[1];
                int b = point[j].get(i - 1)[1];
                edgeList.add(new Edge(a, b, cost));
            }
        }

        edgeList.sort(Comparator.comparingInt(o -> o.cost));

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int answer = 0;

        for (Edge edge : edgeList) {
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.cost;
            }
        }

        System.out.println(answer);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
