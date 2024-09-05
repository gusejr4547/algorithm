package 프로그래머스;

import java.util.*;

public class 메뉴_리뉴얼 {
    public static void main(String[] args) {
        메뉴_리뉴얼 Main = new 메뉴_리뉴얼();
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(Main.solution(orders, course)));
    }

    Map<String, Integer>[] candidateMapList;

    public String[] solution(String[] orders, int[] course) {
        candidateMapList = new Map[course.length];
        for (int i = 0; i < course.length; i++) {
            candidateMapList[i] = new HashMap<>();
        }

        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            for (int i = 0; i < course.length; i++) {
                int type = course[i];
                if (type > arr.length) break;
                combination(0, type, new ArrayList<>(), arr, i);
            }
        }

        List<String> result = new ArrayList<>();
        for (Map<String, Integer> candidateMap : candidateMapList) {
            int max = 0;
            for (int cnt : candidateMap.values()) {
                max = Math.max(max, cnt);
            }
            if (max == 1) continue;

            for (String key : candidateMap.keySet()) {
                if (candidateMap.get(key) == max) {
                    result.add(key);
                }
            }
        }

        result.sort(Comparator.naturalOrder());
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void combination(int idx, int type, List<Character> select, char[] menuArr, int courseIdx) {
        if (select.size() == type) {
            StringBuilder key = new StringBuilder();
            for (char c : select) {
                key.append(c);
            }
            candidateMapList[courseIdx].put(key.toString(),
                    candidateMapList[courseIdx].getOrDefault(key.toString(), 0) + 1);
        }

        for (int i = idx; i < menuArr.length; i++) {
            select.add(menuArr[i]);
            combination(i + 1, type, select, menuArr, courseIdx);
            select.remove(select.size() - 1);
        }
    }
}
