package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.*;

public class 개인정보_수집_유효기간 {
    public static void main(String[] args) {
        개인정보_수집_유효기간 Main = new 개인정보_수집_유효기간();
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(Arrays.toString(Main.solution(today, terms, privacies)));
    }

    // 한달 28일로 계산
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] termDetail = term.split(" ");
            termMap.put(termDetail[0], Integer.parseInt(termDetail[1]));
        }

        String[] todaySplit = today.split("\\.");
        int todayInt = Integer.parseInt(todaySplit[0]) * 12 * 28
                + Integer.parseInt(todaySplit[1]) * 28
                + Integer.parseInt(todaySplit[2]);

        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            String[] start = st.nextToken().split("\\.");
            String termType = st.nextToken();
            int termLength = termMap.get(termType);
            int expiredDateInt = Integer.parseInt(start[0]) * 12 * 28
                    + Integer.parseInt(start[1]) * 28
                    + Integer.parseInt(start[2])
                    + termLength * 28;

            if (todayInt >= expiredDateInt) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(o -> o).toArray();
    }
}
