package 코드트리.HSAT;

import java.util.*;

public class 교차로 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Event[] events = new Event[N];
        for (int i = 0; i < N; i++) {
            int t = sc.nextInt();
            char w = sc.next().charAt(0);
            events[i] = new Event(t, w - 'A');
        }

        // N대의 차. N <= 200 000
        // t초 <= 1 000 000 000

        ArrayDeque<Integer>[] waitingQ = new ArrayDeque[4];
        for (int i = 0; i < 4; i++) {
            waitingQ[i] = new ArrayDeque<>();
        }

        int[] answer = new int[N];
        Arrays.fill(answer, -1);

        int time = -1; // 현재 시간
        for (int i = 0; i < N; i++) {
            int cur = events[i].time;

            // cur 시간 이전까지 동작 수행
            while (time < cur) {
                // 각 위치의 맨 첫번째 확인
                boolean isExist = false;
                for (int d = 0; d < 4; d++) {
                    if (!waitingQ[d].isEmpty()) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    break;
                }

                // 있으면 차량 이동 가능?
                List<Integer> canMove = new ArrayList<>();
                for (int d = 0; d < 4; d++) {
                    // 오른쪽에 비어있으면 움직일 수 있음
                    if (!waitingQ[d].isEmpty() && waitingQ[(d + 3) % 4].isEmpty()) {
                        canMove.add(d);
                    }
                }
                // 움직일 수 있는게 없음
                if (canMove.isEmpty()) {
                    break;
                }

                for (int d : canMove) {
                    int num = waitingQ[d].poll();
                    // 기록
                    answer[num] = time;
                }
                time++;
            }

            // 큐에 넣기
            time = events[i].time;
            waitingQ[events[i].dir].offer(i);
        }
        // 큐에 남은거 수행
        while (true) {
            // 각 위치의 맨 첫번째 확인
            boolean isExist = false;
            for (int d = 0; d < 4; d++) {
                if (!waitingQ[d].isEmpty()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                break;
            }

            // 있으면 차량 이동 가능?
            List<Integer> canMove = new ArrayList<>();
            for (int d = 0; d < 4; d++) {
                // 오른쪽에 비어있으면 움직일 수 있음
                if (!waitingQ[d].isEmpty() && waitingQ[(d + 3) % 4].isEmpty()) {
                    canMove.add(d);
                }
            }
            // 움직일 수 있는게 없음
            if (canMove.isEmpty()) {
                break;
            }

            for (int d : canMove) {
                int num = waitingQ[d].poll();
                // 기록
                answer[num] = time;
            }
            time++;
        }

        for(int i=0; i<N; i++){
            System.out.println(answer[i]);
        }
    }

    private static class Event {
        int time, dir;

        public Event(int time, int dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}
