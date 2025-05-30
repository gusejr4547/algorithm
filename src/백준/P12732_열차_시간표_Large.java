package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P12732_열차_시간표_Large {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= N; tc++) {
            int T = Integer.parseInt(br.readLine()); // 회차 시간
            StringTokenizer st = new StringTokenizer(br.readLine());
            int NA = Integer.parseInt(st.nextToken());
            int NB = Integer.parseInt(st.nextToken());

            int[][] trainA = new int[NA][2];
            // A에서 B로 이동하는 열차의 정보
            for (int i = 0; i < NA; i++) {
                st = new StringTokenizer(br.readLine());
                String start = st.nextToken();
                String end = st.nextToken();
                trainA[i][0] = timeToMinute(start);
                trainA[i][1] = timeToMinute(end);
            }
            int[][] trainB = new int[NB][2];
            // B에서 A로 이동하는 열차의 정보
            for (int i = 0; i < NB; i++) {
                st = new StringTokenizer(br.readLine());
                String start = st.nextToken();
                String end = st.nextToken();
                trainB[i][0] = timeToMinute(start);
                trainB[i][1] = timeToMinute(end);
            }

            PriorityQueue<TrainTime> schedule = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
            for (int i = 0; i < NA; i++) {
                schedule.offer(new TrainTime('A', trainA[i][0], trainA[i][1]));
            }
            for (int i = 0; i < NB; i++) {
                schedule.offer(new TrainTime('B', trainB[i][0], trainB[i][1]));
            }

            int startA = 0;
            int startB = 0;

            int waitingA = 0;
            int waitingB = 0;

            PriorityQueue<TrainTime> run = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

            while (!schedule.isEmpty()) {
                TrainTime trainTime = schedule.poll();

                // 출발시간 이전 운행 종료 열차 처리
                while (!run.isEmpty() && run.peek().end + T <= trainTime.start) {
                    TrainTime wait = run.poll();
                    if ('A' == wait.station) {
                        waitingB++;
                    } else if ('B' == wait.station) {
                        waitingA++;
                    }
                }

                // 대기중인 기차 확인
                if ('A' == trainTime.station) {
                    if (waitingA == 0) {
                        startA++;
                    } else {
                        waitingA--;
                    }
                } else if ('B' == trainTime.station) {
                    if (waitingB == 0) {
                        startB++;
                    } else {
                        waitingB--;
                    }
                }

                run.offer(trainTime);
            }


            sb.append("Case #").append(tc).append(": ").append(startA).append(" ").append(startB).append("\n");
        }
        System.out.println(sb);
    }

    private static int timeToMinute(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    private static class TrainTime {
        char station;
        int start, end;

        public TrainTime(char station, int start, int end) {
            this.station = station;
            this.start = start;
            this.end = end;
        }
    }
}
