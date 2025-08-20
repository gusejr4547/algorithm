package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3020_개똥벌레 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] wall = new int[H + 1];

        // 차분 배열 트릭
        // 장애물의 위치를 +1 하기위함
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());

            // 아래쪽에서 자란거
            if (i % 2 == 0) {
                wall[H - size] += 1;
                wall[H] -= 1;
            }
            // 위쪽에서 자란거
            else {
                wall[0] += 1;
                wall[size] -= 1;
            }
        }

        for (int i = 1; i <= H; i++) {
            wall[i] += wall[i - 1];
        }

//        System.out.println(Arrays.toString(wall));

        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < H; i++) {
            if (wall[i] < min) {
                min = wall[i];
                count = 1;
            } else if (wall[i] == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}
