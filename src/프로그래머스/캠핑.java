package 프로그래머스;

import java.util.*;

public class 캠핑 {
    public static void main(String[] args) {
        캠핑 Main = new 캠핑();
        int n = 4;
        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
        System.out.println(Main.solution(n, data));
    }

    // 영역 내부에 점이 있는지 확인하는게 포인트
    // 선택할 수 있는 두 점은 y 나 x 좌표가 같지 않으면 된다.

    public int solution(int n, int[][] data) {
        // 좌표 압축
        Set<Integer> x = new HashSet<>();
        Set<Integer> y = new HashSet<>();

        for (int i = 0; i < n; i++) {
            x.add(data[i][0]);
            y.add(data[i][1]);
        }

        List<Integer> sortedX = new ArrayList<>(x);
        List<Integer> sortedY = new ArrayList<>(y);
        Collections.sort(sortedX);
        Collections.sort(sortedY);

        for (int i = 0; i < n; i++) {
            data[i][0] = sortedX.indexOf(data[i][0]);
            data[i][1] = sortedY.indexOf(data[i][1]);
        }

        // 2차원 구간합 => 점이 몇개 있는지
        int[][] prefixSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            prefixSum[data[i][0]][data[i][1]] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum[i][j] += (i >= 1 ? prefixSum[i - 1][j] : 0)
                        + (j >= 1 ? prefixSum[i][j - 1] : 0)
                        - (i >= 1 && j >= 1 ? prefixSum[i - 1][j - 1] : 0);
            }
        }

        // 2점을 선택했을 때 구간합을 통해 점의 개수를 구함
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                int x1, x2, y1, y2;
                if (data[i][0] < data[j][0]) {
                    x1 = data[i][0];
                    x2 = data[j][0];
                } else {
                    x2 = data[i][0];
                    x1 = data[j][0];
                }
                if (data[i][1] < data[j][1]) {
                    y1 = data[i][1];
                    y2 = data[j][1];
                } else {
                    y2 = data[i][1];
                    y1 = data[j][1];
                }

                // 사각형 내부로
                int pointCnt = 0;
                if (x1 + 1 > x2 - 1 || y1 + 1 > y2 - 1) {
                    pointCnt = 0;
                } else {
                    pointCnt = prefixSum[x2 - 1][y2 - 1] - prefixSum[x1][y2 - 1] - prefixSum[x2 - 1][y1] + prefixSum[x1][y1];
                }

                if (pointCnt == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
