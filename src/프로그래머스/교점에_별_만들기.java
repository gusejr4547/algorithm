package 프로그래머스;

import java.util.*;

public class 교점에_별_만들기 {
    public String[] solution(int[][] line) {
        int N = line.length;

        // 정수로만 표현되는 교점 좌표
        // line 개수 <= 1000
        // 전부 하나씩하면 1 000 000

        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        List<long[]> pointList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long A = line[i][0];
                long B = line[i][1];
                long E = line[i][2];

                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                // AD - BC
                long determine = A * D - B * C;

                // 평행
                if (determine == 0) {
                    continue;
                }

                long xx = B * F - E * D;
                long yy = E * C - A * F;

                // 정수 교점인지 확인
                if (xx % determine != 0 || yy % determine != 0) {
                    continue;
                }

                long x = xx / determine;
                long y = yy / determine;

                pointList.add(new long[]{x, y});

                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
                // System.out.println(Arrays.toString(pointList.get(pointList.size()-1)));
            }
        }

        // 최적 크기로 맞추기
        int w = (int) (maxX - minX + 1);
        int h = (int) (maxY - minY + 1);

        // * 표시하기
        char[][] grid = new char[h][w];
        for (char[] g : grid) {
            Arrays.fill(g, '.');
        }
        for (long[] point : pointList) {
            // 음수 표현위해 좌표이동
            int row = (int) (maxY - point[1]);
            int col = (int) (point[0] - minX);

            grid[row][col] = '*';
        }

        // String으로 변환
        String[] answer = new String[h];
        for (int i = 0; i < h; i++) {
            answer[i] = new String(grid[i]);
        }


        return answer;
    }
}
