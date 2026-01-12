package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P11000_강의실_배정 {
    // 최소의 강의실을 사용해서 모든 수업을 가능
    // N <= 200 000
    // S, T <= 1 000 000 000
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Info[] info = new Info[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            info[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // 시작시간 오름차순 정렬
        Arrays.sort(info, (o1, o2) -> Integer.compare(o1.start, o2.start));

        int answer = 0;
        // 끝나는 시간 PQ?
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            // start 시간까지 pq에서 삭제
            while (!pq.isEmpty() && pq.peek() <= info[i].start) {
                pq.poll();
            }

            pq.offer(info[i].end);

            // 최대 강의실 갱신
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }

    private static class Info {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
