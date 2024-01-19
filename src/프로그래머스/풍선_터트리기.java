package 프로그래머스;

import java.util.Arrays;

public class 풍선_터트리기 {
    public static void main(String[] args) {
        풍선_터트리기 Main = new 풍선_터트리기();
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(Main.solution(a));
    }

    public int solution(int[] a) {
        int len = a.length;
        int[] leftMin = new int[len];
        int[] rightMin = new int[len];

        leftMin[0] = a[0];
        for (int i = 1; i < len - 1; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }
        rightMin[len - 1] = a[len - 1];
        for (int i = len - 2; i > 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int answer = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                System.out.println(a[i]);
                answer++;
            } else if (i == len - 1) {
                System.out.println(a[i]);
                answer++;
            } else {
                if (a[i] > leftMin[i - 1] && a[i] > rightMin[i + 1])
                    continue;
                System.out.println(a[i]);
                answer++;
            }
        }

        return answer;
    }
}
