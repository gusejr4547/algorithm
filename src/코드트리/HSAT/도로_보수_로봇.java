package 코드트리.HSAT;

import java.util.Scanner;

public class 도로_보수_로봇 {
    static int N, K;
    static int[] positions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = scanner.nextInt();
        }

        // 직선 도로 N개 구멍. 구멍위치 >= 0
        // 보수 패치를 이용해 수리 가능.
        // 최대 K개를 사용해서 모든 구멍을 최소 길이의 패치로 효율적으로 수리 해야함.

        // N, K <= 100 000
        // 구멍 위치 <= 1 000 000 000

        // 최대 K개 활용 => K개 활용하는게 제일 좋음.
        // 허용된는 최대 길이를 정해놓고 계산
        int answer = Integer.MAX_VALUE;
        int min = 1;
        int max = 1_000_000_001;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canMake(mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canMake(int limit) {
        int useCnt = 0;
        int s = 0;
        int e = 0;

        while (s < N) {
            while (e < N) {
                int len = positions[e] - positions[s] + 1;
                if (len <= limit) {
                    e++;
                } else {
                    break;
                }
            }
            useCnt++;
            s = e;
        }
//        System.out.println(limit + " " + useCnt);
        return useCnt <= K;
    }
}
