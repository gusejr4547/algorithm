package 프로그래머스;

import java.util.*;

public class 방의_개수 {
    public static void main(String[] args) {

    }

    // 명령어(edge) 개수 <= 100 000
    int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public int solution(int[] arrows) {
        int answer = 0;

        Set<String> visitEdge = new HashSet<>();
        Set<String> visitVertex = new HashSet<>();

        int x = 0, y = 0;
        visitVertex.add(vertexKey(x, y));

        // 이동을 2배 늘려서 하기
        for (int dir : arrows) {
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                String nowVertex = vertexKey(x, y);
                String nextVertex = vertexKey(nx, ny);

                String edge1 = edgeKey(x, y, nx, ny);
                String edge2 = edgeKey(nx, ny, x, y);

                // 방문한 vertex이고, 새로운 edge로 들어오면 1개의 도형 완성
                if (visitVertex.contains(nextVertex) && !visitEdge.contains(edge1)) {
                    answer++;
                }

                // 방문처리
                visitVertex.add(nextVertex);
                visitEdge.add(edge1);
                visitEdge.add(edge2);

                // 좌표이동
                x = nx;
                y = ny;
            }
        }

        return answer;
    }

    private String vertexKey(int x, int y) {
        return x + "," + y;
    }

    private String edgeKey(int x1, int y1, int x2, int y2) {
        return x1 + "," + y1 + " " + x2 + "," + y2;
    }
}
