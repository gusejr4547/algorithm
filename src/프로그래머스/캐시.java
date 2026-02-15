package 프로그래머스;

import java.util.*;

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
        List<String> cache = new ArrayList<>();

        for (String city : cities) {
            city = city.toUpperCase();

            // hit
            if (cache.remove(city)) {
                cache.add(city);
                answer += HIT;
            }
            // miss
            else {
                // 가득참 => 앞에꺼 제거
                if (cache.size() >= cacheSize) {
                    cache.remove(0);
                }

                cache.add(city);
                answer += MISS;
            }
        }

        return answer;
    }
}
