package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P35274_제설_작업_2 {
    static int N, L;
    static long M;
    static long[] A;
    static long[] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 구역
        M = Long.parseLong(st.nextToken()); // 사용횟수
        L = Integer.parseInt(st.nextToken()); // 최대 연속구간
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        // 제설도구는 1회에 L개 이하의 연속한 구역을 골라 총합 X이하의 눈을 제거 => 전부 제거가 안되어도 됨
        // 제설도구는 최대 M번 사용가능
        // 눈을 치우기 위해서 필요한 제설 도구의 성능 X 최소값?

        // N <= 500 000
        // M <= 1 000 000 000 000 000
        // A[i] <= 10 000 000 000

        long left = 1;
        long right = 1_000_000_000_000_000L;
        long answer = -1;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isValid(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(long X) {
        long cnt = 0;

        ArrayDeque<Work> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // 만료된 작업은 제거
            while (!queue.isEmpty() && queue.peek().end < i) {
                queue.poll();
            }

            long snow = A[i];

            // 기존에 작업 있는것으로 처리
            while (snow > 0 && !queue.isEmpty()) {
                Work w = queue.peek(); // 일단 꺼내지는 않고
                long use = Math.min(snow, w.x);
                snow -= use;
                w.x -= use;

                // 남은 작업량이 없으면 제거
                if (w.x == 0) {
                    queue.poll();
                }
            }

            // 아직 snow 남았으면 작업 추가
            if (snow > 0) {
                // 몇번 작업더해야됨?
                long k = (snow + X - 1) / X; // 올림
                cnt += k;

                // 작업 개수 M개 넘으면 false
                if (cnt > M) {
                    return false;
                }

                // snow 처리
                long remain = X * k - snow;

                // 남으면 queue에 추가
                if (remain > 0) {
                    queue.offer(new Work(Math.min(N - 1, i + L - 1), remain));
                }
            }
        }

        return cnt <= M;
    }

    private static class Work {
        int end; // 작업 마지막 구간
        long x; // 작업량

        public Work(int end, long x) {
            this.end = end;
            this.x = x;
        }
    }
}
