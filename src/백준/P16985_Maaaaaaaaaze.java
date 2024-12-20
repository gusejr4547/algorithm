package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 미로를 탈출하는 가장 적은 이동 횟수
public class P16985_Maaaaaaaaaze {
    static int answer;
    static List<int[][]> mapList;
    static int[][][][] rotatedMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int[][] map = new int[5][5];
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            mapList.add(map);
        }

        // 5개 판을 미리 회전시켜 저장
        rotatedMap = new int[5][4][5][5];
        for (int pan = 0; pan < 5; pan++) {
            rotatedMap[pan][0] = mapList.get(pan);
            for (int rotate = 1; rotate < 4; rotate++) {
                rotatedMap[pan][rotate] = rotate90(rotatedMap[pan][rotate - 1]);
            }
        }

        answer = Integer.MAX_VALUE;
        // 판 순서 => 120가지
        orderingMap(0, new int[5], new boolean[5]);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    private static void orderingMap(int idx, int[] order, boolean[] select) {
        if (idx == 5) {
            // 회전 결정 4^5
            selectRotateAngle(0, new int[5], order);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!select[i]) {
                order[idx] = i;
                select[i] = true;
                orderingMap(idx + 1, order, select);
                select[i] = false;
            }
        }
    }

    private static void selectRotateAngle(int idx, int[] select, int[] mapOrder) {
        if (idx == 5) {
            bfs(select, mapOrder);
            return;
        }

        for (int rotate = 0; rotate < 4; rotate++) {
            select[idx] = rotate;
            selectRotateAngle(idx + 1, select, mapOrder);
        }
    }

    private static void bfs(int[] selectRotate, int[] mapOrder) {
        int[][][] totalMap = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            int h = mapOrder[i];
            int rotate = selectRotate[i];
            totalMap[i] = rotatedMap[h][rotate];
        }
        // 시작, 끝 0인가?
        if (totalMap[0][0][0] == 0 || totalMap[4][4][4] == 0) {
            return;
        }

        // 6방향 이동가능
        int[] dz = {0, 0, 0, 0, 1, -1};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dx = {1, -1, 0, 0, 0, 0};

        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[][][] visit = new boolean[5][5][5];
        queue.offer(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 정답보다 거리 길면 구할필요없음
            if (cur.len >= answer) {
                break;
            }

            if (cur.z == 4 && cur.y == 4 && cur.x == 4) {
                // 종료
                answer = Math.min(answer, cur.len);
                break;
            }

            if (visit[cur.z][cur.y][cur.x]) {
                continue;
            }
            visit[cur.z][cur.y][cur.x] = true;

            for (int d = 0; d < 6; d++) {
                int nz = cur.z + dz[d];
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (nz < 0 || ny < 0 || nx < 0 || nz >= 5 || ny >= 5 || nx >= 5 || totalMap[nz][ny][nx] == 0 || visit[nz][ny][nx]) {
                    continue;
                }

                queue.offer(new Node(nz, ny, nx, cur.len + 1));
            }
        }
    }

    // 판90도 회전
    private static int[][] rotate90(int[][] map) {
        int[][] result = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result[j][4 - i] = map[i][j];
            }
        }
        return result;
    }

    private static class Node {
        int z, y, x;
        int len;

        public Node(int z, int y, int x, int len) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.len = len;
        }
    }

}
