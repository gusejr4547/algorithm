package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 거리두기_확인하기 {
    public static void main(String[] args) {
        거리두기_확인하기 Main = new 거리두기_확인하기();
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(Main.solution(places)));
    }

    char[][][] map;

    public int[] solution(String[][] places) {
        List<Pos>[] personList = new List[5];
        for (int num = 0; num < 5; num++) {
            personList[num] = new ArrayList<>();
        }
        map = new char[5][5][5];
        for (int num = 0; num < 5; num++) {
            for (int i = 0; i < 5; i++) {
                map[num][i] = places[num][i].toCharArray();
                for (int j = 0; j < 5; j++) {
                    if (map[num][i][j] == 'P') {
                        personList[num].add(new Pos(i, j));
                    }
                }
            }
        }

        int[] answer = new int[5];

        for (int num = 0; num < 5; num++) {
            int valid = 1;

            for (int i = 0; i < personList[num].size(); i++) {
                for (int j = i + 1; j < personList[num].size(); j++) {
                    // i와 j가 거리두기 하고 있나?
                    if (!isValid(num, personList[num].get(i), personList[num].get(j))) {
                        valid = 0;
                        break;
                    }
                }
                if (valid == 0) {
                    break;
                }
            }

            answer[num] = valid;
        }

        return answer;
    }

    private boolean isValid(int roomNum, Pos a, Pos b) {
        // 맨해튼 거리 > 2  =>  거리두기 하는중
        int dist = Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
        if (dist > 2) {
            return true;
        }

        // 사이에 벽이 있나?
        // dist = 1인가? => 거리두기 X
        if (dist == 1) {
            return false;
        }

        // 직선상에 있나?
        if (a.y == b.y || a.x == b.x) {
            if (a.y == b.y) {
                return map[roomNum][a.y][(a.x + b.x) / 2] == 'X';
            } else {
                return map[roomNum][(a.y + b.y) / 2][a.x] == 'X';
            }
        }

        // 대각선에 있나?
        int wall = 0;
        if (map[roomNum][a.y][b.x] == 'X') {
            wall++;
        }
        if (map[roomNum][b.y][a.x] == 'X') {
            wall++;
        }

        return wall == 2;
    }

    private class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
