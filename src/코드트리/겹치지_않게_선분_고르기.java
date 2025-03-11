package 코드트리;

import java.util.Arrays;
import java.util.Scanner;

public class 겹치지_않게_선분_고르기 {
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(segments, (o1, o2) -> o1[0] - o2[0]);
        answer = 0;
        selectSegment(0, 0, 0, segments);
        System.out.println(answer);
    }

    private static void selectSegment(int count, int right, int idx, int[][] segments) {
        if (idx == segments.length) {
            answer = Math.max(answer, count);
            return;
        }

        for (int i = idx; i < segments.length; i++) {
            if (right < segments[i][0]) {
                selectSegment(count + 1, segments[i][1], i + 1, segments);
            }
        }
    }
}
