package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P28361_크리스마스 {
    // 1~N => 원형
    // 1번에서 시작해서 1번으로 돌아옴

    // 현재 집에서 거리가 2 이하인 집에 갈수있다
    // 3번 연속 같은 방향 x
    // 같은 집 2번 연속 방문 x => 다른 집 거친 다음에 다시 방문 가능

    // 적게 이동하며 모든 집 방문하고 1번으로 돌아올때, 이동 횟수, 방문 순서 출력
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // N <= 1 000 000

        StringBuilder sb = new StringBuilder();
        sb.append(N).append('\n');
//        sb.append("1 ");

        int now = 1;
        // +2, -1, +2 패턴 사용
        // 3의 배수
        // N%3==0
        if (N % 3 == 0) {
            for (int i = 1; i <= N / 3; i++) {
                sb.append(now).append(' ').append(now + 2).append(' ').append(now + 1).append(' ');
                now += 3;
            }
        }
        // 1
        else if (N % 3 == 1) {
            for (int i = 1; i <= N / 3; i++) {
                sb.append(now).append(' ').append(now + 2).append(' ').append(now + 1).append(' ');
                now += 3;
            }
            // 남은거
            sb.append(N).append(' ');
        }
        // 2
        else if (N % 3 == 2) {
            // 시작 + 1을 먼저 하고 패턴 수행
            sb.append(now).append(' ');
            now++;
            for (int i = 1; i <= N / 3; i++) {
                sb.append(now).append(' ').append(now + 2).append(' ').append(now + 1).append(' ');
                now += 3;
            }
            // 남은거
            sb.append(N).append(' ');
        }

        // 마지막 1로
        sb.append(1);

        System.out.println(sb);
    }
}
