package 백준._20260102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_방바닥_정리하기 {
    // NxN 주어짐
    // 빈칸 '.', 물건 소문자, 구멍 대문자
    // 대문자 소문자 쌍 한가지 색.

    // 1. 구멍중 하나를 선택해 원하는 만큼 상하좌우로 움직임.
    // 2. 이동 경로에 같은색 물건 있으면 제거.... 다른 색 물건은 못지나감
    // 3. 다른 구멍은 지나갈 수 있다. 이동 끝나고는 두개 이상의 구멍이 같은 칸 x
    // 4. 이동 정지하면 이번 차례 끝. or 방에 남은 물건 없으면 끝

    // 최소 시행 수, 모든 물건을 제거할 수 없다면 -1

    // N <= 1000, M <= 2
    // 구멍은 무조건 1색 1개

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 색의 수
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int say = -1, sax = -1, sby = -1, sbx = -1;
        List<Point> itemA = new ArrayList<>();
        List<Point> itemB = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 구멍
                if (map[i][j] == 'A') {
                    say = i;
                    sax = j;
                } else if (map[i][j] == 'B') {
                    sby = i;
                    sbx = j;
                }
                // 물건
                else if (map[i][j] == 'a') {
                    itemA.add(new Point(i, j));
                } else if (map[i][j] == 'b') {
                    itemB.add(new Point(i, j));
                }
            }
        }
        // Map으로 변경
        Map<Integer, Integer> itemAMap = new HashMap<>();
        Map<Integer, Integer> itemBMap = new HashMap<>();
        for (int i = 0; i < itemA.size(); i++) {
            itemAMap.put(point2int(itemA.get(i), N), i);
        }
        for (int i = 0; i < itemB.size(); i++) {
            itemBMap.put(point2int(itemB.get(i), N), i);
        }

        // bfs
        ArrayDeque<State> queue = new ArrayDeque<>();

        State start = new State(say, sax, sby, sbx, 0, new boolean[itemA.size()], new boolean[itemB.size()]);
        queue.offer(start);

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // 종료 조건 확인
            if (isEnd(cur.itemADelete, cur.itemBDelete)) {
                break;
            }

            // A구멍 움직임

            // B구멍 움직임
        }

    }

    private static boolean isEnd(boolean[] itemADelete, boolean[] itemBDelete) {
        for (int i = 0; i < itemADelete.length; i++) {
            if (!itemADelete[i]) {
                return false;
            }
        }
        for (int i = 0; i < itemBDelete.length; i++) {
            if (!itemBDelete[i]) {
                return false;
            }
        }
        return true;
    }

    private static int point2int(Point a, int N) {
        return a.y * N + a.x;
    }

    private static class State {
        int ay, ax, by, bx, count;
        boolean[] itemADelete, itemBDelete;

        public State(int ay, int ax, int by, int bx, int count, boolean[] itemADelete, boolean[] itemBDelete) {
            this.ay = ay;
            this.ax = ax;
            this.by = by;
            this.bx = bx;
            this.count = count;
            this.itemADelete = itemADelete;
            this.itemBDelete = itemBDelete;
        }
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
