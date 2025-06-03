package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P31877_EDF {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 작업
        PriorityQueue<Work> workQ = new PriorityQueue<>((o1, o2) -> o1.deadline - o2.deadline == 0 ? o1.time - o2.time : o1.deadline - o2.deadline);
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            workQ.offer(new Work(t, d));
        }

        // 추가 작업
        PriorityQueue<AdditionalWork> additionalWorks = new PriorityQueue<>((o1, o2) -> o1.addTime - o2.addTime);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            additionalWorks.offer(new AdditionalWork(w, t, d));
        }

        int curTime = 0;

        while (!workQ.isEmpty() || !additionalWorks.isEmpty()) {
            if (workQ.isEmpty()) {
                int nextTime = additionalWorks.peek().addTime;
                while (!additionalWorks.isEmpty() && additionalWorks.peek().addTime <= nextTime) {
                    workQ.offer(additionalWorks.poll());
                }
                curTime = nextTime;
            }

            Work doing = workQ.poll();

            // 도중에 새로운 작업 하는 경우
            if (!additionalWorks.isEmpty() && additionalWorks.peek().addTime < curTime + doing.time) {
                int nextTime = additionalWorks.peek().addTime;
                workQ.offer(new Work(curTime + doing.time - nextTime, doing.deadline));
                while (!additionalWorks.isEmpty() && additionalWorks.peek().addTime < curTime + doing.time) {
                    workQ.offer(additionalWorks.poll());
                }
                curTime = nextTime;
            }
            // 현재 작업을 처리
            else {
                if (curTime + doing.time <= doing.deadline) {
                    curTime += doing.time;
                } else {
                    workQ.offer(doing);
                    break;
                }
            }
        }


        if (workQ.isEmpty() && additionalWorks.isEmpty()) {
            System.out.println("YES");
            System.out.println(curTime);
        } else {
            System.out.println("NO");
        }
    }

    private static class Work {
        int time, deadline;

        public Work(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }
    }

    private static class AdditionalWork extends Work {
        int addTime;

        public AdditionalWork(int addTime, int time, int deadline) {
            super(time, deadline);
            this.addTime = addTime;
        }
    }
}
