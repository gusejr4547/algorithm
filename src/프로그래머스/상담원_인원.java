package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 상담원_인원 {
    public static void main(String[] args) {
        상담원_인원 Main = new 상담원_인원();
        int k = 3;
        int n = 5;
        int[][] reqs = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        System.out.println(Main.solution(k, n, reqs));
    }

    // 먼저 상담 요청한 참가자가 우선됩니다.
    // 참가자가 기다린 시간은 참가자가 상담 요청했을 때부터 멘토와 상담을 시작할 때까지의 시간입니다.
    // 기다린 시간의 합이 최소

    List<Consulting>[] consultLists;

    /**
     * @param k    상담 유형의 수
     * @param n    멘토의 수
     * @param reqs 참가자의 상담 요청 [a분에 요청, b분 동안, c유형]
     */
    public int solution(int k, int n, int[][] reqs) {
        // 상담 유형 별로 나눈다
        consultLists = new List[k + 1];
        for (int i = 1; i <= k; i++) {
            consultLists[i] = new ArrayList<>();
        }

        for (int[] req : reqs) {
            consultLists[req[2]].add(new Consulting(req[0], req[1]));
        }

        // 상담 별로 인원 배분했을 때 소요되는 시간 기록
        int[][] waitTimeMemo = new int[k + 1][n - k + 1 + 1];
        for (int type = 1; type <= k; type++) {
            calWaitTime(type, waitTimeMemo[type]);
        }


        // 상담사 분배 dfs
        return dfs(n - k, 1, waitTimeMemo);
    }

    public int dfs(int maxWorker, int type, int[][] waitTimeMemo) {

        int totalWaitTime = Integer.MAX_VALUE;
        for (int i = 0; i <= maxWorker; i++) {
            int waitTime = waitTimeMemo[type][i + 1];
            if (type < waitTimeMemo.length - 1) {
                waitTime += dfs(maxWorker - i, type + 1, waitTimeMemo);
            }
            totalWaitTime = Math.min(totalWaitTime, waitTime);
        }

        return totalWaitTime;
    }

    public void calWaitTime(int type, int[] waitTime) {
        for (int maxWorker = 1; maxWorker < waitTime.length; maxWorker++) {
            int waitTimeTotal = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // 일할 사람 넣기
            for (int i = 0; i < maxWorker; i++) {
                pq.offer(0);
            }

            for (int i = 0; i < consultLists[type].size(); i++) {
                Consulting cur = consultLists[type].get(i);

                if (!pq.isEmpty() && cur.requestTime < pq.peek()) {
                    // 요청 들어온 상담 시간보다 현재 일하는 사람이 늦게 상담하는 경우
                    waitTimeTotal += pq.peek() - cur.requestTime;
                    pq.offer(pq.poll() + cur.consultTime);
                } else {
                    // 상담할 사람이 있는 경우
                    pq.poll();
                    pq.offer(cur.requestTime + cur.consultTime);
                }
            }
            waitTime[maxWorker] = waitTimeTotal;
        }
    }

    public class Consulting {
        int requestTime;
        int consultTime;

        public Consulting(int requestTime, int consultTime) {
            this.requestTime = requestTime;
            this.consultTime = consultTime;
        }
    }
}
