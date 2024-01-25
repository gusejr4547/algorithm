package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P20366_같이_눈사람_만들래 {
    static int N;
    static int[] snow;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        snow = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);
        int min = Integer.MAX_VALUE;
        // 두 값을 먼저 선택
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int snowman1 = snow[i] + snow[j];

                // 차이 최소 구하기
                int left = 0;
                int right = N - 1;
                while (left < right) {
                    if (left == i || left == j) {
                        left++;
                        continue;
                    }
                    if (right == i || right == j) {
                        right--;
                        continue;
                    }

                    int snowman2 = snow[left] + snow[right];
                    min = Math.min(min, Math.abs(snowman1 - snowman2));
                    if (snowman2 > snowman1) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        System.out.println(min);
    }
}
