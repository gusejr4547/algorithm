package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C_운동장_회전초밥 {
    // 정민, 정화, 은체
    // 폭이 일정한 3개의 트랙
    // 각자 자신의 트랙의 중심선으로 움직임
    //
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int theta = Integer.parseInt(st.nextToken());
        double W = Integer.parseInt(st.nextToken()) / 100.0;

        // 각자 한바퀴 거리 구하기
        // 2D + sin(180-theta)/2 * r *4 +  2*pi*r * theta/360 * 2

        double standard = totalDist(D, theta, R - W / 2);
        double a = totalDist(D, theta, R - W - W / 2);
        double b = totalDist(D, theta, R - W * 2 - W / 2);

        double sSpeed = standard * N / T;
        double aSpeed = a * N / T;
        double bSpeed = b * N / T;

        System.out.println(sSpeed - bSpeed);
        System.out.println(sSpeed - aSpeed);
    }

    private static double totalDist(int D, double theta, double r) {
        return 2 * D + Math.sin(Math.toRadians((180 - theta) / 2)) * r * 4 + 2 * Math.PI * r * theta * 2 / 360;
    }
}
