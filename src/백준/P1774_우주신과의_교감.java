package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1774_우주신과의_교감 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Point[] god = new Point[N + 1];
        // 우주신 좌표
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            god[i] = new Point(x, y);
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        // 연결된 통로
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a번째 신과 b번째 신이 연결
            union(a, b);
        }

        // 만들어야 할 최소의 통로 길이를 소수점 둘째 자리까지 반올림하여 출력하라.
        // 최소 스패닝 트리 MST
        List<Edge> edgeList = new ArrayList<>();
        // 간선 만들 수 있는거
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                edgeList.add(new Edge(i, j, getDistance(god[i], god[j])));
            }
        }
        // 간선 비용 적은거 부터 스패닝 트리 만들 때 사용
        Collections.sort(edgeList, (o1, o2) -> Double.compare(o1.weight, o2.weight));

        double answer = 0;
        for (Edge edge : edgeList) {
            // 연결 안되어있으면
            if (find(edge.a) != find(edge.b)) {
                answer += edge.weight;
                union(edge.a, edge.b);
            }
        }

        // 소수점 둘째자리 반올림
        System.out.printf("%.2f\n", answer);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parents[a] = b;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }

    private static class Edge {
        int a, b;
        double weight;

        public Edge(int a, int b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
