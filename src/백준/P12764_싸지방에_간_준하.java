package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P12764_싸지방에_간_준하 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] logs = new int[N][2]; // 이용시작시각, 종료시각
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            logs[i][0] = Integer.parseInt(st.nextToken());
            logs[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(logs, (o1, o2) -> o1[0] - o2[0]);

        int maxPC = 0;
        Map<Integer, Integer> useCount = new HashMap<>();
        PriorityQueue<Status> pq = new PriorityQueue<>((o1, o2) -> o1.endTime - o2.endTime);
        PriorityQueue<Integer> emptyPC = new PriorityQueue<>((o1, o2) -> o1 - o2); // pc의 번호를 저장

        for (int[] log : logs) {
            // 지난 시간 처리
            while (!pq.isEmpty() && pq.peek().endTime <= log[0]) {
                Status status = pq.poll();
                emptyPC.offer(status.pcNum);
            }

            // pc배정
            int pcNum;
            if (emptyPC.isEmpty()) {
                // 컴퓨터 증설
                maxPC++;
                pcNum = maxPC;
            } else {
                pcNum = emptyPC.poll();
            }
            pq.offer(new Status(log[1], pcNum));
            // 기록
            useCount.put(pcNum, useCount.getOrDefault(pcNum, 0) + 1);
        }

        System.out.println(maxPC);
        for (int i = 1; i <= maxPC; i++) {
            System.out.print(useCount.getOrDefault(i, 0) + " ");
        }
    }

    private static class Status {
        int endTime, pcNum;

        public Status(int endTime, int pcNum) {
            this.endTime = endTime;
            this.pcNum = pcNum;
        }
    }
}
