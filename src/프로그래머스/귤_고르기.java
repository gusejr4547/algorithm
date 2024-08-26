package 프로그래머스;

import java.util.*;

public class 귤_고르기 {
    public static void main(String[] args) {
        귤_고르기 Main = new 귤_고르기();
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(Main.solution(k, tangerine));
    }

    Map<Integer, Integer> groupTangerine;

    public int solution(int k, int[] tangerine) {
        // 귤 분류
        groupTangerine = new HashMap<>();
        for (int tan : tangerine) {
            groupTangerine.put(tan, groupTangerine.getOrDefault(tan, 0) + 1);
        }

        List<Integer> ordering = new ArrayList<>(groupTangerine.values());
        ordering.sort(Collections.reverseOrder());

        int answer = 0;
        int total = 0;
        for (int cnt : ordering) {
            total += cnt;
            answer += 1;
            if (total >= k) {
                break;
            }
        }

        return answer;
    }
}
