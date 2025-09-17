package 코드트리.HSAT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 사물인식_최소_면적_산출_프로그램 {
    static int N, K, answer;
    static List<Point>[] colorPoint;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        colorPoint = new List[K + 1];
        for (int i = 1; i <= K; i++) {
            colorPoint[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int c = sc.nextInt();
            colorPoint[c].add(new Point(x, y));
        }

        // 좌표 <= 100, 색 <= 20
        answer = Integer.MAX_VALUE;
        dfs(1, -1000, 1000, -1000, 1000);

        System.out.println(answer);
    }

    private static void dfs(int color, int maxX, int minX, int maxY, int minY) {
        int area = (maxX - minX) * (maxY - minY);
        if (K < color) {
            // 계산
            answer = Math.min(answer, area);
            return;
        }

        // 가지치기
        if (answer <= area) {
            return;
        }

        // p 포함
        for (Point p : colorPoint[color]) {
            dfs(color + 1, Math.max(maxX, p.x), Math.min(minX, p.x), Math.max(maxY, p.y), Math.min(minY, p.y));
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}
