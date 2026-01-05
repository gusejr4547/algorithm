package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P34930_추진기 {
    // 수직선 원점 위에 시작
    // 세기 L, L+1, L+2, ... , R 추진기 1개씩 가지고 있다
    // 세기 x인 추진기 사용하면 왼쪽 or 오른쪽 x 만큼 이동.

    // 모든 추진기 사용해서 이동하는데 원점에 가장 가깝게 이동하고 싶다.
    // 이동 과정에서 좌표 값이 0에 가깝게

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // 출발점 = R+1/2
            int m = Math.max(L, (R + 1) / 2);
            sb.append(m).append(' ');

            // 오름차순으로 오른쪽 -> 왼쪽 반복하면 되나?
            int v = -1;
            for (int i = R; i >= L; i--) {
                if (i == m) continue;
                sb.append(i * v).append(' ');
                v *= -1;
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
