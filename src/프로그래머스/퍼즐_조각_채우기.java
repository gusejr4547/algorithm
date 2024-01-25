package 프로그래머스;

import java.util.*;

public class 퍼즐_조각_채우기 {
    public static void main(String[] args) {
        퍼즐_조각_채우기 Main = new 퍼즐_조각_채우기();
        int[][] gameBoard = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
        System.out.println(Main.solution(gameBoard, table));
    }

    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        int size = game_board.length;
        List<List<Point>> blankList = new ArrayList<>();
        List<List<Point>> blockList = new ArrayList<>();

        // game_board 빈공간 번호 바꾸기
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game_board[i][j] == 1)
                    game_board[i][j] = 0;
                else
                    game_board[i][j] = 1;
            }
        }
        // game_board 빈공간
        boolean[][] visitBoard = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visitBoard[i][j] && game_board[i][j] == 1) {
                    bfs(i, j, game_board, visitBoard, blankList);
                }
            }
        }
        // table의 블록
        boolean[][] visitTable = new boolean[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (!visitTable[i][j] && table[i][j] == 1) {
                    bfs(i, j, table, visitTable, blockList);
                }
            }
        }

        answer = match(blankList, blockList);

        return answer;
    }

    public int match(List<List<Point>> blankList, List<List<Point>> blockList) {
        int result = 0;
        boolean[] use = new boolean[blockList.size()];

        // blank 하나씩 꺼내서 block과 비교 -> 회전 후 비교
        for (List<Point> blank : blankList) {
            for (int blockIdx = 0; blockIdx < blockList.size(); blockIdx++) {
                List<Point> block = blockList.get(blockIdx);
                if (use[blockIdx] || blank.size() != block.size()) {
                    continue;
                }

                boolean isMatch = blockMatchCheck(blank, block);
                if (isMatch) {
                    use[blockIdx] = true;
                    result += block.size();
                    break;
                }
            }
        }

        return result;
    }

    public boolean blockMatchCheck(List<Point> blank, List<Point> block) {
        Collections.sort(blank);

        // 4번 회전
        for (int rotateCount = 0; rotateCount < 4; rotateCount++) {
            Collections.sort(block);
            // 가장 위, 왼쪽 기준 점 (0,0)변환
            int baseY = block.get(0).y;
            int baseX = block.get(0).x;
            for (int i = 0; i < block.size(); i++) {
                block.get(i).y -= baseY;
                block.get(i).x -= baseX;
            }
            boolean isMatch = true;
            for (int i = 0; i < blank.size(); i++) {
                Point blankPoint = blank.get(i);
                Point blockPoint = block.get(i);
                if (blankPoint.y != blockPoint.y || blankPoint.x != blockPoint.x) {
                    isMatch = false;
                    break;
                }
            }

            if (!isMatch) {
                // rotate
                rotate(block);
            } else {
                return true;
            }
        }
        return false;
    }

    public void rotate(List<Point> block) {
        for (int i = 0; i < block.size(); i++) {
            int temp = block.get(i).x;
            block.get(i).x = block.get(i).y;
            block.get(i).y = -temp;
        }
    }

    public void bfs(int y, int x, int[][] map, boolean[][] visit, List<List<Point>> list) {
        int size = map.length;
        ArrayDeque<Point> queue = new ArrayDeque<>();
        List<Point> blockInfo = new ArrayList<>();

        queue.offer(new Point(y, x));
//        blockInfo.add(new Point(y - y, x - x));

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (visit[curr.y][curr.x]) continue;
            visit[curr.y][curr.x] = true;
            blockInfo.add(new Point(curr.y - y, curr.x - x));

            for (int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= size || nx >= size || visit[ny][nx])
                    continue;
                if (map[ny][nx] == 1) {
                    queue.offer(new Point(ny, nx));
//                    blockInfo.add(new Point(ny - y, nx - x));
                }
            }
        }

        list.add(blockInfo);
    }

    public class Point implements Comparable<Point> {
        int y;
        int x;

        public Point() {
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            return this.y == o.y ? this.x - o.x : this.y - o.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}
