package 백준._20260215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D_Sonic_Wave {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] Y = new int[N];
        for (int i = 0; i < N; i++) {
            Y[i] = Integer.parseInt(st.nextToken());
        }

        // 앞부분 부터 완성시킨다.
        List<Speaker> speakerList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            // 현재 위치에 설치할 스피커의 파워
            // i-2 위치의 파워와 관계있음
            int p = Y[i];
            if (i - 2 >= 0) {
                p += Y[i - 2];
            }

            if (p != 0) {
                int x = i + 1;
                // 양수이면 +3
                if (p > 0) {
                    int theta = 3;
                    speakerList.add(new Speaker(x, p, theta));
                }
                // 음수이면 +1
                else {
                    int theta = 1;
                    speakerList.add(new Speaker(x, -p, theta));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(speakerList.size()).append('\n');
        for (Speaker speaker : speakerList) {
            sb.append(speaker.x).append(' ').append(speaker.v).append(' ').append(speaker.theta).append('\n');
        }
        System.out.println(sb);
    }

    private static class Speaker {
        int x, v, theta;

        public Speaker(int x, int v, int theta) {
            this.x = x;
            this.v = v;
            this.theta = theta;
        }
    }
}
