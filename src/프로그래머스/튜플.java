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

        // 양끝 괄호 '{{', '}}' 제거 후 '},{' 기준으로 분리
        s = s.substring(2, s.length() - 2);
        String[] arr = s.split("},\\{");

        // 길이 순서 정렬
        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());


        Set<Integer> set = new HashSet<>();
        List<Integer> answer = new ArrayList<>();

        for (String str : arr) {
            StringTokenizer st = new StringTokenizer(str, ",");
            while (st.hasMoreTokens()) {
                int n = Integer.parseInt(st.nextToken());
                if (!set.contains(n)) {
                    set.add(n);
                    answer.add(n);
                }
            }
        }

        return answer.stream().mapToInt(v -> v).toArray();
    }
}
