package 프로그래머스;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 캐시 {
    public static void main(String[] args) {
        캐시 Main = new 캐시();
        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(Main.solution(cacheSize, cities));
    }

    // 캐시 교체 알고리즘 LRU(Least Recently Used)
    // hit 실행시간 1
    // miss 실행시간 5
    // 대소문자 구분을 하지 않는다

    public final int HIT = 1;
    public final int MISS = 5;

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * MISS;
        }

        int answer = 0;
        Map<String, Integer> usedTime = new HashMap<>();
        PriorityQueue<Cache> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            // hit
            if (usedTime.containsKey(city)) {
                pq.offer(new Cache(city, i));
                usedTime.put(city, i);
                answer += HIT;
            }
            // miss
            else {
                // 교체 필요
                if (usedTime.size() == cacheSize) {
                    // pq에서 유효한거 1개 제거
                    while (!pq.isEmpty()) {
                        Cache c = pq.poll();
                        // c.city가 usedTime에 있고, time도 같으면 break
                        if (usedTime.containsKey(c.city) && usedTime.get(c.city) == c.time) {
                            // city를 usedTime에서 제거
                            usedTime.remove(c.city);
                            break;
                        }
                    }
                }

                // 새거 넣기
                pq.offer(new Cache(city, i));
                usedTime.put(city, i);
                answer += MISS;
            }
        }

        return answer;
    }

    private class Cache {
        String city;
        int time;

        public Cache(String city, int time) {
            this.city = city;
            this.time = time;
        }
    }
}
