package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 빛의_경로_사이클 {
    public static void main(String[] args) {
        빛의_경로_사이클 Main = new 빛의_경로_사이클();
        String[] grid = {"SL", "LR"};
        System.out.println(Arrays.toString(Main.solution(grid)));
    }

    int R, C;
    char[][] map;
    // 위, 오, 아래, 왼
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};

    boolean[][][] visit;

    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = grid[i].toCharArray();
        }

        visit = new boolean[R][C][4];

        List<Integer> cycleList = new ArrayList<>();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                for (int d = 0; d < 4; d++) {
                    if (!visit[y][x][d]) {
                        int result = shoot(y, x, d);
                        cycleList.add(result);
                    }
                }
            }
        }
        cycleList.sort(Comparator.naturalOrder());
        int[] answer = new int[cycleList.size()];
        for (int i = 0; i < cycleList.size(); i++) {
            answer[i] = cycleList.get(i);
        }
        return answer;
    }

    private int shoot(int y, int x, int direction) {
        int count = 0;
        while (!visit[y][x][direction]) {
            visit[y][x][direction] = true;
            y = y + dy[direction];
            x = x + dx[direction];
            if (y < 0) {
                y = R - 1;
            } else if (y >= R) {
                y = 0;
            }
            if (x < 0) {
                x = C - 1;
            } else if (x >= C) {
                x = 0;
            }

            if (map[y][x] == 'L') {
                direction = direction - 1 < 0 ? 3 : direction - 1;
            } else if (map[y][x] == 'R') {
                direction = direction + 1 > 3 ? 0 : direction + 1;
            }
            count++;
        }
        return count;
    }

    private int dfs(int y, int x, int direction, int count) {
        if (visit[y][x][direction]) {
            return count;
        }
        visit[y][x][direction] = true;

        int ny = y + dy[direction];
        int nx = x + dx[direction];
        if (ny < 0) {
            ny = R - 1;
        } else if (ny >= R) {
            ny = 0;
        }
        if (nx < 0) {
            nx = C - 1;
        } else if (nx >= C) {
            nx = 0;
        }

        int nextDirection = 0;
        if (map[ny][nx] == 'L') {
            nextDirection = direction - 1 < 0 ? 3 : direction - 1;
        } else if (map[ny][nx] == 'R') {
            nextDirection = direction + 1 > 3 ? 0 : direction + 1;
        } else {
            nextDirection = direction;
        }
        return dfs(ny, nx, nextDirection, count + 1);
    }
}
