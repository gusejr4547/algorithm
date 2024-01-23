package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 카운트_다운 {
    public static void main(String[] args) {
        카운트_다운 Main = new 카운트_다운();
        int target = 58;
        System.out.println(Arrays.toString(Main.solution(target)));
    }

    // 최소 다트
    // 먼저 고려 -> 싱글, 불
    public int[] solution(int target) {
        int[] answer = new int[2];
        State result = bfs(target);
        answer[0] = result.throwCount;
        answer[1] = result.singleAndBullCount;
        return answer;
    }

    public State bfs(int start) {
        final int BULL = 50;
        PriorityQueue<State> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[start + 1];
        pq.offer(new State(start, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.score == 0) {
                return curr;
            }

            if (visit[curr.score]) continue;
            visit[curr.score] = true;

            // 불
            if (curr.score - BULL >= 0 && !visit[curr.score - BULL]) {
                pq.offer(new State(curr.score - BULL,
                        curr.throwCount + 1, curr.singleAndBullCount + 1));
            }
            // 일반
            for (int target = 1; target <= 20; target++) {
                for (int multi = 1; multi <= 3; multi++) {
                    if (curr.score - target * multi >= 0 && !visit[curr.score - target * multi]) {
                        pq.offer(new State(curr.score - target * multi,
                                curr.throwCount + 1,
                                multi == 1 ? curr.singleAndBullCount + 1 : curr.singleAndBullCount));
                    }
                }
            }
        }
        return new State(0, 0, 0);
    }


    public class State implements Comparable<State> {
        int score;
        int throwCount;
        int singleAndBullCount;

        public State(int score, int throwCount, int singleAndBullCount) {
            this.score = score;
            this.throwCount = throwCount;
            this.singleAndBullCount = singleAndBullCount;
        }

        @Override
        public int compareTo(State o) {
            if (this.throwCount == o.throwCount) {
                if (this.singleAndBullCount == o.singleAndBullCount) {
                    return this.score - o.score;
                }
                return o.singleAndBullCount - this.singleAndBullCount;
            }
            return this.throwCount - o.throwCount;
        }
    }
}
