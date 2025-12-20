package 백준._20251220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C_블록_생성 {
    // 트랜잭션 = 생성 시간, 수수료
    // 현재 시간으로부터 T이하 차이나는 트랜잭션 중에서 수수료가 큰거부터 K개 꺼내기 가능. => 수수료들의 합을 얻음.
    // 수수료가 같으면, 먼저 생성된거 선택, K보다 적으면 모두 꺼내기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long answer = 0;

        PriorityQueue<Block> pool = new PriorityQueue<>((o1, o2) ->
                o2.value == o1.value ? Long.compare(o1.time, o2.time) : Long.compare(o2.value, o1.value));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int f = Integer.parseInt(st.nextToken()); // 수수료
                pool.offer(new Block(t, f));
            } else if (type == 2) {
                // t를 기준으로 T 이하로 차이나는 트랜잭션... => t - T <= x

                int count = 0;
                while (count < K && !pool.isEmpty()) {
                    Block b = pool.poll();
                    if (t - T > b.time) {
                        continue;
                    }

                    if (t - T <= b.time) {
                        count++;
                        answer += b.value;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static class Block {
        long time, value;

        public Block(long time, long value) {
            this.time = time;
            this.value = value;
        }
    }
}
