package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1781_컵라면 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cupRamenCnt = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadLine, cupRamenCnt);
        }

        // 문제 푸는데 단위시간 1이 걸림.
        // 데드라인에 최대한 가깝게 푸는게 맞음

        // 전체 문제 시간 내림차순
        Arrays.sort(problems, (o1, o2) -> Integer.compare(o2.deadLine, o1.deadLine));
        // 현재 풀수 있는 문제 컵라면 개수 많은거 내림차순 pq
        PriorityQueue<Problem> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cupRamenCnt, o1.cupRamenCnt));

        int answer = 0;
        int idx = 0;
        // 최대 시간
        int time = problems[0].deadLine;
        while (time > 0) {
            // pq에 넣기
            while (idx < N && problems[idx].deadLine >= time) {
                pq.offer(problems[idx]);
                idx++;
            }

            // pq에서 빼기
            if (!pq.isEmpty()) {
                answer += pq.poll().cupRamenCnt;
            }

            time--;
        }

        System.out.println(answer);
    }

    private static class Problem {
        int deadLine, cupRamenCnt;

        public Problem(int deadLine, int cupRamenCnt) {
            this.deadLine = deadLine;
            this.cupRamenCnt = cupRamenCnt;
        }
    }
}
