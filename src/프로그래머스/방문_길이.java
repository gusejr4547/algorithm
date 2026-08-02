package 프로그래머스;

import java.util.HashSet;
import java.util.Set;

public class 방문_길이 {
    public static void main(String[] args) {

    }

    public int solution(String dirs) {
        int answer = 0;

        Set<String> visit = new HashSet<>();

        int y = 0;
        int x = 0;

        for (char dir : dirs.toCharArray()) {
            int ny = y;
            int nx = x;

            if ('L' == dir) {
                nx--;
            } else if ('R' == dir) {
                nx++;
            } else if ('U' == dir) {
                ny++;
            } else if ('D' == dir) {
                ny--;
            }

            if (nx < -5 || ny < -5 || nx > 5 || ny > 5) {
                continue;
            }

            visit.add(x + "," + y + " " + nx + "," + ny);
            visit.add(nx + "," + ny + " " + x + "," + y);

            x = nx;
            y = ny;
        }

        answer = visit.size() / 2;
        return answer;
    }

    // 좌표 2배 늘려서 하는거?
//    public int solution2(String dirs) {
//        Set<String> visit = new HashSet<>();
//
//        int y = 0;
//        int x = 0;
//
//        visit.add(x + "," + y);
//
//        for (char dir : dirs.toCharArray()) {
//            int ny = y;
//            int nx = x;
//
//            if ('L' == dir) {
//                nx -= 2;
//            } else if ('R' == dir) {
//                nx += 2;
//            } else if ('U' == dir) {
//                ny += 2;
//            } else if ('D' == dir) {
//                ny -= 2;
//            }
//
//            if (nx < -10 || ny < -10 || nx > 10 || ny > 10) {
//                continue;
//            }
//
//            visit.add(nx + "," + ny);
//            visit.add((x + nx) / 2 + "," + (y + ny) / 2);
//
//            x = nx;
//            y = ny;
//        }
//
//        return visit.size() / 2;
//    }
}
