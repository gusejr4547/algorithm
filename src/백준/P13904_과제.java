package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 하루에 1개의 과제 할 수 있음
public class P13904_과제 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Assignment> pq = new PriorityQueue<>((o1, o2) -> o2.score - o1.score);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Assignment(d, w));
        }

        int totalScore = 0;
        boolean[] work = new boolean[1001];
        while (!pq.isEmpty()) {
            Assignment assignment = pq.poll();
            if (!work[assignment.day]) {
                work[assignment.day] = true;
                totalScore += assignment.score;
            } else {
                for (int d = assignment.day - 1; d >= 1; d--) {
                    if (work[d])
                        continue;
                    work[d] = true;
                    totalScore += assignment.score;
                    break;
                }
            }
        }

        System.out.println(totalScore);
    }

    private static class Assignment {
        int day, score;

        public Assignment(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }
}
