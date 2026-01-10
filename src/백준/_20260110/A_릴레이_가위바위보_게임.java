package 백준._20260110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_릴레이_가위바위보_게임 {
    // 1,2,3 - 가위,바위,보
    // 첫 번째 줄에 오류가 발생한 라운드에서 원래 출력되었어야 할 손동작의 번호를 출력하고,
    // 두 번째 줄에 그 라운드에서 실제로 출력된 손동작의 번호를 출력한다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] hand = new int[4];
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3 * N; i++) {
            int t = Integer.parseInt(st.nextToken());
            hand[t]++;
        }

        int min = 0, max = 0;

        for (int i = 1; i <= 3; i++) {
            if (hand[i] == N - 1) {
                min = i;
            }
            if (hand[i] == N + 1) {
                max = i;
            }
        }

        System.out.println(min);
        System.out.println(max);
    }
}
