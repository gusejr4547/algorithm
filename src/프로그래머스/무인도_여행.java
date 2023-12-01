package 프로그래머스;

import java.util.*;

public class 무인도_여행 {
    public static void main(String[] args) {
        무인도_여행 Main = new 무인도_여행();
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};
        System.out.println(Arrays.toString(Main.solution(maps)));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int[] solution(String[] maps) {
        int N = maps.length;
        char[][] board = new char[maps.length][];
        for (int i = 0; i < N; i++) {
            board[i] = maps[i].toCharArray();
        }

        int M = board[0].length;

        List<Integer> result = new ArrayList<>();
        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && board[i][j] != 'X') {
                    connect(i, j, result, visit, board);
                }
            }
        }

        if(result.isEmpty()){
            return new int[]{-1};
        }

        int[] answer = new int[result.size()];
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public void connect(int y, int x, List<Integer> result, boolean[][] visit, char[][] board) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        int total = 0;
        int N = visit.length;
        int M = visit[0].length;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            if (visit[curr[0]][curr[1]]) {
                continue;
            }
            visit[curr[0]][curr[1]] = true;
            total += board[curr[0]][curr[1]] - '0';

            for (int d = 0; d < 4; d++) {
                int ny = curr[0] + dy[d];
                int nx = curr[1] + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || board[ny][nx] == 'X') {
                    continue;
                }
                queue.offer(new int[]{ny, nx});
            }
        }
        result.add(total);
    }
}
