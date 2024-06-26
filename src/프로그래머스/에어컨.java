package 프로그래머스;

import java.util.PriorityQueue;

public class 에어컨 {
    public static void main(String[] args) {
        에어컨 Main = new 에어컨();
        int temperature = -10;
        int t1 = -5;
        int t2 = 5;
        int a = 5;
        int b = 1;
        int[] onboard = {0, 0, 0, 0, 0, 1, 0};
        System.out.println(Main.solution(temperature, t1, t2, a, b, onboard));
    }

    // 에어컨의 희망온도와 실내온도가 다르다면 매 분 전력을 a만큼 소비하고
    // 희망온도와 실내온도가 같다면 매 분 전력을 b만큼 소비합니다.
    // 에어컨이 꺼져 있다면 전력을 소비하지 않으며, 에어컨을 켜고 끄는데 필요한 시간과 전력은 0이라고 가정합니다.
    // 차내에 승객이 탑승 중일 때, 실내온도를 t1 ~ t2도 사이로 유지하면서, 에어컨의 소비전력을 최소화
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = 0;

        // 에너지 최소로 마지막 시간까지 먼저 도달하는 것이 결과
        PriorityQueue<MyEnergy> pq = new PriorityQueue<>((e1, e2) -> e1.totalEnergy - e2.totalEnergy);
        pq.offer(new MyEnergy(0, 0, temperature));

        while (!pq.isEmpty()) {
            MyEnergy cur = pq.poll();

            // 끝내는 조건
            if (cur.time == onboard.length - 1 &&
                    (onboard[cur.time] == 0 || (onboard[cur.time] == 1 && t1 <= cur.temperature && cur.temperature <= t2))) {
                answer = cur.totalEnergy;
                break;
            }

            // 승객 있는데 실내온도 못맞춘경우
            if (onboard[cur.time] == 1 && (cur.temperature < t1 || t2 < cur.temperature)) {
                continue;
            }

            // 에어컨 켜기
            // 온도 변화
            pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy + a, cur.temperature - 1));
            pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy + a, cur.temperature + 1));
            // 온도 유지 -> 에어컨 킴
            if (cur.temperature < temperature) {
                pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy + b, cur.temperature));
            }
            // 에어컨 끄기
            if (cur.temperature < temperature) {
                pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy, cur.temperature + 1));
            } else if (cur.temperature == temperature) {
                pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy, cur.temperature));
            } else {
                pq.offer(new MyEnergy(cur.time + 1, cur.totalEnergy, cur.temperature - 1));
            }
        }

        return answer;
    }

    public class MyEnergy {
        int time;
        int totalEnergy;
        int temperature;

        public MyEnergy(int time, int totalEnergy, int temperature) {
            this.time = time;
            this.totalEnergy = totalEnergy;
            this.temperature = temperature;
        }
    }
}
