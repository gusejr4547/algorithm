package 프로그래머스;

import java.util.*;

public class 보석_쇼핑 {
    public static void main(String[] args) {
        보석_쇼핑 Main = new 보석_쇼핑();
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(Main.solution(gems)));
    }

    // 구간 찾기
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        // 일단 보석 종류 몇갠지 확인
        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        int size = gemSet.size();

        int left = 0;
        int right = 0;
        int distance = Integer.MAX_VALUE;

        Map<String, Integer> map = new HashMap<>();
        while (true) {
            if (map.size() == size) {
                String gem = gems[left];
                map.put(gem, map.get(gem) - 1);

                if (map.get(gem) == 0) {
                    map.remove(gem);
                }
                left++;
            } else if (right == gems.length) {
                break;
            } else {
                String gem = gems[right];
                map.put(gem, map.getOrDefault(gem, 0) + 1);
                right++;
            }

            if (map.size() == size) {
                if (right - left < distance) {
                    distance = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }
            }
        }


        return answer;
    }
}
