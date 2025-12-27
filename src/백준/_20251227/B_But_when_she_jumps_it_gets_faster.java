package 백준._20251227;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_But_when_she_jumps_it_gets_faster {
    // 영상 L개 장면
    // K개 장면에서 점프

    // 점프 하면 속도가 빨라짐..
    // 초기 1프레임 1장면
    // 점프하면 속도 2,3,4배.... 빨라짐

    // 5배속이면 1프레임에 5개장면 재생...

    // 점프한 다음 프레임부터 속도 빨라짐.
    // 한 프레임에 여러번 점프하면 그만큼 빨라짐

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 점프하는 장면
        st = new StringTokenizer(br.readLine());
        boolean[] jumps = new boolean[L + 1];
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            jumps[idx] = true;
        }

        // 영상을 N번 반복 재생하기위한 프레임 수?
        // N <= 1 000 000 000 000
        // 한번 재생 점프 횟수 누적 합?
        int[] prefix = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            prefix[i] = prefix[i - 1] + (jumps[i] ? 1 : 0);
        }

        System.out.println(Arrays.toString(prefix));

        long scene = 0; // 현재까지 재생한 장면
        long frame = 0; // 현재 프레임
        // 이번 프레임에 몇개의 점프가 있는지 바로 파악, 몇번 영상 수행하고 어느 장면 위치하는지 파악?
        // 지금까지 재생한 장면 개수 알면 점프 횟수 바로 나옴?
        long total = N * L; // 총 재생 장면 개수
        while (scene < total) {
            // 현재 상태
            long loop = scene / L;
            int idx = (int) (scene % L);

            long jumpCount = loop * K + prefix[idx]; // 지금까지 점프횟수
            long fast = jumpCount + 1; // 이번 프레임에 재생하는 장면

            scene += fast;
            frame++;
        }

        System.out.println(frame);
    }
}
