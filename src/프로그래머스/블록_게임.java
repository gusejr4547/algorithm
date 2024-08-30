package 프로그래머스;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 블록_게임 {
    public static void main(String[] args) {
        블록_게임 Main = new 블록_게임();
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}
        };
        System.out.println(Main.solution(board));
    }

    // 플레이어는 위쪽에서 1 x 1 크기의 검은 블록을 떨어뜨려 쌓을 수 있다.
    // 기존에 놓인 블록을 합해 속이 꽉 채워진 직사각형을 만들 수 있다면 그 블록을 없앨 수 있다.
    // 검은 블록을 떨어뜨려 없앨 수 있는 블록 개수의 최댓값

    // 12개 패턴중 없앨 수 있는 블럭 패턴 5개

    // 패턴 (좌, 하 기준)
    int[][][] blockPattern = {
            {{-1, 0}, {0, 1}, {0, 2}}, // ㄴ
            {{0, 1}, {-1, 1}, {-2, 1}}, // L 뒤집은거
            {{0, 1}, {-1, 0}, {-2, 0}}, // L
            {{0, 1}, {0, 2}, {-1, 2}}, // ㄴ 뒤집은거
            {{0, 1}, {0, 2}, {-1, 1}}, // ㅗ
    };

    int[][][] emptyPart = {
            {{-1, 1}, {-1, 2}},
            {{-1, 0}},
            {{-1, 1}},
            {{-1, 0}, {-1, 1}},
            {{-1, 0}, {-1, 2}}
    };
    int n;

    public int solution(int[][] board) {
        n = board.length;
        List<Block> removableBlockList = new ArrayList<>();
        Map<Integer, Boolean> block = new HashMap<>();
        for (int y = n - 1; y >= 0; y--) {
            for (int x = 0; x < n; x++) {
                if (board[y][x] == 0 || block.containsKey(board[y][x])) {
                    continue;
                }

                // 블록 지울 수 있는지 여부 확인
                int patternNum = checkPattern(y, x, board);
                if (patternNum != -1) {
                    removableBlockList.add(new Block(patternNum, y, x));
                    block.put(board[y][x], true);
                } else {
                    block.put(board[y][x], false);
                }
            }
        }
//        System.out.println(removableBlockList);

        // removableBlockList에서 하나씩 지워본다
        int answer = 0;
        boolean isChange = true;
        while (isChange) {
            isChange = false;
            List<Block> remove = new ArrayList<>();

            // 지금 지울 수 있나?
            // 검은 블록 놔둘 좌표 위로 다른 블록이 존재하는지?
            for (int i = 0; i < removableBlockList.size(); i++) {
                Block cur = removableBlockList.get(i);
//                int blockNum = board[cur.y][cur.x];
                boolean canDelete = true;
                // 빈공간 위쪽으로 탐색해서 다른 블록이 있으면 못지운다
                for (int[] pos : emptyPart[cur.type]) {
                    for (int y = cur.y + pos[0]; y >= 0; y--) {
                        if (board[y][cur.x + pos[1]] != 0) {
                            canDelete = false;
                            break;
                        }
                    }
                    if (!canDelete) {
                        break;
                    }
                }

                if (canDelete) {
                    remove.add(cur);
                }
            }

            answer += remove.size();
            // remove에 있는 블록 처리
            if (!remove.isEmpty()) {
                isChange = true;
                for (Block b : remove) {
//                    System.out.println(board[b.y][b.x]);
                    board[b.y][b.x] = 0;
                    for (int[] pos : blockPattern[b.type]) {
                        board[b.y + pos[0]][b.x + pos[1]] = 0;
                    }
                    removableBlockList.remove(b);
                }
            }
        }

        return answer;
    }

    public int checkPattern(int y, int x, int[][] board) {
        int blockNum = board[y][x];

        for (int i = 0; i < blockPattern.length; i++) {
            int count = 0;
            for (int[] weights : blockPattern[i]) {
                int ny = y + weights[0];
                int nx = x + weights[1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

                if (blockNum == board[ny][nx]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == 3) {
                return i;
            }
        }
        return -1;
    }

    private class Block {
        int type;
        // 왼쪽 아래 좌표
        int y;
        int x;

        public Block(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
        }
    }
}
