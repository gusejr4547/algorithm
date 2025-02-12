package 프로그래머스.코드챌린지;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 지게차와_크레인 {
    public static void main(String[] args) {
        지게차와_크레인 Main = new 지게차와_크레인();
        String[] storage = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests = {"A", "BB", "A"};
        System.out.println(Main.solution(storage, requests));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(String[] storage, String[] requests) {
        char[][] map = new char[storage.length + 2][storage[0].length() + 2];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], '-');
        }

        for (int i = 1; i <= storage.length; i++) {
            for (int j = 1; j <= storage[0].length(); j++) {
                map[i][j] = storage[i - 1].charAt(j - 1);
            }
        }

        int max = storage.length * storage[0].length();
        int cnt = 0;

        for (String request : requests) {
            int size = request.length();
            if (size == 1) {
                cnt += getContainer(request.charAt(0), map);
            } else {
                cnt += getContainerUseCraine(request.charAt(0), map);
            }
            // 컨테이너 사라진 자리 밖인지 아닌지 갱신 필요
            update(map);
        }
//        System.out.println(max);
//        System.out.println(cnt);
        int answer = max - cnt;
        return answer;
    }

    private void update(char[][] map) {
        int N = map.length;
        int M = map[0].length;

        // bfs 사용
        boolean[][] visit = new boolean[N][M];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visit[cur.y][cur.x]) continue;
            visit[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || ('A' <= map[ny][nx] && map[ny][nx] <= 'Z')) {
                    continue;
                }

                if (map[ny][nx] == '~') {
                    map[ny][nx] = '-';
                    queue.offer(new Point(ny, nx));
                } else if (map[ny][nx] == '-') {
                    queue.offer(new Point(ny, nx));
                }
            }
        }
    }

    private int getContainerUseCraine(char target, char[][] map) {
        int cnt = 0;
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] == target) {
                    map[i][j] = '~';
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private int getContainer(char target, char[][] map) {
        List<Point> deleteList = new ArrayList<>();
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] == target) {
                    // 4방항 중의 1군데라도 접근이 가능?
                    boolean canGet = false;
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (map[ny][nx] == '-') {
                            canGet = true;
                            break;
                        }
                    }
                    if (canGet) {
                        deleteList.add(new Point(i, j));
                    }
                }
            }
        }
        // map에서 삭제
        for (Point p : deleteList) {
            map[p.y][p.x] = '-';
        }
        return deleteList.size();
    }

    class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
