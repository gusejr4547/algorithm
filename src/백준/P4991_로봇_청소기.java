package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

// .: 깨끗한 칸
// *: 더러운 칸
// x: 가구
// o: 로봇 청소기의 시작 위치
// 로봇은 한 번 움직일 때, 인접한 칸으로 이동할 수 있다. 또, 로봇은 같은 칸을 여러 번 방문할 수 있다.
// 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최솟값
// 방문할 수 없는 더러운 칸이 존재하는 경우에는 -1
public class P4991_로봇_청소기 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 입력 종료
            if (w == 0 && h == 0) {
                break;
            }

            char[][] map = new char[h][w];
            Pos[] dusts = new Pos[11]; // 더러운칸 + 로봇 <= 10
            int dustIdx = 1;

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 'o') {
                        dusts[0] = new Pos(i, j);
                    } else if (map[i][j] == '*') {
                        dusts[dustIdx++] = new Pos(i, j);
                    }
                }
            }

            // 인접 리스트
            List<Node>[] adjList = new List[dustIdx];
            for (int i = 0; i < dustIdx; i++) {
                adjList[i] = new ArrayList<>();
            }

            // dusts에 있는 점들 사이의 최소 거리 계산
            for (int s = 0; s < dustIdx - 1; s++) {
                for (int e = s + 1; e < dustIdx; e++) {
                    int dist = getMinMoveBFS(dusts[s], dusts[e], map, w, h);
                    if (dist == -1) continue;
                    adjList[s].add(new Node(e, dist));
                    adjList[e].add(new Node(s, dist));
                }
            }

            // dist 번호 순서대로 완전탐색 => 순열
            answer = Integer.MAX_VALUE;
            boolean[] visit = new boolean[dustIdx];
            visit[0] = true;
            findMinMovePermutation(0, 0, 0, visit, adjList, dustIdx);

            sb.append(answer == Integer.MAX_VALUE ? -1 : answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void findMinMovePermutation(int curNode, int depth, int sum, boolean[] visit, List<Node>[] adjList, int totalDustCnt) {
        if (depth == totalDustCnt - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (Node next : adjList[curNode]) {
            if (visit[next.nextNode]) continue;

            visit[next.nextNode] = true;
            findMinMovePermutation(next.nextNode, depth + 1, sum + next.dist, visit, adjList, totalDustCnt);
            visit[next.nextNode] = false;
        }
    }

    private static int getMinMoveBFS(Pos start, Pos end, char[][] map, int w, int h) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(start.y, start.x, 0));
        boolean[][] visit = new boolean[h][w];

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (end.y == cur.y && end.x == cur.x) {
                return cur.moveCnt;
            }

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= h || nx >= w || map[ny][nx] == 'x' || visit[ny][nx]) {
                    continue;
                }

                queue.offer(new Pos(ny, nx, cur.moveCnt + 1));
            }
        }
        return -1;
    }

    private static class Pos {
        int y, x;
        int moveCnt;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Pos(int y, int x, int moveCnt) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
        }
    }

    private static class Node {
        int nextNode, dist;

        public Node(int nextNode, int dist) {
            this.nextNode = nextNode;
            this.dist = dist;
        }
    }
}
