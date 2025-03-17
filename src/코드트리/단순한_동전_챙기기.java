package 코드트리;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class 단순한_동전_챙기기 {
    static char[][] map;
    static int[][] numPosArr;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[] startP, endP;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] grid = new String[N];
        for (int i = 0; i < N; i++) {
            grid[i] = sc.next();
        }
        // Please write your code here.
        // S -> E
        // 최소 3개의 동전을 수집해야함.
        // 동전은 번호 증가 순서로 가져야함.
        // 최소 이동 구하기
        startP = new int[2];
        endP = new int[2];
        numPosArr = new int[10][2]; // 1~9까지 숫자 위치 확인
        map = new char[N][N];
        // -1로 초기화
        for (int i = 0; i < 10; i++) {
            Arrays.fill(numPosArr[i], -1);
        }

        for (int i = 0; i < N; i++) {
            map[i] = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                if ('.' == map[i][j])
                    continue;
                else if ('S' == map[i][j]) {
                    startP[0] = i;
                    startP[1] = j;
                } else if ('E' == map[i][j]) {
                    endP[0] = i;
                    endP[1] = j;
                } else {
                    int num = map[i][j] - '0';
                    numPosArr[num][0] = i;
                    numPosArr[num][1] = j;
                }
            }
        }

        answer = Integer.MAX_VALUE;
        // 고를 숫자 선택하기
        selectNum(new int[3], 0, 1);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    private static void selectNum(int[] select, int selectIdx, int nextNum) {
        if (selectIdx == 3) {
            // 최소 이동 회수 구하기
            int moveCnt = getMinMove(select);
            answer = Math.min(answer, moveCnt);

            return;
        }

        for (int num = nextNum; num <= 9; num++) {
            if (numPosArr[num][0] == -1) continue;
            select[selectIdx] = num;
            selectNum(select, selectIdx + 1, num + 1);
        }
    }

    private static int getMinMove(int[] select) {
        int moveCnt = 0;

        int[] startPoint = startP;
        for (int nextIdx = 0; nextIdx < 3; nextIdx++) {
            moveCnt += getMove(startPoint, numPosArr[select[nextIdx]]);
            startPoint = numPosArr[select[nextIdx]];
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startPoint[0], startPoint[1], 0));
        boolean[][] visit = new boolean[map.length][map[0].length];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == endP[0] && cur.x == endP[1]) {
                moveCnt += cur.cnt;
                break;
            }

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length || visit[ny][nx]) {
                    continue;
                }
                queue.offer(new Node(ny, nx, cur.cnt + 1));
            }
        }
        return moveCnt;
    }

    private static int getMove(int[] start, int[] end) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start[0], start[1], 0));
        boolean[][] visit = new boolean[map.length][map[0].length];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == end[0] && cur.x == end[1]) {
                return cur.cnt;
            }

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length || visit[ny][nx]) {
                    continue;
                }
                if ('E' != map[ny][nx]) {
                    queue.offer(new Node(ny, nx, cur.cnt + 1));
                }
            }
        }

        return -1;
    }

    private static class Node {
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
