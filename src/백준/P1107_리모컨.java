package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1107_리모컨 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] isBrokenButton = new boolean[10];
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int button = Integer.parseInt(st.nextToken());
                isBrokenButton[button] = true;
            }
        }

        int result = 0;

        // 목표 100인 경우
        if (N == 100) {
            System.out.println(result);
            return;
        }

        // 100에서 목표까지의 거리
        result = Math.abs(N - 100);

        // 원하는 채널로부터 출발 -> 작은쪽
        int cost = 0;
        int channel = N;
        while (cost + String.valueOf(channel).length() < result) {
            if (channel < 0) {
                break;
            }

            if (isValid(channel, isBrokenButton)) {
                result = cost;
                // 채널 자리수
                result += String.valueOf(channel).length();
                break;
            }
            cost++;
            channel--;
        }

        // 원하는 채널로부터 출발 -> 큰쪽
        cost = 0;
        channel = N;
        while (cost + String.valueOf(channel).length() < result) {
            if (isValid(channel, isBrokenButton)) {
                result = cost;
                // 채널 자리수
                result += String.valueOf(channel).length();
                break;
            }
            cost++;
            channel++;
        }

        System.out.println(result);
    }

    public static boolean isValid(int channel, boolean[] isBrokenButton) {
        int len = String.valueOf(channel).length();
        for (int i = 0; i < len; i++) {
            if (isBrokenButton[channel % 10]) {
                return false;
            }
            channel /= 10;
        }
        return true;
    }
}
