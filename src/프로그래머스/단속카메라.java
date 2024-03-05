package 프로그래머스;

import java.util.Arrays;

public class 단속카메라 {
    public static void main(String[] args) {
        단속카메라 Main = new 단속카메라();
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(Main.solution(routes));
    }

    public int solution(int[][] routes) {
        Arrays.sort(routes, (e1, e2) -> e1[1] - e2[1]);

        int answer = 0;
        int camera = -30_001;

        for (int[] route : routes) {
            if (camera < route[0]) {
                camera = route[1];
                answer += 1;
            }
        }

        return answer;
    }
}
