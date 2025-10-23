package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2022;

import java.util.*;

public class 신고_결과_받기 {
    public static void main(String[] args) {
        신고_결과_받기 Main = new 신고_결과_받기();
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        System.out.println(Arrays.toString(Main.solution(id_list, report, k)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> nameIdx = new HashMap<>();
        Map<Integer, Set<Integer>> reportedCount = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            nameIdx.put(id_list[i], i);
            reportedCount.put(i, new HashSet<>());
        }

        for (String detail : report) {
            String[] name = detail.split(" ");
            int userIdx = nameIdx.get(name[0]);
            int reportedIdx = nameIdx.get(name[1]);

            reportedCount.get(reportedIdx).add(userIdx);
        }

        // 모든 reportedCount 확인
        for (int key : reportedCount.keySet()) {
            if (reportedCount.get(key).size() >= k) {
                for (int id : reportedCount.get(key)) {
                    answer[id]++;
                }
            }
        }

        return answer;
    }
}
