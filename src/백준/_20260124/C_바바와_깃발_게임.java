package 백준._20260124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C_바바와_깃발_게임 {
    // 1xN게임판
    // 바바를 조종해 바바가 깃발과 같은 칸에 놓이게 되는 순간 게임에서 승리하며 게임은 즉시 종료된다.
    // 게임이 종료된 후에는 추가적인 명령어를 입력할 수 없다. => 모든 명령어 수행전에 끝나면 안됨
    // 명령어 입력해서 승리할 수 있는 게임판이 있는지 ?
    // 없으면 DEFEAT
    // 있으면 WIN, 바바의 위치 B, 깃발의 위치 F

    // N <= 200_000
    // L <= 200_000
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        char[] op = br.readLine().toCharArray();

        // 마지막 명령어이전 바바의 움직임 범위
        // 최종 위치
        // 범위안에 최종 위치가 포함되면 안됨

        // 0을 기준으로 범위 찾기
        int p = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < L - 1; i++) {
            if ('L' == op[i]) {
                p--;
            } else if ('R' == op[i]) {
                p++;
            }

            left = Math.min(left, p);
            right = Math.max(right, p);
        }

        // 마지막 위치
        int last = p;
        if ('L' == op[L - 1]) {
            last--;
        } else if ('R' == op[L - 1]) {
            last++;
        }

        // 마지막이 L
        if ('L' == op[L - 1]) {
            // 깃발이 범위 left보다 왼쪽에 있어야함
            if (last < left) {
                // 깃발을 1번
                int start = 1 - last;

                // 1xN범위
                if (start <= N) {
                    System.out.println("WIN");
                    System.out.println(start + " " + 1);
                }
                // 범위 밖?
                else {
                    System.out.println("DEFEAT");
                }
            } else {
                System.out.println("DEFEAT");
            }
        } else {
            if (right < last) {
                // 깃발을 N번
                int start = N - last;

                if (start >= 1) {
                    System.out.println("WIN");
                    System.out.println(start + " " + N);
                } else {
                    System.out.println("DEFEAT");
                }
            } else {
                System.out.println("DEFEAT");
            }
        }
    }
}
