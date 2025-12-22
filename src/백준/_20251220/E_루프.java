package 백준._20251220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E_루프 {
    // 로봇의 명령어 중 최소 몇 개를 수정해야 로봇이 제자리에서 멈춰 있거나 무한히 같은 경로를 반복하여 이동하도록 만들 수 있는지 출력하라.
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        char[] opSeq = br.readLine().toCharArray();

        // seq 수행후 (0,0)이면 무조건 loop
        // seq 수행후 (0,0)이 아니고 방향이 다르면 loop
        // 방향이 같은경우 방향 1 수정

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            char x = st.nextToken().charAt(0);

            // 변경
            opSeq[idx] = x;

            sb.append(loop(opSeq)).append("\n");
        }

        System.out.println(sb);
    }

    private static int loop(char[] seq) {
        // 몇개 변경해야 loop 도나?
        // L,R,U 3개 명령어
        // URURURU 경우는 4번 반복하면 원래로 돌아옴...
        // seq 한번에 최종 방향하고 최종 위치 구하면?

        int y = 0;
        int x = 0;
        int dir = 0;

        for (int i = 0; i < seq.length; i++) {
            if ('L' == seq[i]) {
                dir = (dir + 3) % 4;
            } else if ('R' == seq[i]) {
                dir = (dir + 1) % 4;
            } else {
                y = y + dy[dir];
                x = x + dx[dir];
            }
        }

        // 처음 위치
        if (y == 0 && x == 0) {
            return 0;
        }
        // 처음 위치아닌데 방향 같음
        else if (dir != 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
