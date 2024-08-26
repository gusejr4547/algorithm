package 프로그래머스;

import java.util.*;

public class 무지의_먹방_라이브 {
    public static void main(String[] args) {
        무지의_먹방_라이브 Main = new 무지의_먹방_라이브();
        int[] food_times = {3, 1, 2};
        long k = 5;
        System.out.println(Main.solution(food_times, k));
    }


    //  시작한 지 K 초 후에 네트워크 장애로 인해 방송이 잠시 중단
    public int solution(int[] food_times, long k) {
        int answer = -1;
        int foodType = food_times.length;

        PriorityQueue<Food> pq = new PriorityQueue<>((o1, o2) -> o1.time == o2.time ? o1.foodIdx - o2.foodIdx : o1.time - o2.time);
        for (int i = 0; i < foodType; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }
        int size = pq.size();
        int roundCnt = 0;

        while (!pq.isEmpty()) {
            Food food = pq.peek();

            // 해당 음식을 다 먹기 위해 몇바퀴 회전?, 시간 얼마나?
            long totalTime = (long) (food.time - roundCnt) * size;

            // 남은 k시간안에 해결 가능
            if (totalTime <= k) {
                k -= totalTime;
                size -= 1;
                roundCnt = food.time;
                pq.poll();
            }
            // 다먹는데 남은 k시간보다 더걸린다
            else {
                int move = (int) (k % size);

                List<Food> leftFood = new ArrayList<>(pq);
                leftFood.sort((o1, o2) -> o1.foodIdx - o2.foodIdx);

                Food f = leftFood.get(move);
                answer = f.foodIdx;
                break;
            }
        }

        return answer;
    }

    private class Food {
        int foodIdx;
        int time;

        public Food(int foodIdx, int time) {
            this.foodIdx = foodIdx;
            this.time = time;
        }
    }
}
