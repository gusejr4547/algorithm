package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
        Arrays.sort(data, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                boolean valid = true;
                for (int k = i + 1; k < j; k++) {
                    // 안에 점이 있는 경우
                    if (data[i][0] < data[k][0] && data[k][0] < data[j][0]
                            && Math.min(data[i][1], data[j][1]) < data[k][1] && data[k][1] < Math.max(data[i][1], data[j][1])) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
