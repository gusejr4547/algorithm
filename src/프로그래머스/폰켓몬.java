package 프로그래머스;

import java.util.HashMap;
import java.util.Map;

public class 폰켓몬 {
    public static void main(String[] args) {

    }

    public int solution(int[] nums) {
        int answer = 0;

        int N = nums.length;

        Map<Integer, Integer> mons = new HashMap<>();
        for (int n : nums) {
            mons.put(n, mons.getOrDefault(n, 0) + 1);
        }

        return Math.min(N / 2, mons.size());
    }
}
