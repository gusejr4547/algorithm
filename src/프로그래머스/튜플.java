package 프로그래머스;

import java.util.*;

public class 튜플 {
    public static void main(String[] args) {
        튜플 Main = new 튜플();
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(Main.solution(s)));
    }

    // 특정 튜플을 표현하는 집합이 담긴 문자열 s가 매개변수로 주어질 때, s가 표현하는 튜플을 배열에 담아 return
    public int[] solution(String s) {
        // 원소 1개짜리 => 1번
        // 원소 2개짜리 => 이전 원소 말고 다른거 2번
        // ---
        // s 중에서 { } 에 가장 많은 원소 들어가 있는 것 까지 계속 수행

        Map<Integer, List<Integer>> m = new HashMap<>();

        int i = 1;
        while (i < s.length() - 1) {
            if ('{' == s.charAt(i)) {
                int j = i + 1;
                while ('}' != s.charAt(j)) {
                    j++;
                }

                StringTokenizer st = new StringTokenizer(s.substring(i + 1, j), ",");
                List<Integer> list = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    list.add(Integer.parseInt(st.nextToken()));
                }

                m.put(list.size(), list);

                i = j;
            }
            i++;
        }

        List<Integer> answer = new ArrayList<>();

        for (int size = 1; size <= m.size(); size++) {
            List<Integer> list = m.get(size);
            list.removeAll(answer);

            answer.add(list.get(0));
        }


        return answer.stream().mapToInt(v -> v).toArray();
    }
}
